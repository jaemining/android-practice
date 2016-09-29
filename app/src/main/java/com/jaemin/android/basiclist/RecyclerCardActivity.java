package com.jaemin.android.basiclist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class RecyclerCardActivity extends AppCompatActivity {

    ArrayList<RecyclerData> datas = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_card);

        // ArrayList<RecyclerData> datas = new ArrayList<>();
        for (int i=0; i<100; i++) {
            RecyclerData data = new RecyclerData();
            data.title = (i+1) + ". Make You Feel My Love";
            data.name = "adele";
            data.image = R.mipmap.adele;

            datas.add(data);
        }

    }
}
