package com.jaemin.android.materialdesign_elevation;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/*
    1. res/values/style.xml의 Theme을 android:Theme.Material로 변경
    2. AndroidManifest.xml의 application의 theme속성을 변경된 Theme으로 지정(Default로 됨)
    3. Activity의 상속 클래스를 AppCompatActivity에서 Activity로 변경
 */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
