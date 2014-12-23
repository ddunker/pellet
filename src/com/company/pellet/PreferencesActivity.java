package com.company.pellet;


import android.app.Activity;
import android.os.Bundle;

public class PreferencesActivity extends Activity {

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        setContentView(R.layout.preferences);

        Colorize colorize = new Colorize();
        colorize.colorPreferences(getWindow());
    }
}
