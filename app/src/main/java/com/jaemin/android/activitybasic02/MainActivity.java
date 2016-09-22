package com.jaemin.android.activitybasic02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void callSub(View v) {
        et = (EditText) findViewById(R.id.editText);

        Intent intent = new Intent(MainActivity.this, SubActivity.class);
        String str = et.getText().toString();
        intent.putExtra("key1", str);

        startActivity(intent);
    }
}
