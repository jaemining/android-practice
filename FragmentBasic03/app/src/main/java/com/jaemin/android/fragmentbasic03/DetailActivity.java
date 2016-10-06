package com.jaemin.android.fragmentbasic03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class DetailActivity extends AppCompatActivity {

    EditText detailTitle;
    EditText detailContent;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailTitle = (EditText) findViewById(R.id.editText_detail_title);
        detailContent = (EditText) findViewById(R.id.editText_detail_content);

        intent = getIntent();

        int position = intent.getExtras().getInt("position");// 리스트 데이터의 키 값

        // 키 값을 이용하여 RecyclerAnimationActivity에 전역으로 선언되어 있는 ArrayList를 쓸 수 있다
        ListData data = MainActivity.datas.get(position);
        detailTitle.setText(data.title);
        detailContent.setText(data.content);
    }
}
