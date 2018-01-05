package com.example.orinamokaya.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * Created by orinamokaya on 1/4/18.
 */

public class Tabs extends Activity implements View.OnClickListener {

    TabHost tH;
    TextView showResults;
    long start, stop; // so we can work with ms

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs);

        tH = (TabHost) findViewById(R.id.tabhost);
        //setting up other buttons

        //add tab button
        Button newTab = (Button) findViewById(R.id.bAddTab);

        //stop watch buttons
        Button bStart = (Button) findViewById(R.id.bstartWatch);
        Button bStop = (Button) findViewById(R.id.bstopWatch);

        //set up the view where the results will be displayed
        showResults = (TextView) findViewById(R.id.tvShowWatch);

        // set onclick listener for start stop buttons
        bStart.setOnClickListener(this);
        bStop.setOnClickListener(this);

        //set an onclick listener for new tab button
        newTab.setOnClickListener(this);



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

        // adding the simple stop watch
        start = 0;// initialize this;

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.bAddTab:
                //create the tab when clicked
                TabHost.TabSpec ourSpec = tH.newTabSpec("tag1");
                ourSpec.setContent(new TabHost.TabContentFactory() {
                    @Override
                    public View createTabContent(String s) {

                        TextView text = new TextView(Tabs.this);
                        text.setText("You've created a new Tab!");
                        return text;
                    }
                });
                ourSpec.setIndicator("New"); // this is the title of the tab
                tH.addTab(ourSpec);

                break;
            case R.id.bstartWatch:
                //get current time when start button is clicked
                start = System.currentTimeMillis();

                break;
            case R.id.bstopWatch:
                // get current time when stop button is clicked
                stop = System.currentTimeMillis();

                //in-case stop is clicked before start...
                if (start != 0){
                    long totalTime = stop - start ;

                    //formating the results to be more readable
                    int milis = (int) totalTime;
                    int seconds = (int) totalTime/1000;
                    int minutes = seconds/60;

                    //divides and returns only the remainder
                    milis = milis % 100;
                    seconds = seconds % 60;

                    //change the totalTime to a string and  display in the text view
                    showResults.setText(String.format("%d :%02d :%02d", minutes, seconds, milis));
                }
                break;
        }
    }
}
