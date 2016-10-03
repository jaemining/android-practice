package com.jaemin.android.basiclist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

public class BasicGridActivity extends AppCompatActivity {

    String data[] = {"백향목", "김동진", "김태원", "임재민", "김도형", "석주영", "장홍석", "김해든"};
    ArrayAdapter adapter;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_grid);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, data);
        gridView = (GridView) findViewById(R.id.gridView);

        gridView.setChoiceMode(GridView.CHOICE_MODE_SINGLE);
        gridView.setAdapter(adapter);

    }
}
