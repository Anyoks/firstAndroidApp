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

                break;
            case R.id.bSQLOpenView:

                break;
        }

    }
}
