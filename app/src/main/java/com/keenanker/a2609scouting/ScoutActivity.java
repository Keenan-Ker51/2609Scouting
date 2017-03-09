package com.keenanker.a2609scouting;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.BaseColumns;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;


public class ScoutActivity extends AppCompatActivity implements View.OnTouchListener{




    public class ScoutingData{
        String scoutName;
        String teamNum;
        String matchNum;
        int lowAutoCount;
        int lowTeleCount;
        int highAutoCount;
        int highTeleCount;
        int gearTeleCount;
        String endPos;
        String comments;
    }


    public String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Scouting";
    Button save;
    EditText teamNum;
    EditText matchNum;
    EditText humanComments;
    EditText comments;
    String masterJSON;
    EditText scoutName;
    String masterString;
    String titleString;
    Button gearAutoAdd;
    Button gearAutoLess;
    Button gearTeleAdd;
    Button gearTeleLess;
    Button gearDroppedTeleAdd;
    Button gearDroppedTeleLess;
    Button lowAutoLess;
    Button lowAutoAdd;
    Button lowTeleLess;
    Button lowTeleAdd;
    Button highAutoAdd;
    Button highAutoLess;
    Button highTeleAdd;
    Button highTeleLess;
    Button liftoffLess;
    Button liftoffAdd;
    TextView gearsAutoCountTV;
    TextView gearsTeleCountTV;
    TextView gearsDroppedTeleCountTV;

    TextView lowAutoCountTV;
    TextView lowTeleCountTV;
    TextView highAutoCountTV;
    TextView highTeleCountTV;
    TextView liftoffCountTV;
    int lowAutoCount = 0;
    int lowTeleCount = 0;
    int highAutoCount = 0;
    int highTeleCount = 0;
    int gearAutoCount = 0;
    int gearTeleCount = 0;
    int gearDroppedTeleCount = 0;
    int liftoffCount = 0;
    int allianceNumber = 0;
    EditText allianceNumberET;
    String liftOffString;
    File file;
    File scoutFile;
    String[] saveText;
    String[] scoutSaveText;
    int totalScore;

    TextView liftTimer;

    Spinner liftoffDropdown;
    String[] liftoffDropItems;
    Spinner gearDropdown;
    String[] gearItems;

    RadioGroup gearGroup;
    RadioGroup liftGroup;
    RadioGroup allianceGroup;
    String alliance;
    String scoutString;
    int sharedPrefMade = 1;
    String username;

    String gearString;
    String autoHighString;
    String autoLowString;
    String teleHighString;
    String teleLowString;


    String fullPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Scouting";
    String scoutPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Scouting/ScoutingData";



    Chronometer chronometer;
    //long millisecondsUntilDone;
    int seconds;





    int liftPos;

    float saveTime;
    Boolean counterIsActive = false;
    CountDownTimer countDownTimer;
    String secondString;
    String finalCount;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }





    public static void saveFunction(File file, String[] data) {


        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            try {
                for (int i = 0; i < data.length; i++) {
                    assert fos != null;
                    fos.write(data[i].getBytes());
                    if (i < data.length - 1) {
                        fos.write("\n".getBytes());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                assert fos != null;
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void buttonSave(View view) {
        makeMaster();
        //File file = new File (path + "scouting.txt");
        file = new File(fullPath, titleString + ".txt");
        scoutFile = new File("scoutname.txt");
        saveText = String.valueOf(masterString).split(System.getProperty("line.separator"));
        scoutSaveText = String.valueOf(scoutString).split(System.getProperty("line.separator"));

        //teamNum.setText("");


        if (Objects.equals(teamNum.getText().toString(), "")){

            Toast.makeText(getApplicationContext(), "You are missing a team number!", Toast.LENGTH_SHORT).show();
            //Log.i("filename", titleString);
            //changes
        }
        else if (Objects.equals(matchNum.getText().toString(), "")){
            Toast.makeText(getApplicationContext(), "You are missing a team number!", Toast.LENGTH_SHORT).show();
            //Log.i("filename", titleString);
        }
        else if (Objects.equals(gearString, "")){
            Toast.makeText(getApplicationContext(), "You are missing a team number!", Toast.LENGTH_SHORT).show();
            //Log.i("filename", titleString);
        }
        else{
            saveFunction(file, saveText);
            Toast.makeText(getApplicationContext(), "This will make a fine addition to my collection", Toast.LENGTH_LONG).show();
        }

        //totalScore = gearTeleCount;
        //saveFunction(scoutFile, scoutSaveText);



        //Log.i("teamnum is", teamNum.getText().toString());
    }


    //Stuff that will hopefully read the drop downs

    public void onGearButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.gearDidntTry:
                if (checked)
                    gearString = "N";
                //Toast.makeText(this, "Did not attempt", Toast.LENGTH_SHORT).show();
                break;
            case R.id.gearTried:
                if (checked)
                    gearString = "T";
                //Toast.makeText(this, "Tried but failed", Toast.LENGTH_LONG).show();
                break;
            case R.id.gearSucc:
                if (checked)
                    gearString = "S";
                //Toast.makeText(this, "Good good  ( ͡° ͜ʖ ͡°)", Toast.LENGTH_LONG).show();
                break;
        }
    }

    public void onAutoLowButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.ALSucc:
                if (checked)
                    autoLowString = "S";
                //Toast.makeText(this, "Did not attempt", Toast.LENGTH_SHORT).show();
                break;

            case R.id.ALDidntTry:
                if (checked)
                    autoLowString = "N";
                //Toast.makeText(this, "Good good  ( ͡° ͜ʖ ͡°)", Toast.LENGTH_LONG).show();
                break;
        }
    }

    public void onTeleLowButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.TLDidntTry:
                if (checked)
                    teleLowString = "N";
                //Toast.makeText(this, "Did not attempt", Toast.LENGTH_SHORT).show();
                break;

            case R.id.TLSucc:
                if (checked)
                    teleLowString = "S";
                //Toast.makeText(this, "Good good  ( ͡° ͜ʖ ͡°)", Toast.LENGTH_LONG).show();
                break;
        }
    }


    public void onLiftButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.liftDidntTry:
                if (checked)
                    liftOffString = "N";
                //Toast.makeText(this, "Did not attempt", Toast.LENGTH_SHORT).show();
                break;
            case R.id.liftTried:
                if (checked)
                    liftOffString = "T";
                //Toast.makeText(this, "Tried but failed", Toast.LENGTH_LONG).show();
                break;
            case R.id.liftSucc:
                if (checked)
                    liftOffString = "S";
                //Toast.makeText(this, "Good good  ( ͡° ͜ʖ ͡°)", Toast.LENGTH_LONG).show();
                break;
        }
    }


    public void makeMaster() {
        Random random = new Random();
        int i = random.nextInt(1000);
        titleString = "Team"  + teamNum.getText().toString()+String.valueOf(i);



/*        masterString = teamNum.getText().toString() + ";"+alliance + ";" + matchNum.getText().toString() + ";" +
                ";" + gearString + ";" + lowAutoCount + ";" + highAutoCount  + ";" + gearTeleCount +
                ";" + lowTeleCount + ";" + highTeleCount + ";" + ";" + liftOffString //9
                + ";" + comments.getText().toString() + ";" + humanComments.getText().toString() +
                ";" + scoutName.getText().toString()+";"+alliance+";"+finalCount+";"+gearDroppedTeleCount
                ;*/

        masterString =   matchNum.getText().toString() +"~" + teamNum.getText().toString()+"~"
        +gearString+"~"+autoLowString+"~"+ String.valueOf(gearTeleCount)
        +"~"+teleLowString+"~"+liftOffString
        ;



        /*public class ScoutingData{
            String scoutName;
            int teamNum;s
            int matchNum;
            int lowAutoCount;
            int lowTeleCount;
            int highAutoCount;
            int highTeleCount;
            int gearTeleCount;
            int endPos;
            String comments;
        }*/

    }

    public void loadFile () {
        //Find the directory for the SD Card using the API
        //*Don't* hardcode "/sdcard"
        File sdcard = Environment.getExternalStorageDirectory();

        //Get the text file
        File file = new File(scoutPath, "scoutname.txt");

        //Read text from file
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (IOException e) {
            //You'll need to add proper error handling here
            Toast.makeText(this, "Sorry the programmer has aids :(", Toast.LENGTH_SHORT).show();
        }

        //Find the view by its id

//Set the text
        scoutName.setText(text.toString());
    }

    public void makeFolder() {

        boolean mExternalStorageAvailable = false;
        boolean mExternalStorageWriteable = false;

        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            mExternalStorageAvailable = true;
            mExternalStorageWriteable = true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            mExternalStorageAvailable = true;
            mExternalStorageWriteable = false;
        } else {
            mExternalStorageAvailable = false;
            mExternalStorageWriteable = false;
        }
        File exst = Environment.getExternalStorageDirectory();
        String exstPath = exst.getPath();

        File fooo = new File(exstPath + "/Scouting");
        boolean success = fooo.mkdir();
        System.out.println(mExternalStorageAvailable);
        System.out.println(mExternalStorageWriteable);
        System.out.println(success);
    }

    //Starting Adding Counts and Shit

    public void gearsTeleAdd(View view) {
        if (gearTeleCount == 12) {
            gearTeleCount = gearTeleCount;
        } else {
            gearTeleCount = gearTeleCount + 1;
            gearsTeleCountTV.setText(String.valueOf(gearTeleCount));
        }
    }
    public void gearsTeleLess(View view) {
        if (gearTeleCount == 0) {
            gearTeleCount = gearTeleCount;
        } else {
            gearTeleCount = gearTeleCount - 1;
            gearsTeleCountTV.setText(String.valueOf(gearTeleCount));
        }
    }



    public void clearButton (View view){

        gearTeleCount = 0;
        gearsTeleCountTV.setText(String.valueOf(gearTeleCount));
        lowAutoCount = 0;
        lowAutoCountTV.setText(String.valueOf(lowAutoCount));
        lowTeleCount = 0;
        lowTeleCountTV.setText(String.valueOf(lowTeleCount));
        highAutoCount = 0;
        highAutoCountTV.setText(String.valueOf(highAutoCount));
        highTeleCount = 0;
        highTeleCountTV.setText(String.valueOf(highTeleCount));

        comments.setText("");
        humanComments.setText("");

    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scout);
        //saveFunction();
        boolean that = false;
        teamNum = (EditText) findViewById(R.id.teamNumET);
        matchNum = (EditText) findViewById(R.id.match);
        humanComments = (EditText) findViewById(R.id.humanPlayerET);
        comments = (EditText) findViewById(R.id.commentsET);
        scoutName = (EditText) findViewById(R.id.scoutNameET);

        gearTeleAdd = (Button) findViewById(R.id.gearTeleAdd);
        gearTeleLess = (Button) findViewById(R.id.gearTeleLess);
        gearsTeleCountTV = (TextView) findViewById(R.id.gearCountTVTele);


        lowAutoLess = (Button) findViewById(R.id.lowHitLess);
        lowAutoAdd = (Button) findViewById(R.id.lowHitAdd);
        lowTeleLess = (Button) findViewById(R.id.lowTeleLess);
        lowTeleAdd = (Button) findViewById(R.id.lowTeleAdd);
        lowTeleCountTV = (TextView) findViewById(R.id.lowTeleCount);
        lowAutoCountTV = (TextView) findViewById(R.id.lowHitCount);

        highAutoLess = (Button) findViewById(R.id.highHitLess);
        highAutoAdd = (Button) findViewById(R.id.highHitAdd);
        highTeleLess = (Button) findViewById(R.id.highTeleLess);
        highTeleAdd = (Button) findViewById(R.id.highTeleAdd);
        highTeleCountTV = (TextView) findViewById(R.id.highTeleCount);
        highAutoCountTV = (TextView) findViewById(R.id.highHitCount);

        liftoffLess = (Button) findViewById(R.id.liftLess);
        liftoffAdd = (Button) findViewById(R.id.liftAdd);
        liftoffCountTV = (TextView) findViewById(R.id.liftCountTV);

        save = (Button) findViewById(R.id.saveButton);

        gearGroup = (RadioGroup)  findViewById(R.id.gearGroup);
        liftGroup = (RadioGroup) findViewById(R.id.liftGroup);
        allianceGroup = (RadioGroup) findViewById(R.id.allianceGroup);




        makeFolder();

        File dir = new File(path);
        dir.mkdirs();


        //Getting Permission Shit


        //Starting SQLite shit
//        DBHandler db = new DBHandler(this);
//
//
//        // Reading all shops
//        Log.d("Reading: ", "Reading all shops..");
//        List<Shop> shops = db.getAllShops();
//
//        for (Shop shop : shops) {
//            String log = "Id: " + shop.getId() + " ,Name: " + shop.getName() + " ,Address: " + shop.getAddress();
//            // Writing shops  to log
//            Log.d("Shop: : ", log);
//        }
    }

    public final class FeedReaderContract {
        // To prevent someone from accidentally instantiating the contract class,
        // make the constructor private.
        private FeedReaderContract() {
        }

        /* Inner class that defines the table contents */
        public class FeedEntry implements BaseColumns {
            public static final String TABLE_NAME = "Information";
            public static final String COLUMN_NAME_TITLE = "Autonomous";
        }
    }

}
