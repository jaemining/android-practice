package com.jaemin.android.permissionruntime;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    private final static int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M) { // 마시멜로우보다 낮을 경우
            createFile();
        } else {
            checkPermissions();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermissions() {
        if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // 쓰기 권한이 없으면 로직 처리
            // 중간에 권한 내용에 대한 알림을 처리하는 함수
            //shouldShowRequestPermissionRationale();
            String permissionArray[] = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            requestPermissions(permissionArray, REQUEST_CODE);// 콜백함수가 호출될 때 requestCode가 넘어온다
        } else {
            // 쓰기 권한이 있으면 파일 생성
            createFile();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) { // 사용자가 권한 설정을 OK 했는지 확인하는 과정
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE :
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    createFile();
                }
        }
    }

    private void createFile() {
        String filePath = Environment.getExternalStorageDirectory().getPath();//Environment로 환경설정을 가져온다
        Log.i("Root Path", "=" + filePath);
        try {
            File file = new File(filePath + File.separator + "temp.txt");
            if(!file.exists()) {
                file.createNewFile();// file이 존재하지 않을 때 지정된 경로에 create new file 된다
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
