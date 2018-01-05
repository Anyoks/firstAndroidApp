package com.example.orinamokaya.myapplication;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;

import static com.example.orinamokaya.myapplication.R.xml.prefs;

/**
 * Created by orinamokaya on 1/1/18.
 */

public class Prefs extends PreferenceActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(prefs);

    }
}
