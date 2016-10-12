package com.jaemin.android.sqlitebasic_dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jaemin on 2016. 10. 12..
 */

public class DBHelper extends SQLiteOpenHelper{

    public static final String DB_NAME = "sqlite.db";
    public static final int DB_VERSION = 1;


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String schema = "create table bbs4(no integer primary key autoincrement not null"
                + ", title text not null)";
        sqLiteDatabase.execSQL(schema);

        for(int i=0; i<100; i++) {
            String query = "insert into bbs4(title) values('title"+i+"')";
            sqLiteDatabase.execSQL(query);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    private static DBHelper dbHelper = null;

    private DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static SQLiteDatabase openDatabase(Context context) {
        if(dbHelper == null) {
            dbHelper = new DBHelper(context);
        }
        return dbHelper.getWritableDatabase();
    }
}
