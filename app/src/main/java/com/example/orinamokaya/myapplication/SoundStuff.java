package com.example.orinamokaya.myapplication;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by orinamokaya on 1/4/18.
 */

public class SoundStuff extends Activity implements View.OnClickListener, View.OnLongClickListener {

    SoundPool sp; //used for short sounds, loads quickly
    int explosion = 0;
    MediaPlayer mP; // used for longer sounds
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // creating a new view, without xml layout.
        View v = new View(this);
        v.setOnClickListener(this);
        v.setOnLongClickListener(this);
        setContentView(v);

        //setting up the soundpool object
        sp = new SoundPool(5, AudioManager.STREAM_MUSIC,0) ;
        //load the Sp object into explosion var
        explosion = sp.load(this,R.raw.anydo_pop,1);
        //setting up the media player for longer sounds
        mP = MediaPlayer.create(this,R.raw.psalm19_14 );
    }

    // this method is called when the screen is touched
    @Override
    public void onClick(View view) {

        //whenever the screen is touched, anywhere, the sound will be played.

        //play the explosion from the sound pool object only if ithas been loaded
        if (explosion!=0)
        sp.play(explosion,1,1,0,0,1);

    }

    // this method is called when one presses and holds for a longer time
    @Override
    public boolean onLongClick(View view) {

        mP.start();// play the background sound
        return false;
    }
}
