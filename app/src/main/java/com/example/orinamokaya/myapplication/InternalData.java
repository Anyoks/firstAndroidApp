package com.example.orinamokaya.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

public class InternalData extends Activity implements View.OnClickListener{

    EditText sharedData;
    TextView dataResults;
    FileOutputStream fos; // to write data
    String FILENAME = "internalStorage"; // name of the file we will create and save data into

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.internaldata);
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
                // this enables us to do this in the background to avoid hanging if the file is large
                new loadSomeStuff().execute(FILENAME);
//                dataResults.setText(collected);

                break;
        }

    }
    // taking file loading  into the background..
    // string - parameters being passed in to the method
    // Integers - progrss bar
    //String - data returned returned
    public class loadSomeStuff extends AsyncTask<String, Integer, String> {

        //there are four methods that are called in this Asynch task class.
        // 1. onPreExecute 2. doInBackground 3. onProgressUpdate 4. onPostExecute
        //they are called in that order.

        //setting up var for progess bar display
//        ProgressBar progressBar;
        ProgressDialog progressBar1;

        //this method will be exectured first in this Asynch task
        protected void onPreExecute(){
            //set up the progress bar
//            progressBar = new ProgressBar(InternalData.this,null, android.R.attr.progressBarStyleHorizontal); // takes the context of the main class and progress bar styele
            progressBar1 = new ProgressDialog(InternalData.this);
            progressBar1.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressBar1.setMax(100); //we could take the size of the bytes here. byte this is just an simple example
//            progressBar.setVisibility(View.VISIBLE); //show progress bar
//            progressBar.setVisibility(View.GONE); // disappear when done
            progressBar1.setTitle("Loading data...");
            progressBar1.show();//show

        }

        //this method will be executed second
        @Override
        protected String doInBackground(String... strings) {

            // FileInputStream is used to load data or read data
            String collectedData = null;
            FileInputStream fis = null;

            //for progress bar movement. a simple example
            for (int i = 0; i<100; i++ ){
                //refer to the onProgressUpdate method
                publishProgress(1); //this method calls the onPrigressUpdate method and passes in by 5 as the increment value
//                it'llincrement really fast so to see it lets add a sleep
                try {
                    Thread.sleep(88);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            progressBar1.dismiss();



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
                    return collectedData; // return the data

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        //this is called third. Integer...ptogress is an integer array called progress
        protected void onProgressUpdate(Integer...progress){
            //increment progress bar
//            progressBar.incrementProgressBy(progress[0]); //increment by 5 which is at position 0 of the array
            progressBar1.incrementProgressBy(progress[0]); //increment by 5 which is at position 0 of the array
        }

        //this method will be executed last after everything is completed. here is where we retrieve results
        protected void onPostExecute(String result){ // because we are returning a string called collected, we pass in a string var
            //this is where we set up or text view
            String s = " --from internal storage";
            dataResults.setText(result + s);
        }

    }
}
