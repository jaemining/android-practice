package com.jaemin.android.permissionruntime;

import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {

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
        if(checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // 쓰기 권한이 없으면 로직 처리
        } else {
            // 쓰기 권한이 있으면 파일 생성
            createFile();
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
