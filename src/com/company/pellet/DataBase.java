package com.company.pellet;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.*;

public class DataBase extends SQLiteOpenHelper{

    // константы для конструктора
    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "calc_table";
    public static final String UID = "_id";
    public static final String DATE = "date";
    public static final String PRODUCT_NAME = "product_name";
    public static final String WRAPPING = "wrapping";
    public static final String FR = "fr";
    public static final String DESTINATION = "destination";
    public static final String DISTANCE = "distance";
    public static final String ONE_KM_COST = "one_km_cost";
    public static final String WEIGHT = "weight";
    public static final String BUY_PRICE = "buy_price";
    public static final String MARGIN = "margin";
    public static final String EXPENSES = "expenses";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE "
            + TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DATE + " DATETIME, "
            + PRODUCT_NAME + " VARCHAR(255), "
            + WRAPPING + " VARCHAR(255), "
            + FR + " VARCHAR(255), "
            + DESTINATION + " VARCHAR(255), "
            + DISTANCE + " VARCHAR(255), "
            + ONE_KM_COST + " VARCHAR(255), "
            + WEIGHT + " VARCHAR(255), "
            + BUY_PRICE + " VARCHAR(255), "
            + MARGIN + " VARCHAR(255), "
            + EXPENSES + " VARCHAR(255));";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    private static final String selectQuery = "SELECT "
            + UID + ", "
            + DATE + ", "
            + PRODUCT_NAME + ", "
            + WRAPPING + ", "
            + FR + ", "
            + DESTINATION + ", "
            + DISTANCE + ", "
            + ONE_KM_COST + ", "
            + WEIGHT + ", "
            + BUY_PRICE + ", "
            + MARGIN + ", "
            + EXPENSES
            + " FROM " + TABLE_NAME;

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("LOG_TAG", "Обновление базы данных с версии " + oldVersion
                + " до версии " + newVersion + ", которое удалит все старые данные");
        // Удаляем предыдущую таблицу при апгрейде
        db.execSQL("DROP TABLE IF EXISTS " + SQL_DELETE_ENTRIES);
        // Создаём новый экземпляр таблицы
        onCreate(db);
    }

    public void insertRow(String product, String wrapping, String from, String to, String distance, String oneKmCost,
                          String weight, String buyPrice, String margin, String expanses) {
        SQLiteDatabase db = this.getWritableDatabase();

        String insertQuery = "INSERT INTO " +
                TABLE_NAME +
                " (" + DATE + ", "
                + PRODUCT_NAME + ", "
                + WRAPPING + ", "
                + FR + ", "
                + DESTINATION + ", "
                + DISTANCE + ", "
                + ONE_KM_COST + ", "
                + WEIGHT + ", "
                + BUY_PRICE + ", "
                + MARGIN + ", "
                + EXPENSES
                + ") VALUES ('"
                + getDateTime() + "', '"
                + product + "', '"
                + wrapping + "', '"
                + from + "', '"
                + to + "', '"
                + distance + "', '"
                + oneKmCost + "', '"
                + weight + "', '"
                + buyPrice + "', '"
                + margin + "', '"
                + expanses
                + "');";
        db.execSQL(insertQuery);
        db.close();
    }

    public ArrayList<Products> getList() {
        ArrayList<Products> products = new ArrayList<Products>();
        String q = selectQuery + " ORDER BY " + UID + " DESC;";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(q, null);
        if (cursor.moveToFirst()) {
            do {
                String date = cursor.getString(1);
                String product = cursor.getString(2);
                String path = cursor.getString(4) + " - " + cursor.getString(5);
                String id = cursor.getString(0);
                products.add(new Products(date, product, path, id));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return products;
    }

    public ArrayList<SelectedProduct> selectProduct(String selectedId) {
        ArrayList<SelectedProduct> selectedProduct = new ArrayList<SelectedProduct>();
        String q = selectQuery + " WHERE " + UID + " = " + selectedId + ";";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(q, null);
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(0);
                String date = cursor.getString(1);
                String productName = cursor.getString(2);
                String wrapping = cursor.getString(3);
                String fr = cursor.getString(4);
                String destination = cursor.getString(5);
                String distance = cursor.getString(6);
                String oneKmCost = cursor.getString(7);
                String weight = cursor.getString(8);
                String buyPrice = cursor.getString(9);
                String margin = cursor.getString(10);
                String expenses = cursor.getString(11);

                selectedProduct.add(new SelectedProduct(id, date, productName, wrapping, fr, destination, distance,
                        oneKmCost, weight, buyPrice, margin, expenses));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return selectedProduct;
    }

    public void deleteItem(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String q = "DELETE FROM " + TABLE_NAME + " WHERE " + UID + " = " + id + ";";
        db.execSQL(q);
        db.close();
    }

    public String getLastId() {
        String lastId = null;
        String q = "SELECT MAX(" + UID + ") FROM " + TABLE_NAME + ";";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(q, null);
        if (cursor.moveToFirst()) {
            do {
                lastId = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lastId;
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

}
