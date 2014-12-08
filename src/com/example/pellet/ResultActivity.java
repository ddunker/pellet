package com.example.pellet;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by binkovskiy on 05.12.14.
 */
public class ResultActivity extends Activity {
    String txt;
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        setContentView(R.layout.result);

        TextView text = (TextView)findViewById(R.id.prodTextView);
        txt = getIntent().getStringExtra("product").concat(" (").concat(getIntent().getStringExtra("wrapping")).concat(")");
        text.setText(txt);
    }
}

