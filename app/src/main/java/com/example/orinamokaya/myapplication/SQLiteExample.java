package com.example.orinamokaya.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by root on 1/15/18.
 */

public class SQLiteExample extends Activity implements View.OnClickListener {

    Button sqlUpdate, sqlView;
    EditText sqlFoodName, sqlDeliciousness;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sqlitexample);

        sqlDeliciousness = (EditText) findViewById(R.id.etSQLDeliciousness);
        sqlFoodName      = (EditText) findViewById(R.id.etSQLName);

        sqlUpdate        = (Button) findViewById(R.id.bSQLUpdate);
        sqlView          = (Button) findViewById(R.id.bSQLOpenView);

        sqlUpdate.setOnClickListener(this);
        sqlView.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.bSQLUpdate:
                //when this button is clicked both text will be grabbed, set to the name and
                // delicious variables and sent over to the DeliciousOrNot class, database will be
                // set up... etc
                boolean didItWork = true; // we'll use this to check whether data entry in to the database worked or not.

                //surround with try catch so we can catch any errors
                try {

                    // grab user data
                    String name = sqlFoodName.getText().toString();
                    String delicious = sqlDeliciousness.getText().toString();

                    // now lets access the data base using the class we created
                    DeliciousOrNot entry = new DeliciousOrNot(SQLiteExample.this);// you can also just say this to refer to this class
                    entry.open(); //open

                    entry.createEntry( name, delicious); // method that creates the database entry.

                    entry.close();
                } catch (Exception e) {
                    //e.printStackTrace();
                    didItWork = false;
                    // get the error as a string
                    String error = e.toString();
                    //set up a dialog
                    Dialog d = new Dialog(this);
                    d.setTitle("Database Error");
                    //set a text view for the dialog
                    TextView tv = new TextView(this);
                    tv.setText(error); // print the error so we can get the information
                    d.setContentView(tv); // set the text body of the dialog
                    d.show(); // show the dialog

                } finally {
                    if (didItWork){
                        //set up a dialog
                        Dialog d = new Dialog(this);
                        d.setTitle("Database Success");
                        //set a text view for the dialog
                        TextView tv = new TextView(this);
                        tv.setText("Database insert Successful!");
                        d.setContentView(tv); // set the text body of the dialog
                        d.show(); // show the dialog

                    }
                }

                break;
            case R.id.bSQLOpenView:

                //
                //start another activity
                Intent i = new Intent("com.example.orinamokaya.SQLVIEW"); // this will open the SQLView class
                startActivity(i);

                break;
        }

    }
}
