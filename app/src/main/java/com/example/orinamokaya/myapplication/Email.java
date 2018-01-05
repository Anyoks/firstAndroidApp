package com.example.orinamokaya.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by orinamokaya on 7/30/17.
 */

public class Email extends Activity implements View.OnClickListener {

    Button sendEmail;
    EditText email, intro, personName, things, action, out; // these will collect data from the user, we must feed these to the app smhow
    String addEmail,begin, name, toDo, forYou, getOut; // some pretty names for later string concatenation


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.email); // set the view
        setUpVars(); // set up variables

        sendEmail.setOnClickListener(this);
    }

    private void getData() {
        // This method changes the data from in put to a string for easy manipulation
        addEmail = email.getText().toString();
        begin = intro.getText().toString();
        name = personName.getText().toString();
        toDo = things.getText().toString();
        forYou = action.getText().toString();
        getOut = out.getText().toString();
    }

    private void setUpVars() {
        // This method assigns the variables for easy use within the app
        sendEmail = findViewById(R.id.btnSendEmail);
        email = findViewById(R.id.etEmails);
        intro = findViewById(R.id.etIntro);
        personName = findViewById(R.id.etName);
        things = findViewById(R.id.etThings);
        action = findViewById(R.id.etAction);
        out = findViewById(R.id.etOutro);

    }

    @Override
    public void onClick(View view) {

        getData(); // You can only get data after user has clicked.

        String emailAddresses[] = {addEmail};

        String message = "well, Hello "
                        + name
                        + "I wanted to say "
                        + begin
                        +". Not Only that, but I love it when you "
                        + forYou
                        + ", that just makes me go crazy!! If only  could just "
                        + toDo
                        +". Oh well, that's all I had to say today, talk later "
                        + getOut
                        +". Oh, when you get bored just call me, we'll find something to do."
                        +'\n' + "PS. God bless you... :-)" ;


        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, emailAddresses);
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"I Love the Truth");
        emailIntent.setType("plain/text");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
        startActivity(emailIntent);

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
