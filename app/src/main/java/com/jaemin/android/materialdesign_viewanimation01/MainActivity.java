package com.jaemin.android.materialdesign_viewanimation01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnAlpha; // 전역변수로 선언
    Button btnRotate;
    Button btnScale;
    Button btnTranslate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAlpha = (Button)findViewById(R.id.button); // 전역변수인 btn을 사용 (자바 문법 구조)
        btnAlpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1. 미리 정의된 애니매이션 xml을 load 한다
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.alpha);
                // 2. 애니매이션을 뷰에 적용하고 실행한다
                btnAlpha.startAnimation(animation);
            }
        });

        btnRotate = (Button)findViewById(R.id.btnRotate);
        btnRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate);
                btnRotate.startAnimation(animation);
            }
        });

        btnScale = (Button)findViewById(R.id.btnScale);
        btnScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale);
                btnScale.startAnimation(animation);
            }
        });

        btnTranslate = (Button)findViewById(R.id.btnTranslate);
        btnTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.translate);
                btnTranslate.startAnimation(animation);
            }
        });
    }
}
