package com.jaemin.android.sqlitebasic_sqlitedatabase;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assetToDisk("sqlite.db");
    }

    public void assetToDisk(String fileName) {
        // 외부에서 작성된 sqlite.db 파일 사용하기
        // 1. assets에 담아둔 파일을 internal 혹은 external 공간으로 복사하기 위해 읽어온다
        // 가져오는 명령어 - assetmanager
        AssetManager manager = getAssets();

        InputStream is = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;


        try {
            is = manager.open(fileName);
            bis = new BufferedInputStream(is);

            // 2. 저장할 위치에 파일이 없으면 생성한다
            String internalPath = getFilesDir().getAbsolutePath();
            Log.i("aaa", "=== internalPath ===" + internalPath);
            String targetFile = internalPath + File.separator + fileName;
            Log.i("aaa", "=== targetFile ===" + targetFile);
            File file = new File(targetFile);
            if(!file.exists()) {
                file.createNewFile();
            }

            // 3. Fileoutputstream을 생성해서 파일 내용을 쓴다
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            // 읽어올 데이터를 담아줄 변수
            int read = -1; // -1이 아닐 때 까지 버퍼를 읽어온다. flag 값
            // 한번에 읽을 버퍼의 크기 지정
            byte buffer[] = new byte[1024];

            // 더이상 읽어올 데이터가 없을 때 까지 buffer 단위로 읽어서 쓴다
            while ((read = bis.read(buffer, 0, 1024)) != -1) {
                bos.write(buffer, 0, read);
            }
            // 남아있는 데이터를 buffer에서 써준다
            bos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) { is.close(); }
                if (bis != null) { bis.close(); }
                if (fos != null) { fos.close(); }
                if (bos != null) { bos.close(); }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
