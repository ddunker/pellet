package com.example.pellet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    public void onClick(View view) {
        EditText product = (EditText) findViewById(R.id.productEditText);
        EditText wrapping = (EditText) findViewById(R.id.wrappingEditText);
        EditText from = (EditText) findViewById(R.id.fromEditText);
        EditText to = (EditText) findViewById(R.id.toEditText);
        EditText distance = (EditText) findViewById(R.id.distanceEditText);
        EditText oneKmCost = (EditText) findViewById(R.id.oneKmEditText);
        EditText weight = (EditText) findViewById(R.id.weightEditText);
        EditText buy = (EditText) findViewById(R.id.buyEditText);
        EditText margin = (EditText) findViewById(R.id.marginEditText);
        EditText exp = (EditText) findViewById(R.id.expEditText);

        Intent intent = new Intent(MainActivity.this, ResultActivity.class);

        intent.putExtra("product", product.getText().toString());
        intent.putExtra("wrapping", wrapping.getText().toString());
        intent.putExtra("from", from.getText().toString());
        intent.putExtra("to", to.getText().toString());
        intent.putExtra("distance", Float.valueOf(distance.getText().toString()));
        intent.putExtra("oneKmCost", Float.valueOf(oneKmCost.getText().toString()));
        intent.putExtra("weight", Float.valueOf(weight.getText().toString()));
        intent.putExtra("buy", Float.valueOf(buy.getText().toString()));
        intent.putExtra("margin", Integer.valueOf(margin.getText().toString()));
        intent.putExtra("exp", Float.valueOf(exp.getText().toString()));

        startActivity(intent);
    }
}

