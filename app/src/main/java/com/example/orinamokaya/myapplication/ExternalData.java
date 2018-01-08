package com.example.orinamokaya.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by orinamokaya on 1/8/18.
 *
 * this enables an app to permanently store data in the DS card or internal storage
 * when the app is updated, the data will not be lost. as opposed to shared preferences
 * where the data will be deleted
 *
 * the important thing is to check the environment status before attempting a read or write
 * operation
 */

public class ExternalData extends Activity {

    // these can only be accessed in this class
    private TextView canWrite, canRead;
    private String status;
    boolean canR, canW;
    Spinner spinner;
    String[] paths = {"Music", "Pictures", "Download"}; // where we will store data

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.externaldata);

        canRead = (TextView) findViewById(R.id.tvCanRead);
        canWrite = (TextView) findViewById(R.id.tvCanWrite);

        //status tells us whether we can read or write to the sd card or not
        status = Environment.getExternalStorageState(); // returns a string of the current state

        //check the environment status
        if (status.equals(Environment.MEDIA_MOUNTED)){
            //then we can read or write. you don't want to read or write to a storage that is not there.
            canRead.setText("true");
            canWrite.setText("true");
            canR = canW = true;

        }else if (status.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
            //can read but can't write
            canRead.setText("true");
            canWrite.setText("false");

            canR = true;
            canW = false;
        }else{
            // we cannot read or write
            canRead.setText("false");
            canWrite.setText("false");

            canR = canW = false;
        }

        //the spinner accepts an array adapter in its params
        // the array adapter data type depends on the data type you want. we are passing Strings so we use string
        //fo the array adapter accepts the current class context and a spinner layout i.e simple spinner
        //and the string array paths
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ExternalData.this, android.R.layout.simple_spinner_item, paths);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);


    }
}
