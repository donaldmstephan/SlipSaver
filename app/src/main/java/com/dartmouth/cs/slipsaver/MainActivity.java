package com.dartmouth.cs.slipsaver;

import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements ServiceConnection {

    private Fragment fragment;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    BroadcastReceiver receiver;
    IntentFilter filter = new IntentFilter();
    public boolean fallen = false;
    public String INTENTFILTER2 = "UpdatedActivity";
    public byte[] bytes;
    MonitorService mService;

    boolean mIsBound;
    private ServiceConnection mConnection = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Slip Saver");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Slip Saver");
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        setupDrawerContent(navigationView);

        //Create a schedule fragment to start the view on
        EnableScreenFragment iFrag = new EnableScreenFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragContent, iFrag).commit();

         /*
         Receives alert of a fall and then converts the given location from byte[] to Location.
         */
        filter.addAction(INTENTFILTER2);
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                //Listen for the updated location broadcast to redraw the map
                if (action.equals(INTENTFILTER2)) {
                    fallen = true;
                    Bundle extras = intent.getExtras();
                    bytes = extras.getByteArray("Location");
                }
            }
        };

        if (fallen) {
            Intent alertIntent = new Intent(this, AlertActivity.class);
            Bundle alertExtras = new Bundle();
            alertExtras.putByteArray("Location", bytes);
            startActivity(alertIntent);
        }
    }

    //control the drawer
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //inflate the menu bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    //set up the drawer menu and clicker
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the planet to show based on
        // position
        fragment = null;

        Class fragmentClass;
        switch (menuItem.getItemId()) {
            case R.id.nav_enable:
                if (menuItem.getTitle().toString().equals("Enable Monitor Mode")) {
                    fragmentClass = DisableScreenFragment.class;
                    menuItem.setIcon(android.R.drawable.star_on);
                    SpannableString s = new SpannableString("Disable Monitor Mode");
                    s.setSpan(new ForegroundColorSpan(Color.RED), 0, s.length(), 0);
                    menuItem.setTitle(s);
                    if (mConnection != this) {
                        unbindService(mConnection);
                        Log.d("CS65", "Unbinding");
                    }
                } else {
                    fragmentClass = EnableScreenFragment.class;
                    SpannableString s = new SpannableString("Enable Monitor Mode");
                    s.setSpan(new ForegroundColorSpan(Color.argb(250, 30, 90, 50)), 0, s.length(), 0);
                    menuItem.setTitle(s);
                    menuItem.setIcon(android.R.drawable.star_off).setChecked(false);
                    Log.d("CS65", "Binding");
                    automaticBind();

                }
                toolbar.setTitle("Slip Saver");
                break;
            case R.id.nav_contacts:
                fragmentClass = ContactFragment.class;
                toolbar.setTitle("Emergency Contacts");
                break;
            case R.id.nav_privacy:
                fragmentClass = PrivacyFragment.class;
                toolbar.setTitle("Privacy");
                break;
            case R.id.nav_mode:
                fragmentClass = ModeFragment.class;
                toolbar.setTitle("Sport Mode");
                break;
            default:
                fragmentClass = DisableScreenFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragContent, fragment).commit();

        // Highlight the selected item, update the title, and close the drawer
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }


    /**
     * Check if the service is running. If the service is running
     * when the activity starts, we want to automatically bind to it.
     */
    private void automaticBind() {
        doBindService();
    }

    /**
     * Bind this Activity to Tracking Service
     */
    private void doBindService() {
        bindService(new Intent(this, MonitorService.class), mConnection, Context.BIND_AUTO_CREATE);
        mIsBound = true;
        Log.d("CS65", "Is m bound?: " + mIsBound);
    }

    public void OnCancel(View view) {
        unbindService(mConnection);
        //unregisterReceiver(receiver);
        finish();
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        MonitorService.MonitorBinder binder = (MonitorService.MonitorBinder) service;
        mService = binder.getService();
        Log.d("CS65", "Connected to service!");
        //ee = binder.getExerciseEntry();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
 /*   private void SendEmail() {
        Intent i = new Intent(Intent.ACTION_SEND);

        i.setType("message/rfc822");

        //Load contacts from database in background
          String email = "k.sprout16@gmail.com";
            String name = "Katy";
            i.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
            i.putExtra(Intent.EXTRA_TEXT, name + " We are alerting you about a detected fall.");
            i.putExtra(Intent.EXTRA_SUBJECT, "Slip Saver Alert!");

            try {
                startActivity(Intent.createChooser(i, "Send mail..."));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(this, "Sorry, no email clients installed..?", Toast.LENGTH_SHORT).show();
            }
        }*/
}