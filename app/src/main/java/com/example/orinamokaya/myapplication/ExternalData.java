package com.example.orinamokaya.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by orinamokaya on 1/7/18.
 *
 * we need a file out put stream to write data - save
 * and a file in put stream to read data - load
 */

public class ExternalData extends Activity implements View.OnClickListener{

    EditText sharedData;
    TextView dataResults;
    FileOutputStream fos; // to write data
    String FILENAME = "internaStorage"; // name of the file we will create and save data into

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.externaldata);
        setupVars();
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

        //open the file. we must surround with a try catch
        try {
            //MODE_PRIVATE means this can only be accessed by code. it is a hidden file
            fos = openFileOutput(FILENAME, Context.MODE_PRIVATE); // open input stream
            // anytime you open an input stream wemust close it
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.bSaveData:

                break;
            case R.id.bLoadData:

                break;
        }

    }
}
