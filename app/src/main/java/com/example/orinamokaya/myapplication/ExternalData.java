package com.example.orinamokaya.myapplication;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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

public class ExternalData extends Activity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    // these can only be accessed in this class
    private TextView canWrite, canRead;
    private String status;
    boolean canR, canW;
    Spinner spinner;
    String[] paths = {"Music", "Pictures", "Download"}; // where we will store data
    File dirPath = null;
    File file = null; // the file we will save
    EditText saveAs; // user input of the name of the file that is to be saved
    Button confirm, save;



    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.externaldata);

        canRead = (TextView) findViewById(R.id.tvCanRead);
        canWrite = (TextView) findViewById(R.id.tvCanWrite);

        confirm = (Button) findViewById(R.id.bConfirmFilePath);
        save = (Button) findViewById(R.id.bSaveFile);

        saveAs = (EditText) findViewById(R.id.etSaveAs);

        confirm.setOnClickListener(this);
        save.setOnClickListener(this);

        checkReadWriteStatus();

        //the spinner accepts an array adapter in its params
        // the array adapter data type depends on the data type you want. we are passing Strings so we use string
        //fo the array adapter accepts the current class context and a spinner layout i.e simple spinner
        //and the string array paths
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ExternalData.this, android.R.layout.simple_spinner_item, paths);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);// when an item is selected do something


    }

    private void checkReadWriteStatus() {

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
    }

    @TargetApi(Build.VERSION_CODES.FROYO)
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        int position = spinner.getSelectedItemPosition(); //get the position of the selected item

        // this is simply to tell us where we want to save he item. what directory
        //the position is either Music, Pictures, or Download. they must be in that order. as in the list we created
        switch (position){
            case 0:
                //getExternalStoragePublicDirectory gets a location that can be accessed even without the app
                //this path must co-respond with the first item in the string array paths

                // returns Music folder path
                dirPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
                break;

            case 1:
                // returns pictures folder path
                dirPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

                break;
            case 2:
                // returns downloads path
                dirPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {

        //when the confirm button is clicked...
        switch (view.getId()){

            case R.id.bConfirmFilePath:

                //reveal save button
                save.setVisibility(View.VISIBLE);

                break;
            //when the save button is clicked...
            case R.id.bSaveFile:
                String f = saveAs.getText().toString() + ".png"; // this will be the preferred file name inputed by the user
                //this will create a file
                file = new File(dirPath,f); // dirPath is the folder to save the file and f is the name of the file

                //check if we can read and write.
                checkReadWriteStatus();
                if (canR == canW == true){

                    //in case the path does not exist, create a folder there. if it does so nothing
                    dirPath.mkdirs(); // will create a folder where the path is pointing if it does not exist


                    //now lets write
                    try {

                        // this will save a picture to the particular location selected and neamed by the user in the edit text,
//                        InputStream is =  getResources().openRawResource(R.raw.anydo_pop); //we'll use this particular sound as an example
                        InputStream is =  getResources().openRawResource(+ R.drawable.brown_ball); //we'll use this particular sound as an example
                        OutputStream os = new FileOutputStream(file);

                        byte[] data = new byte[is.available()]; // size of the input stream
                        is.read(data); // read the input stream or the source
                        os.write(data); // write to the destination
                        is.close();
                        os.close();

                        Toast t = Toast.makeText(ExternalData.this,"File has been saved", Toast.LENGTH_LONG); // create a toast message notifying the user
                        t.show(); // show the toast message

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }


                break;
        }
    }
}
