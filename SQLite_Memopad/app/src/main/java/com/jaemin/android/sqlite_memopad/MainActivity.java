package com.jaemin.android.sqlite_memopad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jaemin.android.sqlite_memopad.com.jaemin.android.sqlite_memopad.domain.Memo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbHelper = new DBHelper(this);
        Memo memo = new Memo();
        memo.contents = "메모 내용 ";
        memo.ndate = dbHelper.getTimeStamp();

        dbHelper.dbInsert(memo);
        Log.i("Memo", "Db ===== "+dbHelper);


        ArrayList<Memo> datas = dbHelper.dbSelectAll();
        for (Memo data : datas) {
            Log.i("Memo", "no ===== " + data.no);
            Log.i("Memo", "contents ===== " + data.contents);
            Log.i("Memo", "ndate ===== " + data.ndate);
        }
    }
}
