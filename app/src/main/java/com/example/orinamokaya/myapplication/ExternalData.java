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

import java.io.File;
import java.io.FileInputStream;
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
                String data = sharedData.getText().toString(); //get user data

                /* this is one method to write data into a file int the internal storage via FILE
                File f = new File(FILENAME); // create a file with name FILENAME
                try {
                    fos = new FileOutputStream(f); //create a file output stream for the file
                    // write data
                    fos.close(); // close the file
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                try {
                    fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                    fos.write(data.getBytes());// write data in bytes
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.bLoadData:
                // FileInputStream is used to load data or read data
                String collectedData = null;
                FileInputStream fis = null;
                try {
                    fis = openFileInput(FILENAME); //open the file via input stream
                    byte[] dataArray = new byte[fis.available()]; //get the length of the file
                    //loop through the file byte by byte and save each byte as a string in coollectedData
                    while (fis.read(dataArray) != -1){
                        collectedData = new String(dataArray);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        fis.close();// close the file
                        dataResults.setText(collectedData); // update the text view

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                break;
        }

    }
}
