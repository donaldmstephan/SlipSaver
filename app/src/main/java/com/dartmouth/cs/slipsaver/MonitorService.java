package com.dartmouth.cs.slipsaver;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.dartmouth.cs.slipsaver.WekaClassifier;import com.dartmouth.cs.slipsaver.meapsoft.FFT;

import java.lang.Double;import java.lang.Exception;import java.lang.Integer;import java.lang.Math;import java.lang.Object;import java.lang.Override;import java.lang.String;import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Vector;

/**
 * Don Stephan and Katy Sprout
 * Tracking Service for the fitness tracker
 */
public class MonitorService extends Service implements SensorEventListener {

    //Location
    String Lprovider;
    LocationManager locationManager;
    SensorManager sensorManager;

    public Queue sensorQueue = new PriorityQueue();


    private final IBinder mBinder = new MonitorBinder();

    //Constants
    private String TAG = "CS65";
    public String INTENTFILTER2 = "UpdatedActivity";
    private static boolean isRunning = false;


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("CS65", "managers: " + locationManager + sensorManager);
        SetUpLocationManager();
        SetUpSensorManager();

        //Check location every 100L and update Exercise entry location list.
        //mTimer.scheduleAtFixedRate(new LocationTask(), 0, 10000L);
        isRunning = true;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
    }

    //Register sensors for accelerometer
    private void SetUpSensorManager() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY), SensorManager.SENSOR_DELAY_NORMAL);

    }

    private void SetUpLocationManager() {
        String svcName = Context.LOCATION_SERVICE;
        locationManager = (LocationManager) getSystemService(svcName);

        //Set criteria for tracking location
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        criteria.setAltitudeRequired(true);
        criteria.setBearingRequired(false);
        criteria.setSpeedRequired(true);
        criteria.setCostAllowed(true);
        Lprovider = locationManager.getBestProvider(criteria, true);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("CS65", "OnBind hit");
        return mBinder;
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        Object[] features;
        Double classed = 0.0;

        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        
        double m = Math.sqrt(x * x + y * y + z * z);
        sensorQueue.add(m);

        if (sensorQueue.size() >= 64) {
            features = GenerateFeatureVector();

            WekaClassifier classifier = new WekaClassifier();
            try {
                classed = classifier.classify(features);
            } catch (Exception e) {
                e.printStackTrace();
            }

            Log.d(TAG, "Sensor classified: " + classed);
            if (classed == 2) {
                //Broadcast to Tracking Service that we adjusted our activity type
                Intent intent = new Intent();
                intent.setAction(INTENTFILTER2);//random intent action
                Bundle extras = new Bundle();

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }

                //Get last known location, convert to byte array, then put byte array into the extras when we send a broadcast back to Main Activity.
                Location location = locationManager.getLastKnownLocation(Lprovider);
                byte[] bytes = getLocationByteArray(location);
                extras.putByteArray("Location", bytes);
                sendBroadcast(intent);
            }
        }
    }

    // Convert Location ArrayList to byte array, to pass back to
    public byte[] getLocationByteArray(Location location) {
        ArrayList<Location> loc = new ArrayList<Location>();
        loc.add(0,location);
        int[] intArray = new int[loc.size() * 2];

        for (int i = 0; i < loc.size(); i++) {
            intArray[i * 2] = (int) (loc.get(i).getLatitude() * 1E6);
            intArray[(i * 2) + 1] = (int) (loc.get(i).getLongitude() * 1E6);
        }

        ByteBuffer byteBuffer = ByteBuffer.allocate(intArray.length
                * Integer.SIZE);
        IntBuffer intBuffer = byteBuffer.asIntBuffer();
        intBuffer.put(intArray);

        return byteBuffer.array();
    }

    /*
     *  Generate a feature vector which will be the input of Weka classifier.
     */
    private Object[] GenerateFeatureVector() {
        Log.d(TAG, String.valueOf(sensorQueue));
        Vector featureVect = new Vector();
        double max = 0.0;
        FFT fft = new FFT(64);

        double[] re = new double[64];
        double[] im = new double[64];

        int j = 0;

        while (sensorQueue.size() != 0) {
            double value = (double) sensorQueue.poll();

            if (value >= max) {
                max = value;
            }
            im[j] = 0.0;
            re[j] = value;
            j++;
        }

        // Compute the re and im:
        fft.fft(re, im);

        for (int i = 0; i < re.length; i++) {
            // Compute each coefficient
            double mag = Math.sqrt(re[i] * re[i] + im[i] * im[i]);
            // Adding the computed FFT coefficient to the
            // featVect
            featureVect.add(Double.valueOf(mag));

            // Clear the field
            im[i] = .0;
        }

        // Finally, append max after frequency components
        featureVect.add(Double.valueOf(max));
        return featureVect.toArray();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    /*
     * Binding class were we can also access our new entry that the service created, as well as the service itself.
     */
    public class MonitorBinder extends Binder {
        MonitorService getService() {
            // Return this instance of TrackingBinder so MapActivity can call public methods
            return MonitorService.this;
        }
    }
}
