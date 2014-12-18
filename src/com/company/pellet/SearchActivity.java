package com.company.pellet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchActivity extends Activity {

    private ArrayList<Products> products;

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        setContentView(R.layout.search);

        loadData();
        ListAdapter listAdapter = new ListAdapter(this, products);
        ListView lvMain = (ListView) findViewById(R.id.listItems);
        lvMain.setAdapter(listAdapter);

        lvMain.setOnItemClickListener(selectItem);
    }

    // данные для адаптера
    private void loadData() {
        DataBase db = new DataBase(getApplicationContext());
        products = db.getList();
    }

    AdapterView.OnItemClickListener selectItem = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.v("########", " position: " + position + " id: " + ((TextView) view.findViewById(R.id.tvId)).getText());
            Intent intent = new Intent();
            intent.putExtra("id", ((TextView) view.findViewById(R.id.tvId)).getText());
            setResult(RESULT_OK, intent);
            finish();
        }
    };
}
