package com.company.pellet;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class SearchActivity extends Activity {
    private ListView listView;
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        setContentView(R.layout.search);

        listView = (ListView) findViewById(R.id.listView);
        loadSpinnerData();
    }

    private void loadSpinnerData() {
        // database handler
        DataBase db = new DataBase(getApplicationContext());

        List<String> lables = db.getList();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lables);





        // attaching data adapter to spinner
        listView.setAdapter(dataAdapter);
    }
}
