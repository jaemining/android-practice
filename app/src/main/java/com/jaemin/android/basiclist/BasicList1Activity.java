package com.jaemin.android.basiclist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BasicList1Activity extends AppCompatActivity {

    String data[] = {"백향목", "김동진", "김태원", "임재민", "김도형", "석주영", "장홍석", "김해든"};

    ArrayAdapter<String> adapter;// 데이터를 담을 객체
    ListView listView;// 데이터를 담은 adqpter를 받는 리스트

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_list1);

        //                               1.컨텍스트         2. 아이템 레이아웃          3. 데이터
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

    }
}
