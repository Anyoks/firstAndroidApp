package com.example.orinamokaya.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

/**
 * Created by orinamokaya on 7/12/17.
 */

public class Splash extends Activity{

    MediaPlayer splashSound; // variable for our start up sound, we define it here so we can access it in the onPause method
    @Override
    protected void onCreate(Bundle sampleVariable) {
        super.onCreate(sampleVariable); //sample variable is a variable that is passed into this method
        setContentView(R.layout.splash); // this is how this activity will have access to the layout called splash

        splashSound = MediaPlayer.create(Splash.this, R.raw.splash_sound); // we now define the variable

        // now we want to make this splash song optional.
        // if the preference is checked it'll play,if not it won't.

        // first we have to access our preferences [check out the prefs xml and java class]
        SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        // now we get the value in the preference checkbox, if not set, the default value is set to true
        boolean splash = getPrefs.getBoolean("checkbox",true);

        //if splash sound is checked, play the sound. If not, don't play the sound
        if (splash == true){
            splashSound.start(); // start the sound
        }


//        I want this activity to last only for a few seconds then the app begins, this will be accomplishes
//        With threads,
        Thread timer = new Thread(){
//            A thread is always looking for a method called Run, we need that set up.
            public void run(){
                try{
                    sleep(5000);
            } catch (InterruptedException e) {
                    e.printStackTrace();
            }finally {
                    //the Method startActivity needs an intent to run, so we have to first define an intent
                    // which is basically to open up the main Activity by using it's action name as in the Manifest file.
                    Intent openMainActivity = new Intent("com.example.orinamokaya.MENU"); // see how we use the action name here?
                    startActivity( openMainActivity);
                }
            }
        };

        timer.start(); // This is what starts the thread
    }

    @Override
    protected void onPause() {
        super.onPause();
        splashSound.release(); // tells the song variable, "we are done!"
        finish(); // the splash activity will now die forever. till the app is restarted
    }
}
