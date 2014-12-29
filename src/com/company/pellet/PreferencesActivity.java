package com.company.pellet;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

public class PreferencesActivity extends Activity {

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        setContentView(R.layout.preferences);

        Colorize colorize = new Colorize();
        colorize.colorPreferences(getWindow());

        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // обработка нажатий
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Операции для выбранного пункта меню
        switch (item.getItemId()) {
            case android.R.id.home: {
                onBackPressed();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
