package com.example.orinamokaya.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TabHost;

/**
 * Created by orinamokaya on 1/4/18.
 */

public class Tabs extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs);

        TabHost tH = (TabHost) findViewById(R.id.tabhost);
        tH.setup(); // sets up the tab, now we can set up individual tabs

        //setting up individual tabs
        TabHost.TabSpec specs = tH.newTabSpec("tag1");
        specs.setContent(R.id.tab1);
        specs.setIndicator("StopWatch");
        tH.addTab(specs);

        specs = tH.newTabSpec("tag2");
        specs.setContent(R.id.tab2);
        specs.setIndicator("Tab 2");
        tH.addTab(specs);

        specs = tH.newTabSpec("tag3");
        specs.setContent(R.id.tab3);
        specs.setIndicator("Add a Tab");
        tH.addTab(specs);
    }
}
