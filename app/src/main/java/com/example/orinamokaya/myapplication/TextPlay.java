package com.example.orinamokaya.myapplication;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

import java.util.Random;

/**
 * Created by orinamokaya on 7/16/17.
 *
 * 1 Corinthians 10:31 - Whatsoever you eat, or drink or whatsoever you do
 * do all to the glory of God.
 */

/**
 * Implements is the magic word, but it has a condition...
 * When you implement a method you MUST use all the methods in that class. Onclick listener has only one method :-)
*/

public class TextPlay extends Activity implements View.OnClickListener{
    // setting up variables for later use;
    Button chkCommand;
    ToggleButton passTog;
    EditText input;
    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.text); //this is the xml view that will show up for this activity
        /* NOTE
        * Any time you set up a new activity you must also set up its details in the android Manifest
        * */

        //Now we set up (initialize)the variables so we can grab data from the text view for later manipulation.
        // Code clean up, we'll do this in a method
        setUpVariables();

        passTog.setOnClickListener(this); // 'this' sets up the context for this method as the current class. without 'this'
        // the method will have no idea where the onlcick listener is implemented.


        /* This is The Old Method implementation, Before code clean up and the Implements method was introduced */
       /* passTog.setOnClickListener(new View.OnClickListener() {
            @Override
            //Each time the Tog button is clicked his code here will run.
            public void onClick(View view) {
                if (passTog.isChecked()) {//show password stars
                    input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {//show real text
                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                }
            }
        }); */

        chkCommand.setOnClickListener(this); // 'this' sets up the context for this method as the current class.


        /* This is the old method implementation, this was replaced by implements method to make code cleaner, leaner. */
       /* chkCommand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String check = input.getText().toString(); // convert to a string for further manipulation
                display.setText(check);

                changing the layout of in the xml using java code

                if (check.contains("left")){
                    display.setGravity(Gravity.LEFT);
                }else if (check.contains("center")){
                    display.setGravity(Gravity.CENTER);
                }else if (check.contains("right")){
                    display.setGravity(Gravity.RIGHT);
                }else if (check.contains("blue")){
                    display.setTextColor(Color.BLUE);
                }else if (check.contains("DMSO")){
                    Random crazy = new Random(); // playing with random numbers
                    display.setText("DMSO!!!");
                    display.setTextSize(crazy.nextInt(75));
                    display.setTextColor(Color.rgb(crazy.nextInt(265),crazy.nextInt(265),crazy.nextInt(265))); // using random colors

                    // doing swich case contract
                    switch (crazy.nextInt(3)){
                        case 0:
                            display.setGravity(Gravity.LEFT);
                            break;
                        case 1:
                            display.setGravity(Gravity.CENTER);
                            break;
                        case 2:
                            display.setGravity(Gravity.RIGHT);
                            break;
                    }
                }else {
                    display.setText("invalid");
                    display.setGravity(Gravity.CENTER);
                    display.setTextColor(Color.BLACK);
                }
            }
        }); */
    }

    private void setUpVariables() {
        chkCommand = findViewById(R.id.bresults);
        passTog    = findViewById(R.id.tbPassword);
        input      = findViewById(R.id.etCommand);
        display    = findViewById(R.id.tvResults);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) { // the view ID to identify which button was pressed then do the desired action
            case R.id.bresults:
                String check = input.getText().toString(); // convert to a string for further manipulation
                display.setText(check);

                /*changing the layout of in the xml using java code*/

                if (check.contains("left")){
                    display.setGravity(Gravity.LEFT);
                }else if (check.contains("center")){
                    display.setGravity(Gravity.CENTER);
                }else if (check.contains("right")){
                    display.setGravity(Gravity.RIGHT);
                }else if (check.contains("blue")){
                    display.setTextColor(Color.BLUE);
                }else if (check.contains("DMSO")){
                    Random crazy = new Random(); // playing with random numbers
                    display.setText("DMSO!!!");
                    display.setTextSize(crazy.nextInt(75));
                    display.setTextColor(Color.rgb(crazy.nextInt(265),crazy.nextInt(265),crazy.nextInt(265))); // using random colors

                    // doing swich case contract
                    switch (crazy.nextInt(3)){
                        case 0:
                            display.setGravity(Gravity.LEFT);
                            break;
                        case 1:
                            display.setGravity(Gravity.CENTER);
                            break;
                        case 2:
                            display.setGravity(Gravity.RIGHT);
                            break;
                    }
                }else {
                    display.setText("invalid");
                    display.setGravity(Gravity.CENTER);
                    display.setTextColor(Color.BLACK);
                }
            case R.id.tbPassword:
                if (passTog.isChecked()) {//show password stars
                    input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {//show real text
                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                }

        }
    }
}
