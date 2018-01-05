package com.example.orinamokaya.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by orinamokaya on 10/25/17.
 */

public class OpenedClass extends Activity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener{

    TextView question, text;
    RadioGroup selectionList;
    Button returnData;
    String gotBread, setData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.send); // set the content view
        setUpVars();

        //Lets get user set preferences and use them in the question variable.

        SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getBaseContext()); // get the shared preferences

        // Now lets get what the user entered in the Edit text preference and use it in the question variable
        String et = getData.getString("name", "Dennis Is ..." ); // get the string in the key of Edit Text preference. The default value is 'Dennis is...' see prefs.xml
        String values = getData.getString("list", "4") ; // get the values for the strings displayed. the key for the preference is 'list' the values are store in the array.xml and mapped to their corresponding strings in the prefs.xml

        //now lets set the question content based on the user's options
        if (values.contentEquals("1")){
            question.setText(et);
        }

//        Bundle gotBasket = getIntent().getExtras(); // a bundle sent the data, so a bundle must receive the data, from the intent into which the data was put
//        gotBread = gotBasket.getString("bread"); // fetch the string from thr basket.
//        question.setText(gotBread); // set the new string value to the text view variable which we brought from data class

    }

    private void setUpVars() {

        question = findViewById(R.id.tvQuestion);
        text = findViewById(R.id.tvText);
        returnData = findViewById(R.id.bReturn);
        selectionList = findViewById(R.id.rgAnswers);

        returnData.setOnClickListener(this); // set up the button's onclick listener
        selectionList.setOnCheckedChangeListener(this); // set up the radio group
    }

    @Override
    public void onClick(View view) {
        //when the return button is clicked, the data from the radio buttons will be returned to the data class.
        // anytime you want to pass data to another class you need an start an intent, put the data in a bundle in a key value format,
        // put the bundle in the intent, then send it.
        Intent person = new Intent();
        Bundle backpack = new Bundle();
        backpack.putString("answer", setData); //put the bundle in the intent.
        person.putExtras(backpack); // put bundle in the intent
        // Now lets give the data to the next class
        setResult(RESULT_OK, person); // since this intent has been started for result, we need to set the result as done here.
        finish(); // close the app class.

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        switch (i) {
            case R.id.rHumble:
                setData = "Probably right!";
                break;
            case R.id.rKind:
                setData = "Definitely right!";
                break;
            case R.id.rBoth:
                setData = "Spot on!";
                break;
        }
        text.setText(setData); // change the text view according to the radio selection so we can see what's happening.
    }
}
