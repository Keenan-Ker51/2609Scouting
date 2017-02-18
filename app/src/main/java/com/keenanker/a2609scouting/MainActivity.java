package com.keenanker.a2609scouting;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    public void scoutStart (View view){
        Intent i = new Intent(getApplicationContext(), ScoutActivity.class);
        startActivity(i);

    }
    public void humanScoutStart (View view){
        Intent i = new Intent(getApplicationContext(), HumanScoutActivity.class);
        startActivity(i);
    }
    public void mentorStart (View view){
        Intent i = new Intent(getApplicationContext(), MentorActivity.class);
        startActivity(i);
    }
    public void pitStart (View view){
        Intent i = new Intent(getApplicationContext(), PitActivity.class);
        startActivity(i);
    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                //Log.v(TAG,"Permission is granted");
                return true;
            } else {

                //Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            //Log.v(TAG,"Permission is granted");
            return true;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isStoragePermissionGranted();



    }
}
