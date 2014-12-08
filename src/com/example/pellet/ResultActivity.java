package com.example.pellet;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by binkovskiy on 05.12.14.
 */
public class ResultActivity extends Activity {
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        setContentView(R.layout.result);
    }
}

