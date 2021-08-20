package com.antislot.antislot.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class sleepTimeDB extends SQLiteOpenHelper {


    public static final String DBNAME = "Inventory";
    private static final int DBVersion = 1 ;

    public sleepTimeDB(Context context) {
        super(context, DBNAME, null, DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
