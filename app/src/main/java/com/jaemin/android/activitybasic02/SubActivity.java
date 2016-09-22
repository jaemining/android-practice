package com.jaemin.android.activitybasic02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;


public class SubActivity extends AppCompatActivity {
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        et = (EditText) findViewById(R.id.editText2);

        // 던져진 intent를 받는다
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        et.setText(bundle.getString("key1"));//데이터를 넘기는것까지

    }
}
