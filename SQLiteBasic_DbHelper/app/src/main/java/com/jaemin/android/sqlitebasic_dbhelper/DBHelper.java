package com.jaemin.android.sqlitebasic_dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jaemin on 2016. 10. 12..
 */

public class DBHelper extends SQLiteOpenHelper{

    public static final String DB_NAME = "sqlite.db";
    public static final int DB_VERSION = 3;


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
// 1.
//        String schema = "create table bbs4(no integer primary key autoincrement not null"
//                + ", title text not null)";
//        sqLiteDatabase.execSQL(schema);
//
//        for(int i=0; i<100; i++) {
//            String query = "insert into bbs4(title) values('title"+i+"')";
//            sqLiteDatabase.execSQL(query);
//        }

        // 2.
        // 스키마 버전 3으로 변경 - 2016.10.12
        String query = "delete from bbs4";
        sqLiteDatabase.execSQL(query);;
        for(int i=0; i<100; i++) {
            query = "insert into bbs4(title) values('everything"+i+"')";
            sqLiteDatabase.execSQL(query);
        }

        // 3.
        // assets에 있는 파일을 복사해서 디스크로 옮긴다
    }

    // DB_VERSION이 높아질 때만 호출
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
// 1.
//        String query = "delete from bbs4";
//        sqLiteDatabase.execSQL(query);;
//        for(int i=0; i<100; i++) {
//            query = "insert into bbs4(title) values('everything"+i+"')";
//            sqLiteDatabase.execSQL(query);
//        }

        // 2.
        onCreate(sqLiteDatabase);

        // 3.
        // 변경사항이 있으면 디스크에 있는 db에 덮어쓰기 한다
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
