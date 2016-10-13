package com.jaemin.android.sqlite_memopad;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.jaemin.android.sqlite_memopad.com.jaemin.android.sqlite_memopad.domain.Memo;

import java.util.ArrayList;

/**
 * Created by Jaemin on 2016. 10. 13..
 */

public class DBHelper extends SQLiteOpenHelper {

    private final static String DB_NAME = "memo.sqlite";
    private final static int DB_VERSION = 2;

    // 쿼리를 만들어 준다
    // CRRUD(C: insert, R:select one, R:select all, U:update, D:delete)
    public void dbInsert(Memo memo) {
        // DB를 연결
        SQLiteDatabase db = getWritableDatabase();

        // insert하기 위한 query 생성
        String query = " insert into memo(contents, ndate)" +
                " values('"+memo.contents+"','"+memo.ndate+"')";

        db.execSQL(query);

        // DB 사용 후 닫는다
        db.close();
    }

    // select는 table의 key를 기준으로 값을 받는다
    public Memo dbSelectOne(int no) {
        Memo memo = new Memo(); // 리턴 타입에 맞게 객체를 생성해준다

        //TODO 처리 후

        return memo; // 정의된 리턴 타입의 변수를 넘겨준다
    }

    public ArrayList<Memo> dbSelectAll() {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        ArrayList<Memo> datas = new ArrayList<>();

        // 1. DB를 읽기 모드로 open
        db = getReadableDatabase();

        // 2. select 쿼리 작성
        String query = " select * from memo";

        // 3. 쿼리를 실행해서 cursor에 담고
        cursor = db.rawQuery(query, null);

        // 4. 커서에 담긴 데이터를 while문을 돌면서 꺼내고
        while(cursor.moveToNext()) {
            // 5.1 한줄씩 데이터 단위로 cursor에서 꺼내와 담아준다
            Memo memo = new Memo();

            int idx = cursor.getColumnIndex("no");
            memo.no = cursor.getInt(idx);

            idx = cursor.getColumnIndex("contents");
            memo.contents = cursor.getString(idx);

            idx = cursor.getColumnIndex("ndate");
            memo.ndate = cursor.getLong(idx);

            idx = cursor.getColumnIndex("image");
            memo.image = cursor.getString(idx);

            // 5.2 datas.add(메모데이터)
            datas.add(memo);
        }


        cursor.close();
        db.close();

        return datas;
    }

    public void dbUpdate(Memo memo) {

    }


    public void dbDelete(Memo memo) {

    }





    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        // DB version 1 - 최초등록 2016/10/13 11:30
//        // primary key 다음에 autoincrement를 써야한다 (순서 바뀌면 안됨) , ndate가 integer인 이유는 자바에서 넣어줄거라서!!
//        String schema_01 = "create table memo(no integer primary key autoincrement not null" +
//                ", contents text not null" +
//                ", ndate integer not null)";
//
//        // 실행해서 table을 생성한다
//        sqLiteDatabase.execSQL(schema_01);


        // DB version2 - upgrade 2016/10/13 14:08
        // 이미지 추가
        String schema_02 = "create table memo(no integer primary key autoincrement not null" +
                ", contents text not null" +
                ", image text" +
                ", ndate integer not null)";
        sqLiteDatabase.execSQL(schema_02);

        // 백업된 데이터 복원
        for (Memo memo : backupDatas) {
            String query = " insert into memo(contents, ndate)" +
                    " values('"+memo.contents+"', '"+memo.ndate+"')";
            sqLiteDatabase.execSQL(query);
        }

    }

    ArrayList<Memo> backupDatas = new ArrayList<>();

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        // 이전 데이터베이스 버전에 따라 처리 방식이 달라진다
        if (oldVersion == 1 ) {
            // 컬럼이 3개
            // 디비는 현재 열려져있는 상태 ?-?

            String query = " select no, contents, ndate from memo order by no ";
            Cursor cursor = sqLiteDatabase.rawQuery(query, null);

            while (cursor.moveToNext()) {
                Memo memo = new Memo();

                memo.no = cursor.getInt(0);
                memo.contents = cursor.getString(1);
                memo.ndate = cursor.getLong(2);

                backupDatas.add(memo);
            }
        }

        // DB version2 - upgrade 2016/10/13 14:08
        String schema_01_drop = "drop table memo";
        sqLiteDatabase.execSQL(schema_01_drop);

        onCreate(sqLiteDatabase);
    }

    // 현재 시간 가져오기
    public long getTimeStamp() {
        return System.currentTimeMillis();
    }
}
