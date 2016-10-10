package com.jaemin.android.sqlitebasic_sqlitedatabase;

import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    TextView result;
    Button openDatabase;
    Button button_insert;
    Button button_select;
    Button button_update;
    Button button_delete;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        openDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = SQLiteDatabase.openDatabase(getFullPath("sqlite.db"), null, 0);
            }
        });

        button_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(db != null) {
                    // 쿼리를 실행해준다, 즉 select문을 제외한 모든 쿼리에 사용
                    db.execSQL("insert into bbs(num, user_name, title) values(1, 'jaemin', 'title1');");
                    // 쿼리 실행 후 결과값을 커서로 리턴해준다, 즉 select문에 사용
                    //db.rawQuery("", null);
                }
            }
        });

        button_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(db != null) {
                    Cursor cursor = db.rawQuery("select * from bbs", null);
                    while(cursor.moveToNext()) {
                        int idx = cursor.getColumnIndex("num");
                        String num = cursor.getString(idx);
                        idx = cursor.getColumnIndex("user_name");
                        String user_name = cursor.getString(idx);
                        idx = cursor.getColumnIndex("title");
                        String title = cursor.getString(idx);
                        idx = cursor.getColumnIndex("nDate");
                        String nDate = cursor.getString(idx);
                        String temp = result.getText().toString();
                        result.setText(temp + "\n num = " + num + ", user_name = " + user_name + ", title = " + title + ", nDate = " + nDate);
                    }
                }
            }
        });
    }

    private void init() {
        File file = new File(getFullPath("sqlite.db"));
        if(!file.exists()) {
            assetToDisk("sqlite.db");
        }
        result = (TextView) findViewById(R.id.editText);
        openDatabase = (Button) findViewById(R.id.button_openDatabase);
        button_insert = (Button) findViewById(R.id.button_insert);
        button_select = (Button) findViewById(R.id.button_select);
        button_update = (Button) findViewById(R.id.button_update);
        button_delete = (Button) findViewById(R.id.button_delete);

    }

    public String getFullPath(String fileName) {
        return getFilesDir().getAbsolutePath() + File.separator + fileName;
    }

    public void assetToDisk(String fileName) {
        // 외부에서 작성된 sqlite.db 파일 사용하기
        // 1. assets에 담아둔 파일을 internal 혹은 external 공간으로 복사하기 위해 읽어온다
        // 가져오는 명령어 - assetmanager
        InputStream is = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;


        try {
            AssetManager manager = getAssets();

            is = manager.open(fileName);
            bis = new BufferedInputStream(is);

            // 2. 저장할 위치에 파일이 없으면 생성한다
//            String internalPath = getFilesDir().getAbsolutePath();
//            Log.i("aaa", "=== internalPath ===" + internalPath);
//            String targetFile = internalPath + File.separator + fileName;

            String targetFile = getFullPath(fileName);

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
