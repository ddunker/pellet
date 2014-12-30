package com.company.pellet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchActivity extends Activity {

    private ArrayList<Products> products;
    private AlertDialog.Builder ad;
    private int item_id;
    private ListAdapter listAdapter;
    private ListView lvMain;

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        setContentView(R.layout.search);

        loadData();
        listAdapter = new ListAdapter(this, products);
        lvMain = (ListView) findViewById(R.id.listItems);
        lvMain.setAdapter(listAdapter);

        Colorize colorize = new Colorize();
        colorize.colorSearch(getWindow());

        lvMain.setOnItemClickListener(selectItem);
        lvMain.setOnItemLongClickListener(deleteItem);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        final Context context = SearchActivity.this;
        String title = getResources().getString(R.string.title);
        String message = getResources().getString(R.string.message);
        String button1String = getResources().getString(R.string.yes_button);
        String button2String = getResources().getString(R.string.no_button);

        ad = new AlertDialog.Builder(context);
        ad.setTitle(title);  // заголовок
        ad.setMessage(message); // сообщение
        ad.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                DataBase db = new DataBase(getApplicationContext());
                db.deleteItem(item_id);


                Toast.makeText(context, "Deleted", Toast.LENGTH_LONG).show();
            }
        });
        ad.setNegativeButton(button2String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
            }
        });
        ad.setCancelable(true);
        ad.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
            }
        });
    }

    // обработка нажатий
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Операции для выбранного пункта меню
        switch (item.getItemId()) {
            case android.R.id.home: {
                onBackPressed();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // данные для адаптера
    private void loadData() {
        DataBase db = new DataBase(getApplicationContext());
        products = db.getList();
    }

    AdapterView.OnItemClickListener selectItem = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.v("########", " position: " + position + " item_id: " + ((TextView) view.findViewById(R.id.tvId)).getText());
            Intent intent = new Intent();
            intent.putExtra("item_id", ((TextView) view.findViewById(R.id.tvId)).getText());
            setResult(RESULT_OK, intent);
            finish();
        }
    };

    AdapterView.OnItemLongClickListener deleteItem = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            Toast toast = Toast.makeText(getApplicationContext(), ((TextView) view.findViewById(R.id.tvId)).getText(),
                    Toast.LENGTH_LONG);
            toast.show();
            item_id = Integer.valueOf((((TextView) view.findViewById(R.id.tvId)).getText()).toString());
            DataBase db = new DataBase(getApplicationContext());
            db.deleteItem(item_id);
//            ad.show();
            listAdapter.notifyDataSetChanged();
            return true;
        }
    };
}
