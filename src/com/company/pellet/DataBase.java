package com.company.pellet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by binkovskiy on 10.12.14.
 */
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
        // TODO Auto-generated constructor stub
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

        Log.w("LOG_TAG", "Обновление базы данных с версии " + oldVersion
                + " до версии " + newVersion + ", которое удалит все старые данные");
        // Удаляем предыдущую таблицу при апгрейде
        db.execSQL(SQL_DELETE_ENTRIES);
        // Создаём новый экземпляр таблицы
        onCreate(db);
    }

}
