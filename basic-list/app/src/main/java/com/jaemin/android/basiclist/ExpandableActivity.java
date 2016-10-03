package com.jaemin.android.basiclist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;

public class ExpandableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable);

        ExpandableListView listView = (ExpandableListView) findViewById(R.id.expandableListView);

        // adapter에 넘겨줄 data 정의
        ArrayList<ExpandData> data = new ArrayList<>();

        ExpandData data2 = new ExpandData();
        data2.cityName = "서울";
        data2.guNames.add("강동");
        data2.guNames.add("강서");
        data2.guNames.add("동작");
        data2.guNames.add("관악");
        data2.guNames.add("서초");

        data.add(data2);

        data2 = new ExpandData();
        data2.cityName = "광주";
        data2.guNames.add("광산");
        data2.guNames.add("중구");
        data2.guNames.add("북구");
        data.add(data2);

        data2 = new ExpandData();
        data2.cityName = "부산";
        data2.guNames.add("동래");
        data2.guNames.add("서면");
        data2.guNames.add("북구");
        data.add(data2);

        ExpandableAdapter ea = new ExpandableAdapter(this, R.layout.expand_parent_item, R.layout.expand_child_item, data);
        listView.setAdapter(ea);

    }
}
