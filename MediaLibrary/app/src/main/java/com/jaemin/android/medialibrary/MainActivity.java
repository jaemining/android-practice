package com.jaemin.android.medialibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<RecyclerData> datas = new ArrayList<>();
        for (int i=0; i<100; i++) {
            RecyclerData data = new RecyclerData();
            data.title = (i+1) + " Make you feel my love";
            data.name = "adele";

            datas.add(data);
        }

        RecyclerView listView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerAdapter adapter = new RecyclerAdapter(datas, R.layout.recycler_item);
        listView.setAdapter(adapter);
        listView.setLayoutManager(new LinearLayoutManager(this));
    }
}
