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

        Intent intent = new Intent(MainActivity.this, ResultActivity.class);

        intent.putExtra("product", product.getText().toString());
        intent.putExtra("wrapping", wrapping.getText().toString());
        startActivity(intent);

    }
}

