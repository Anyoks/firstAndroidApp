package com.example.orinamokaya.myapplication;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SlidingDrawer;

/**
 * Created by orinamokaya on 1/4/18.
 */

public class Slider extends Activity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, SlidingDrawer.OnDrawerOpenListener {

    Button handle1;
    Button handle2;
    Button handle3;
    Button handle4;
    CheckBox checkBox;
    SlidingDrawer sD;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sliding);
        setupVars();

        checkBox.setOnCheckedChangeListener(this);
        sD.setOnDrawerOpenListener(this);
        handle1.setOnClickListener(this);
        handle2.setOnClickListener(this);
        handle3.setOnClickListener(this);
        handle4.setOnClickListener(this);
    }

    private void setupVars() {
        handle1 = (Button) findViewById(R.id.handle1);
        handle2 = (Button) findViewById(R.id.handle2);
        handle3 = (Button) findViewById(R.id.handle3);
        handle4 = (Button) findViewById(R.id.handle4);
        checkBox = (CheckBox) findViewById(R.id.checkBoxSlidable);
        sD = (SlidingDrawer) findViewById(R.id.slidingDrawer);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.handle1:
                sD.open(); // open slider when clicked
                break;
            case R.id.handle2:

                break;
            case R.id.handle3:
                sD.toggle();
                break;
            case R.id.handle4:
                sD.close(); // close slider when clicked

                break;

        }

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        // when the checkbox is checked..
        if (compoundButton.isChecked()){
            sD.lock(); // lock slider, it won't be closed
        }else {
            sD.unlock(); // unlock slider
        }

    }

    @Override
    public void onDrawerOpened() {
        //do something whenthe drawer opens

        //when the drawer opens this sound will be played
        MediaPlayer mP  = MediaPlayer.create(this, R.raw.anydo_pop);
        mP.start();
    }
}
