package com.jaemin.android.basiclist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BasicList2Activity extends AppCompatActivity {

    ArrayList<Map<String, String>> data;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_list2);

        listView = (ListView) findViewById(R.id.listView);

        setData();

    }

    private void setData() {
        data = new ArrayList<>();
        // 키 값 형태로
        for(int i=0; i<26; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("number", i+1+"");
            //char ch = (char)(65+i);
            map.put("char", (char)(65+i)+"");
            map.put("small", (char)(97+i)+"");
            data.add(map);
        }

        // this, data, 커스텀레이아웃, data에 들어가있는 맵의 key값들, 커스텀 레이아웃의 view 아이디들
        SimpleAdapter adapter = new SimpleAdapter(
                this, data, R.layout.activity_basic_list2_item, new String[]{"number","char", "small"}, new int[]{R.id.text1, R.id.text2, R.id.text3}
                );
        listView.setAdapter(adapter);
    }
}
