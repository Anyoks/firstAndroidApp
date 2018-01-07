package com.example.orinamokaya.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ViewFlipper;

/**
 * Created by orinamokaya on 1/7/18.
 *
 * this class is best fofr flash cards , studying stuff, flipping through pictures etc.
 * photo gallery, to flip through all pictures quickly etc
 */

public class Flipper extends Activity implements View.OnClickListener {

    ViewFlipper flippy;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flipper);

        flippy = (ViewFlipper) findViewById(R.id.viewFlipper1); // sett up the view flipper layout
        flippy.setOnClickListener(this);

        //flip automatically every half a second
        flippy.setFlipInterval(500); // set flipping time
        flippy.startFlipping(); // start flipping
    }

    @Override
    public void onClick(View view) {
        flippy.showNext();// SHOW  next view when clicked
    }
}
