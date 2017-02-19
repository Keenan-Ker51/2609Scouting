package com.keenanker.a2609scouting;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MentorActivity extends AppCompatActivity {

    EditText teamOneNum;
    EditText teamTwoNum;
    EditText teamThreeNum;
    EditText teamFourNum;
    EditText teamFiveNum;
    EditText teamSixNum;
    EditText teamOneComments;
    EditText teamTwoComments;
    EditText teamThreeComments;
    EditText teamFourComments;
    EditText teamFiveComments;
    EditText teamSixComments;
    EditText mentorName;
    EditText matchNum;
    RadioGroup autoRotors;
    EditText totalRotorET;
    RadioGroup furnaceGroup;
    EditText kpaRemaining;


    Button saveButton;
    Button clearButton;
    String masterString;
    String titleString;

    File file;
    String[] saveText;

    String fullPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Scouting";

    String autoRotor;
    String furnaceCheck;


    public void makeMaster(){

        titleString = "Mentor"+matchNum.getText().toString() + mentorName.getText();

        masterString = teamOneNum.getText().toString() + ","+  teamOneComments.getText().toString() +
                "," + teamTwoNum.getText().toString() + ","+  teamTwoComments.getText().toString() +
                ","+ teamThreeNum.getText().toString() + ","+  teamThreeComments.getText().toString() +
                ","+ teamFourNum.getText().toString() + ","+  teamFourComments.getText().toString() +
                ","+ teamFiveNum.getText().toString() + ","+  teamFiveComments.getText().toString() +
                ","+ teamSixNum.getText().toString() + ","+  teamSixComments.getText().toString() + ","+
                autoRotor+","+totalRotorET.getText().toString()+","+furnaceCheck+","+kpaRemaining.getText().toString()+
                "," + mentorName.getText();

    }

    public void saveButton (View view) {

        makeMaster();
        //File file = new File (path + "scouting.txt");
        file = new File(fullPath, titleString + ".txt");
        saveText = String.valueOf(masterString).split(System.getProperty("line.separator"));

        //teamNum.setText("");

        Toast.makeText(getApplicationContext(), "This will make a fine addition to my collection", Toast.LENGTH_LONG).show();

        saveFunction(file, saveText);

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


    public void clearButton(View view){
        teamOneNum.setText("");
        teamOneComments.setText("");
        teamTwoNum.setText("");
        teamTwoComments.setText("");
        teamThreeNum.setText("");
        teamThreeComments.setText("");
        teamFourNum.setText("");
        teamFourComments.setText("");
        teamFiveNum.setText("");
        teamFiveComments.setText("");
        teamSixNum.setText("");
        teamSixComments.setText("");

    }

    public void autoRotorCheck(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.autoRotorNo:
                if (checked)
                    autoRotor = "0";
                //Toast.makeText(this, "Did not attempt", Toast.LENGTH_SHORT).show();
                break;
            case R.id.autoRotorSucc:
                if (checked)
                    autoRotor = "2";
                //Toast.makeText(this, "Good good  ( ͡° ͜ʖ ͡°)", Toast.LENGTH_LONG).show();
                break;
        }
    }
    public void furnaceCheck(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.furnaceNo:
                if (checked)
                    furnaceCheck = "0";
                //Toast.makeText(this, "Did not attempt", Toast.LENGTH_SHORT).show();
                break;
            case R.id.furnaceSucc:
                if (checked)
                    furnaceCheck = "2";
                //Toast.makeText(this, "Good good  ( ͡° ͜ʖ ͡°)", Toast.LENGTH_LONG).show();
                break;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor);

        teamOneNum = (EditText) findViewById(R.id.teamOneNum);
        teamTwoNum = (EditText) findViewById(R.id.teamTwoNum);
        teamThreeNum = (EditText) findViewById(R.id.teamThreeNum);
        teamFourNum = (EditText) findViewById(R.id.teamFourNum);
        teamFiveNum = (EditText) findViewById(R.id.teamFiveNum);
        teamSixNum = (EditText) findViewById(R.id.teamSixNum);
        teamOneComments = (EditText) findViewById(R.id.teamOneComments);
        teamTwoComments = (EditText) findViewById(R.id.teamTwoComments);
        teamThreeComments = (EditText) findViewById(R.id.teamThreeComments);
        teamFourComments = (EditText) findViewById(R.id.teamFourComments);
        teamFiveComments = (EditText) findViewById(R.id.teamFiveComments);
        teamSixComments = (EditText) findViewById(R.id.teamSixComments);
        clearButton = (Button) findViewById(R.id.clearButton);
        saveButton = (Button) findViewById(R.id.saveveButton);
        mentorName = (EditText) findViewById(R.id.mentorName);
        matchNum = (EditText) findViewById(R.id.matchNum);

        kpaRemaining = (EditText) findViewById(R.id.kpaRemainET);
        totalRotorET = (EditText) findViewById(R.id.rotorCountET);


    }
}

