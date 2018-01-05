package com.example.orinamokaya.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by orinamokaya on 10/25/17.
 */

public class Data extends Activity implements View.OnClickListener {

    Button start, startFor;
    EditText sendET;
    TextView gotAnswer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.get1); // set the view
        setUpVars(); // set up the variables
    }

    private void setUpVars() {
        start = findViewById(R.id.bSA);
        startFor = findViewById(R.id.bSAFR);
        sendET = findViewById(R.id.etSend);
        gotAnswer = findViewById(R.id.tvGot);

        // set up the button onclick listeners
        start.setOnClickListener(this);
        startFor.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.bSA:
                // now, for us to be able to send this data to the openedclass class we do the following
                String bread = sendET.getText().toString(); // put the data in to a string var
                Bundle basket = new Bundle(); // create a bundle, a bundle is what enables us to move data to another class

                // put the bread in the basket--- put the data into the bundle.
                basket.putString("bread", bread);  // a basket is like a key -> value hash in ruby

                // start up an intent
                Intent a = new Intent(Data.this, OpenedClass.class); // what class is the data coming from [Data] and going to [OpenedClass]
                a.putExtras(basket); // set up the bundle within the new intent.
                startActivity(a); // data sent!!

                break;
            case R.id.bSAFR:

                Intent i = new Intent(Data.this, OpenedClass.class);
                startActivityForResult(i,0); // when you use this you must set up the onActivityResult method

                break;
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // this is where the data is received.
        if (resultCode == RESULT_OK){
            Bundle basket = data.getExtras(); // A bundle is used to send data, a Bundle is used to receive data.
            String s = basket.getString("answer"); // get the string that is in the bundle using the key
            gotAnswer.setText(s); // set the received answer to the text view
        }
    }
}
