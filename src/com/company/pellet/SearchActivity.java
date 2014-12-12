package com.company.pellet;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchActivity extends Activity {

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        setContentView(R.layout.search);

        loadData();
    }

    private void loadData() {
        DataBase db = new DataBase(getApplicationContext());
        ArrayList<String> itemsList = (ArrayList<String>) db.getList();
        ListView listItems = (ListView) findViewById(R.id.listItems);
        ListAdapter listAdapter = new ListAdapter(getApplicationContext(), itemsList);
        listItems.setAdapter(listAdapter);
        listItems.setOnItemClickListener(selectItem);
    }

    private AdapterView.OnItemClickListener selectItem = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View v, int i, long l) {
            Log.v("###########", "t" + i + " " + l+ " " + ((TextView) v.findViewById(R.id.list_name)).getText());
        }
    };


}
