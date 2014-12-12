package com.company.pellet;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchActivity extends Activity {

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        setContentView(R.layout.search);

        loadData();
    }

    private void loadData() {
        DataBase db = new DataBase(getApplicationContext());
        Map<String, ArrayList<String>> itemsList = (HashMap<String, ArrayList<String>>) db.getList();
        ListView list = (ListView) findViewById(R.id.listItems);
        ListAdapter listAdapter = new ListAdapter(getApplicationContext(), itemsList);
        list.setAdapter(listAdapter);
        list.setOnItemClickListener(selectItem);
    }

    private AdapterView.OnItemClickListener selectItem = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View v, int i, long l) {
            Log.v("###########", "t" + i + " " + l+ " " + ((TextView) v.findViewById(R.id.item1)).getText());
        }
    };


}
