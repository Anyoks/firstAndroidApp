package com.example.orinamokaya.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
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
        }else if (status.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
            //can read but can't write
            canRead.setText("true");
            canWrite.setText("false");
        }else{
            // we cannot read or write
            canRead.setText("false");
            canWrite.setText("false");
        }
    }
}
