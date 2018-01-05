package com.example.orinamokaya.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;

/**
 * Created by orinamokaya on 1/2/18.
 */

public class GFX extends Activity{

    //this will be our own data type(class) type called MyBringBack. we will use it to hold our graphix
    MyBringBack ourView;
    PowerManager.WakeLock wL;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        //Setting up the wake-Lock.
        //what this does, is it either dims the screen after some time of no activity to save power
        //or it prevents the phone from dimming and locking for as long as needed

        //to use the wake lock you need the power manager class.
        PowerManager pM = (PowerManager)getSystemService(Context.POWER_SERVICE);
        wL = pM.newWakeLock(PowerManager.FULL_WAKE_LOCK, "gfxWakeLock");

        super.onCreate(savedInstanceState);
        wL.acquire(); // start the wake lock
        ourView = new MyBringBack(this); // creating a new object, an instance of MyBringBack class
        setContentView(ourView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        wL.release(); // turn off the wake lock
    }

    @Override
    protected void onResume() {
        super.onResume();
        wL.acquire(); // start it again when the app resumes
    }
}
