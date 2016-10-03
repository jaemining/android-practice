package com.jaemin.android.notepad;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    FloatingActionButton fab;
    RelativeLayout writeLayout;
    Button buttonCancel;
    Button buttonSave;
    RecyclerView recyclerView;
    EditText et;

    ArrayList<MemoData> datas = null;
    AdapterMemo adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        writeLayout = (RelativeLayout) findViewById(R.id.write_layout);
        buttonCancel = (Button) findViewById(R.id.button_cancel);
        buttonSave = (Button) findViewById(R.id.button_save);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        et = (EditText) findViewById(R.id.item_text);

        datas = new ArrayList<>();
        datas.add(new MemoData("sample 1"));

        adapter = new AdapterMemo(datas, R.layout.layout_item_memo);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeLayout.setVisibility(View.VISIBLE);
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeLayout.setVisibility(View.GONE);
            }
        });


        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datas.add(new MemoData(et.getText().toString()));
                updateView();
                et.setText("");
                writeLayout.setVisibility(View.GONE);
            }
        });

    }

    private void updateView() {
        Log.d("Memo", "aaaaaaa " + datas.size());
        adapter.notifyDataSetChanged();
    }
}
