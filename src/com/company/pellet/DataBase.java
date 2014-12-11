package com.company.pellet;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.SimpleCursorAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "
            + TABLE_NAME + ";";

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

//    public List<String> getList() {
//        List<String> labels = new ArrayList<String>();
//
//        String selectQuery = "SELECT "
//                + UID + ", "
//                + DATE + ", "
//                + PRODUCT_NAME + ", "
//                + WRAPPING + ", "
//                + FR + ", "
//                + DESTINATION + ", "
//                + DISTANCE + ", "
//                + ONE_KM_COST + ", "
//                + WEIGHT + ", "
//                + BUY_PRICE + ", "
//                + MARGIN + ", "
//                + EXPENSES
//                + " FROM " + TABLE_NAME + ";";
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        while (cursor.moveToNext()) {
//            int id = cursor.getInt(cursor.getColumnIndex(DataBase.UID));
//            String name = cursor.getString(cursor.getColumnIndex(DataBase.PRODUCT_NAME));
//            Log.i("LOG_TAG", "ROW " + id + " HAS NAME " + name);
//        }
//
////        // looping through all rows and adding to list
////        if (cursor.moveToFirst()) {
////            do {
////                labels.add(cursor.getString(1));
////            } while (cursor.moveToNext());
////        }
//
//        // closing connection
//        cursor.close();
//        db.close();
//
//        // returning lables
//        return labels;
//    }

    public List<String> getList() {
        List<String> labels = new ArrayList<String>();

        String selectQuery = "SELECT "
                + UID + ", "
                + DATE + ", "
                + PRODUCT_NAME + ", "
                + WRAPPING + ", "
                + FR + ", "
                + DESTINATION
                + " FROM " + TABLE_NAME
                + " ORDER BY " + DATE + " DESC;";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

//        while (cursor.moveToNext()) {
//            int id = cursor.getInt(cursor.getColumnIndex(DataBase.UID));
//            String name = cursor.getString(cursor.getColumnIndex(DataBase.PRODUCT_NAME));
//            Log.i("LOG_TAG", "ROW " + id + " HAS NAME " + name);
//        }

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1) + "\n" + cursor.getString(2) + "\n" + cursor.getString(3) +
                        " - " + cursor.getString(4));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        String[] from = new String[] {DATE, PRODUCT_NAME };
        int[] to = new int[] { R.id.dateView, R.id.prodView };

        SimpleCursorAdapter mCursorAd = new SimpleCursorAdapter(SearchActivity, R.layout.item, getAllItems(), from, to, 0);

        return labels;
    }

    public Cursor getAllItems() {
        SQLiteDatabase mDb = this.getReadableDatabase();
        return mDb.query(TABLE_NAME, null, null, null, null, null, null);
    }
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

}
