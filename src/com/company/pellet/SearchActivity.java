package com.company.pellet;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by binkovskiy on 10.12.14.
 */
public class SearchActivity extends Activity {
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        setContentView(R.layout.search);
    }
}
