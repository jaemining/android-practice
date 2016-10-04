package com.jaemin.android.permissionruntime;

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

        createFile();
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
