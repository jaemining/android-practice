package com.jaemin.android.versioncontrol;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 버전별 분기 처리
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // sdk 버전이 마시멜로우일 경우

        } else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // 롤리팝일 경우

        } else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { // 킷캣일 경우

        }
    }
}
