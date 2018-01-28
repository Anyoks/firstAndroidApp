package com.example.orinamokaya.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

/**
 * Created by root on 1/15/18.
 */

public class SQLView extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sqlview);

        //setting up a text view and linking it to the text view in the xml layout
        TextView tv = (TextView) findViewById(R.id.tVSQLinfo);

        // we want to get data from the Db so we set up a HotOrNot var and use it to retrieve data
        DeliciousOrNot info = new DeliciousOrNot(this);
        info.open(); // open the database
        String data = info.getData(); // this method is to retrieve data from the database
        info.close(); // close dB
        tv.setText(data);

    }
}
