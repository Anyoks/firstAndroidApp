package com.example.orinamokaya.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by orinamokaya on 1/5/18.
 */

public class SimpleBrowser extends Activity implements View.OnClickListener {

    EditText url;
    WebView ourBrowser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.simplebrowser);


        ourBrowser = (WebView) findViewById(R.id.wvBrowser);

        //setting browwser settings
        ourBrowser.getSettings().setJavaScriptEnabled(true); // enable javascript
        ourBrowser.getSettings().setLoadWithOverviewMode(true); //zoom out of the page so you can see the whole page
        ourBrowser.getSettings().setUseWideViewPort(true); //desktop version wesite

        ourBrowser.setWebViewClient(new WebViewClient()); // if you don't set this, the url will be opened in a browser

        //just incase the url does not load... catch the error
        try {
            ourBrowser.loadUrl("http://www.miraclemanner.org"); //default page
        } catch (Exception e) {
            e.printStackTrace();
        }

        //setting up  the vars
        // setting up references to view
        Button go = (Button) findViewById(R.id.bGo);
        Button back = (Button) findViewById(R.id.bBack);
        Button refresh = (Button) findViewById(R.id.bRefresh);
        Button forward  = (Button) findViewById(R.id.bForward);
        Button clearHistory  = (Button) findViewById(R.id.bClearHistory);
        url = (EditText) findViewById(R.id.etUrl);

        //setting up onclick listeners
        go.setOnClickListener(this);
        back.setOnClickListener(this);
        refresh.setOnClickListener(this);
        forward.setOnClickListener(this);
        clearHistory.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.bGo:
                String theLink = url.getText().toString();
                ourBrowser.loadUrl(theLink);

                //hide keyboard after clicking go
                // first set up an input method manager
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(url.getWindowToken(),0); // this hides the keyboard
                break;
            case R.id.bBack:
                // if there's no go back link...we must check
                if (ourBrowser.canGoBack())
                ourBrowser.goBack();

                break;
            case R.id.bRefresh:

                ourBrowser.reload();
                break;
            case R.id.bForward:

                if (ourBrowser.canGoForward())
                    ourBrowser.goForward();
                break;
            case R.id.bClearHistory:

                ourBrowser.clearHistory();
                break;

        }
    }
}
