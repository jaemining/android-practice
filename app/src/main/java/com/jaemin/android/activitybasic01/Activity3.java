package com.jaemin.android.activitybasic01;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Jaemin on 2016. 9. 20..
 *
 * ### 수동으로 Activity 만들기
 * 1. Activity 클래스를 상속받는다
 * 2. onCreate 메서드를 오버라이드 한다
 * 3. onCreate 메서드 안에서 layout.xml을 Set 해줘야 한다(setContentView 함수로 세팅)
 */
public class Activity3 extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
    }
}
