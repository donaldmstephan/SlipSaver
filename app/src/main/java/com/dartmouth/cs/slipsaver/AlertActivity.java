package com.dartmouth.cs.slipsaver;

import android.app.Activity;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.location.Location;

import com.dartmouth.cs.slipsaver.Contact;import com.dartmouth.cs.slipsaver.ContactDBHelper;import com.dartmouth.cs.slipsaver.R;import com.google.android.gms.maps.model.LatLng;

import java.lang.Integer;import java.lang.Object;import java.lang.Override;import java.lang.String;import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Katy on 3/1/16.
 */
public class AlertActivity extends Activity {
    public Location mLocation = null;
    List<Contact> contacts;
    public ArrayList<String> fromColumns;
    public ContactDBHelper db;
    private ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_alert_wait);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            byte[] bytes = extras.getByteArray("Location");
            mLocation = setLocation(bytes);
        }
        Log.d("CS65", "Start Timer");
        //Start Timer
        new CountDownTimer(30000, 1000) {
            TextView timer = (TextView) findViewById(R.id.timer_text);

            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {
                timer.setText("An email has been sent regarding your safety.");
                SendEmail();

            }
        }.start();

    }

    private void SendEmail() {
        Intent i = new Intent(Intent.ACTION_SEND);

        i.setType("message/rfc822");

        //Load contacts from database in background
        db = new ContactDBHelper(this);
        lv = (ListView) findViewById(R.id.List_View);
        fromColumns = new ArrayList<>();

        Loader loader = new Loader(this);
       loader.loadInBackground();

        String emails = "";
        //Go through contacts to gather all the emails we need. Send email for each contact.
        for (Contact contact : contacts) {
            emails += contact.Email;
        }
        Sender sender = new Sender(this);
        sender.execute(emails);
    }

    // Convert byte array to Location.
    public Location setLocation(byte[] bytePointArray) {
        Location loc = null;
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytePointArray);
        IntBuffer intBuffer = byteBuffer.asIntBuffer();

        int[] intArray = new int[bytePointArray.length / Integer.SIZE];
        intBuffer.get(intArray);

        int locationNum = intArray.length / 2;

        for (int i = 0; i < locationNum; i++) {
            LatLng latLng = new LatLng((double) intArray[i * 2] / 1E6F,
                    (double) intArray[i * 2 + 1] / 1E6F);
            loc = new Location("");
            loc.setLatitude(latLng.latitude);
            loc.setLongitude(latLng.longitude);

        }
        return loc;
    }

    public void Disable(View view) {
        Intent monitorIntent = new Intent(this, MonitorService.class);
        startActivity(monitorIntent);
    }

    //Class that extends Async to load the exercise entries from the database in the background
    public class Loader extends AsyncTaskLoader {
        //private List<ExerciseEntry> dbEntries;
        public Loader(Context context) {
            super(context);
        }

        @Override
        public Object loadInBackground() {
            contacts = db.fetchEntries();
            return contacts;
        }
    }
    //Class that extends Async to load the exercise entries from the database in the background
    public class Sender extends AsyncTask {
        //private List<ExerciseEntry> dbEntries;
        public Sender(Context context) {
            super();
        }

        @Override
        protected Object doInBackground(Object[] params) {
            try {
                GMailSender sender = new GMailSender("theslipsaver", "slip_saver");
                Log.d("SendMail", sender.getPasswordAuthentication().getUserName());
                sender.sendMail("Slip Saver Alert!",
                        "Katy, we have received detection of a fall. ",
                        "TheSlipSaver@gmail.com",
                        String.valueOf(params));
            } catch (Exception e) {
                Log.e("SendMail", e.getMessage(), e);
            }
            Log.e("SendMail", "Sent Message!");

            return null;
        }
    }
}
