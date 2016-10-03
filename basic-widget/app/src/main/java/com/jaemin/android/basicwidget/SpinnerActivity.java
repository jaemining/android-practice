package com.jaemin.android.basicwidget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class SpinnerActivity extends AppCompatActivity {

    Spinner sp;
    TextView tv;

    String data[] = {"Mon","Tue","Wed","Thu","Fri","Sat","Sun"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        sp = (Spinner) findViewById(R.id.spinner);
        tv = (TextView) findViewById(R.id.spinnerText);

        // array adaptor 가장 많이 씀
        /*
            첫 번째 : context
            두 번째 : 줄 당 레이아웃
            세 번째 : 데이터 배열
         */

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, data
        );
        // 스피너에 값이 세팅된 어답터를 넣어준다
        sp.setAdapter(adapter);

        // 스피너에 리스너를 등록한다
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                tv.setText(data[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
