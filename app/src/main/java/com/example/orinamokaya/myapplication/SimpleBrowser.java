package com.example.orinamokaya.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

/**
 * Created by orinamokaya on 1/5/18.
 */

public class SimpleBrowser extends Activity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.simplebrowser);

        //setting up  the vars
        WebView ourBrowser= (WebView) findViewById(R.id.wvBrowser);
        ourBrowser.loadUrl("http://www.google.com"); //default page
    }
}
