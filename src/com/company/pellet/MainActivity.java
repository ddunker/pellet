package com.company.pellet;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    SQLiteDatabase sqdb;
    DataBase sqh;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Инициализируем наш класс-обёртку
        sqh = new DataBase(this);

        // База нам нужна для записи и чтения
        sqdb = sqh.getWritableDatabase();
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
                String qu = "SELECT "
                        + DataBase.UID + ", "
                        + DataBase.DATE + ", "
                        + DataBase.PRODUCT_NAME + ", "
                        + DataBase.WRAPPING + ", "
                        + DataBase.FR + ", "
                        + DataBase.DESTINATION + ", "
                        + DataBase.DISTANCE + ", "
                        + DataBase.ONE_KM_COST + ", "
                        + DataBase.WEIGHT + ", "
                        + DataBase.BUY_PRICE + ", "
                        + DataBase.MARGIN + ", "
                        + DataBase.EXPENSES
                        + " FROM " + DataBase.TABLE_NAME + ";";

                Cursor cursor = sqdb.rawQuery(qu, null);
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(cursor
                            .getColumnIndex(DataBase.UID));
                    String name = cursor.getString(cursor
                            .getColumnIndex(DataBase.PRODUCT_NAME));
                    Log.i("LOG_TAG", "ROW " + id + " HAS NAME " + name);
                }
                cursor.close();
                Log.w("LOG_TAG", "ok!!!");

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

