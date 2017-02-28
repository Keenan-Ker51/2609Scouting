package com.keenanker.a2609scouting;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PitActivity extends AppCompatActivity {

    EditText pitName;
    EditText pitInfo;
    EditText teamNum;

    String titleString;
    String masterString;
    File file;
    String[] saveText;

    String fullPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Scouting/Pit";


    public void saveButton (View view) {

        makeMaster();
        //File file = new File (path + "scouting.txt");
        file = new File(fullPath, titleString + ".txt");
        saveText = String.valueOf(masterString).split(System.getProperty("line.separator"));

        //teamNum.setText("");

        // Toast.makeText(getApplicationContext(), "This will make a fine addition to my collection", Toast.LENGTH_LONG).show();

        saveFunction(file, saveText);

    }

    public void makeMaster(){

        titleString = "Pit"+teamNum.getText().toString() + pitName.getText();

        masterString = pitInfo.getText().toString();

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

        File fooo = new File(exstPath + "/Scouting/Pit");
        boolean success = fooo.mkdir();
        System.out.println(mExternalStorageAvailable);
        System.out.println(mExternalStorageWriteable);
        System.out.println(success);
    }

    public void clearButton(View view){
        teamNum.setText("");
        pitInfo.setText("");


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pit);
        makeFolder();


        pitName = (EditText) findViewById(R.id.nameET);
        teamNum = (EditText) findViewById(R.id.teamNumber);
        pitInfo = (EditText) findViewById(R.id.infoET);




    }
}
