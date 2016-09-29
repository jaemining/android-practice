package com.jaemin.android.basiclist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    ImageButton image;
    TextView title;
    TextView artistName;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        image = (ImageButton) findViewById(R.id.imageButton);
        title = (TextView) findViewById(R.id.title_textView);
        artistName = (TextView) findViewById(R.id.artistName_textView);


        intent = getIntent();

//        Bundle bundle = intent.getExtras();
//
//        image.setBackgroundResource(bundle.getInt("img"));
//        title.setBackgroundResource(bundle.getInt("title"));
//        title.setBackgroundResource(bundle.getInt("name"));
        int position = intent.getExtras().getInt("position");// 리스트 데이터의 키 값

        // 키 값을 이용하여 RecyclerAnimationActivity에 전역으로 선언되어 있는 ArrayList를 쓸 수 있다
        RecyclerData data = RecyclerAnimationActivity.datas.get(position);
        image.setBackgroundResource(data.image);
        title.setText(data.title);
        artistName.setText(data.name);


    }
}
