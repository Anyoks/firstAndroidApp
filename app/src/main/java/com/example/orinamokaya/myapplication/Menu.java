package com.example.orinamokaya.myapplication;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by orinamokaya on 7/16/17.
 */

public class Menu extends ListActivity { // the ListActivity class has list manipulation methods that we need as well as Activity methods

    String classes [] = {"MainActivity", "TextPlay", "Email", "Camera", "Data", "GFX", "GFXSurface", "SoundStuff", "Slider",
            "Tabs","SimpleBrowser"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        We want this to be Full screen so we request the OS to allow us to do so.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        /*here we will create a list by using an array adapter that will convert our
                *array to a string*/
        setListAdapter( new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1,classes));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        // we want cheese to have the name of the list item on the position clicked. so we use 'position'
        String cheese = classes[position];

        /*how to start an activity using  a class
        this is going to create a new calss variable for the path name in quotes
        Just incase the class being created does not exist, we do a try catch thing to handle exceptions
        */
        try {
            /* now we are going to create a class for each list item clicked and use the class created
            to start an activity.
            This means that each list item clicked will start a different activity. This is our goal
             */
            Class ourClass = Class.forName("com.example.orinamokaya.myapplication." + cheese);
            Intent ourIntent = new Intent(Menu.this, ourClass);
            startActivity(ourIntent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Creating our menu
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater blowUp = getMenuInflater();
        blowUp.inflate(R.menu.cool_menu, menu);
        return true;

    }

    //making the menu items to work when selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // we will use a switching case on the menu ids to identify which is selected and do something
        switch (item.getItemId()) {
            case R.id.aboutUs:
                // an about us activity will be started when this is selected [that means there's an aboutus class and xml and manifest declaration  ]
                Intent i = new Intent("com.example.orinamokaya.ABOUT");
                startActivity(i);
                break;
            case R.id.preferences:
                // a prefs activity will be started when this is selected
                Intent p = new Intent("com.example.orinamokaya.PREFS"); // [that means there's a prefs class and xml and manifest declaration ]
                startActivity(p);
                break;
            case R.id.exit: // this will end the application
                finish(); // this is enough to end the app because at this time only the menu activity will be running
                break;
        }
        return false;
    }
}
