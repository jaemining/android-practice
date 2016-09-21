package com.jaemin.android.materialdesign_elevation;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/*
    // Api level 21 이상에서 Material design 설정
    1. res/values/style.xml의 Theme을 android:Theme.Material로 변경
    2. AndroidManifest.xml의 application의 theme속성을 변경된 Theme으로 지(Default로 됨)
    3. Activity의 상속 클래스를 AppCompatActivity에서 Activity로 변경

    // Api level 21 미만에서 설정(하지만 안됨..)
    1. 상속 받는 Activity를 원래대로 AppCompatActivity로 변경
    2. style의 AppTheme을 Theme.AppCompat으로 변경
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
