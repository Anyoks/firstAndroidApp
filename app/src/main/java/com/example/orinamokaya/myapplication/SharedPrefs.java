package com.example.orinamokaya.myapplication;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by orinamokaya on 1/7/18.
 *
 * shared preferences are used to store primitive data in a key value pair. e.g strings, bolean, int etc
 */

public class SharedPrefs extends Activity implements View.OnClickListener {

    // we want o use these vars throught the class
    EditText sharedData;
    TextView dataResults;
    public static String fileName = "MySharedData"; // to save data you need a file name that does not change [static] and is accessible to other clases [public]
    SharedPreferences someData; // this is where shared data will be stored

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharedprefs);
        setupVars();
        someData = getSharedPreferences(fileName,0); //load data into the shared pref variable

    }

    private void setupVars() {
        //local variables
        Button saveData = (Button) findViewById(R.id.bSaveData);
        Button loadData = (Button) findViewById(R.id.bLoadData);

        //global variables
        sharedData = (EditText) findViewById(R.id.etData);
        dataResults = (TextView) findViewById(R.id.tvDataResults);

        //set onlcilck listeners
        saveData.setOnClickListener(this);
        loadData.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.bSaveData:
                /*Each time we hit save, the text will be grabbed from the edit text converted to a string
                * then we'll access our shared preference someData and edit it to contain the string stringData
                 * we just grabbed, give it a key for reference then cpmmit the changes to the shared preference
                 *
                 * YOu can save as many strings and boolean, and int as possible and later load them using for use.
                * */

                //this is the variable we want to save
                String stringData = sharedData.getText().toString(); // get data input and convert to string

                // we need an editor to edit the data and save it
                SharedPreferences.Editor editor = someData.edit(); // this is editing the data we want to save then we can save it.
                editor.putString("sharedString",stringData); // this is a key value pair. key is sharedString, value is the StringDatas
                editor.commit(); // make the changes binding and save in the shared preference.

                break;
            case R.id.bLoadData:
                // update the shared preference variable, or load it here in order to get data from it
                someData = getSharedPreferences(fileName,0); //load shared preference
                /* now fetch the saved string data from the shared preference using the key used in saving it i.e "sharedString" tp a
                string varible called dataFetch and a default value in case the file name or key cannot be found
                */
                String dataFetch = someData.getString("sharedString", "Couldn't load data, did you save any?");
                dataResults.setText(dataFetch);// display the data on the text view

                break;
        }

    }
}

