package com.jaemin.android.basiclist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

public class RecyclerCardActivity extends AppCompatActivity {

    ArrayList<RecyclerData> datas = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_card);

        datas = new ArrayList<>();

        for (int i=0; i<100; i++) {
            RecyclerData data = new RecyclerData();
            data.title = (i+1) + ". Make You Feel My Love";
            //data.name = "adele";
            data.image = R.mipmap.adele;

            datas.add(data);
        }

        RecyclerView listView = (RecyclerView) findViewById(R.id.recyclerCardView);
        RecyclerCardAdapter adapter = new RecyclerCardAdapter(datas, R.layout.activity_recycler_card_item, this);
        listView.setAdapter(adapter);

        // 수직 수평 스크롤이 가능
        //RecyclerView.LayoutManager manager = new LinearLayoutManager(this);

        // 그리드 타입
        //RecyclerView.LayoutManager manager = new GridLayoutManager(this, 3);

        // StaggeredGrid 타입
        RecyclerView.LayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        listView.setLayoutManager(manager);
    }
}
