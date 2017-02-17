package com.keenanker.a2609scouting;

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


public class HumanScoutActivity extends AppCompatActivity {

    EditText teamOneNum;
    EditText teamTwoNum;
    EditText teamThreeNum;
    EditText teamFourNum;
    EditText teamOneComments;
    EditText teamTwoComments;
    EditText teamThreeComments;
    EditText teamFourComments;
    EditText scoutName;
    EditText matchNum;
    Button saveButton;
    Button clearButton;
    String masterString;
    String titleString;

    File file;
    String[] saveText;

    String fullPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Scouting";

    public void makeMaster(){

        titleString = matchNum.getText().toString() + scoutName.getText();

        masterString = teamOneNum.getText().toString() + "\n"+  teamOneComments.getText().toString() +
                "\n" + teamTwoNum.getText().toString() + "\n"+  teamTwoComments.getText().toString() +
                "\n"+ teamThreeNum.getText().toString() + "\n"+  teamThreeComments.getText().toString() +
                "\n"+ teamFourNum.getText().toString() + "\n"+  teamFourComments.getText().toString() +
                "\n"; scoutName.getText();

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

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_human_scout);

        teamOneNum = (EditText) findViewById(R.id.teamOneNum);
        teamTwoNum = (EditText) findViewById(R.id.teamTwoNum);
        teamThreeNum = (EditText) findViewById(R.id.teamThreeNum);
        teamFourNum = (EditText) findViewById(R.id.teamFourNum);
        teamOneComments = (EditText) findViewById(R.id.teamOneComments);
        teamTwoComments = (EditText) findViewById(R.id.teamTwoComments);
        teamThreeComments = (EditText) findViewById(R.id.teamThreeComments);
        teamFourComments = (EditText) findViewById(R.id.teamFourComments);
        clearButton = (Button) findViewById(R.id.clearButton);
        saveButton = (Button) findViewById(R.id.saveveButton);
        scoutName = (EditText) findViewById(R.id.scoutName);
        matchNum = (EditText) findViewById(R.id.matchNum);
    }
}
