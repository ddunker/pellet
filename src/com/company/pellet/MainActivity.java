package com.company.pellet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
    EditText product;
    EditText wrapping;
    EditText from;
    EditText to;
    EditText distance;
    EditText oneKmCost;
    EditText weight;
    EditText buy;
    EditText margin;
    EditText exp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        product = (EditText) findViewById(R.id.productEditText);
        wrapping = (EditText) findViewById(R.id.wrappingEditText);
        from = (EditText) findViewById(R.id.fromEditText);
        to = (EditText) findViewById(R.id.toEditText);
        distance = (EditText) findViewById(R.id.distanceEditText);
        oneKmCost = (EditText) findViewById(R.id.oneKmEditText);
        weight = (EditText) findViewById(R.id.weightEditText);
        buy = (EditText) findViewById(R.id.buyEditText);
        margin = (EditText) findViewById(R.id.marginEditText);
        exp = (EditText) findViewById(R.id.expEditText);
    }
    public void onClick(View view) {
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

    // menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // обработка нажатий
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Операции для выбранного пункта меню
        switch (item.getItemId()) {
            case R.id.searchItem:
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
                DataBase db = new DataBase(getApplicationContext());
                db.getList();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

