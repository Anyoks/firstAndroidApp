package com.example.orinamokaya.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                // set up... et.t.c

                // grab user data
                String name = sqlFoodName.getText().toString();
                String delicious = sqlDeliciousness.getText().toString();

                // now lets access the data base using the class we created
                DeliciousOrNot entry = new DeliciousOrNot(SQLiteExample.this);
                entry.open(); //open

                entry.createEntry( name, delicious);

                entry.close();

                break;
            case R.id.bSQLOpenView:

                break;
        }

    }
}
