package com.jaemin.android.activitybasic01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * Created by Jaemin on 2016. 9. 20..
 *
 * ### 수동으로 Activity 만들기
 * 1. Activity 클래스를 상속받는다
 * 2. onCreate 메서드를 오버라이드 한다
 * 3. onCreate 메서드 안에서 layout.xml을 Set 해줘야 한다(setContentView 함수로 세팅)
 */
public class Activity3 extends Activity{
    private static final String TAG = "Activity3";

    @Override // 1. 액티비티 생성시에 호출되는 함수
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        Log.i(TAG, "called onCreate()");
    }
    @Override // 2. 화면에 나타나기 바로 전에 호출되는 함수
    protected void onStart() {
        super.onStart();
        //Log는 앱에 영향을 주지 않고 다른 스레드로 돌아가기때문에 System.out.println을 쓰는것보다 좋음.
        Log.i(TAG,"called onStart()");
    }

    @Override // 3. 이 때, 사용자가 화면에 무언가를 입력할 수 있게 된다
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "called onResume()");
    }
    // 3번과 4번 사이에는 Activity가 동작하고 있는 중

    @Override // 4. 화면에서 사라졌을 때 <== 액티비티가 나를 일부만 가리고 있을 때(4번 액티비티가 투명하거나 반투명 할 때)
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "called onPause()");
    }

    @Override // 5. 화면에서 사가졌을 때 onStop이 호출 됨
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "called onStop()");
    }
    // 번외
    @Override // 5.1. Stop 되었던 액티비티가 다시 호출될 때
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "called onRestart()");
    }

    @Override // 6. 앱이 종료되면(메모리에서 해제되면) 호출됨
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "called onDestroy()");
    }

    public void openActivity4(View v) {
        Intent intent = new Intent(this, Activity4.class);
        startActivity(intent);
    }

    public void openActivity5(View v) {
        Intent intent = new Intent(this, Activity5.class);
        startActivity(intent);
    }
}
