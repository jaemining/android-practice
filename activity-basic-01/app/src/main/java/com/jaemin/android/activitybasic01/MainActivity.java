package com.jaemin.android.activitybasic01;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    // 전화걸기
    public void openCall(View v) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:114"));
        startActivity(intent);
    }

    // 문자 보내기
    public void openSMS(View v) {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:01000000000"));
        startActivity(intent);
    }

    // 네이버 열기
    public void openNaver(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://naver.com"));
        startActivity(intent);
    }
    // Activity를 호출하는 함수 - 버튼에서 호출하기 위해 파라미터로 View를 설정해야 한다.
    public void openActivity(View v) {
        // Activity를 호출하는 로직
        // 1. Intent 생성
        // 2. 두 가지 인자를 넘겨줘야 한다
        //    1) context(자시 자신일 경우 this를 넘겨준다)
        //    2) 호출할 액티비티 클래스명 (메모리에 클래스를 올려주기 위해 ~.class로 넘겨준다)
        Intent intent = new Intent(this, Activity2.class);
        // 3. 생성된 인텐트를 시스템에 넘겨서 실행
        startActivity(intent);
    }

    public void openMe(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
