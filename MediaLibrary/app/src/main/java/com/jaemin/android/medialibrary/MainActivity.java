package com.jaemin.android.medialibrary;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final static int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            initData();
        } else {
            checkPermissions();
        }

    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermissions() {
        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // 쓰기 권한이 없으면 로직 처리
            // 중간에 권한 내용에 대한 알림을 처리하는 함수
            //shouldShowRequestPermissionRationale();
            String permissionArray[] = {Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permissionArray, REQUEST_CODE);// 콜백함수가 호출될 때 requestCode가 넘어온다
        } else {
            // 쓰기 권한이 있으면 파일 생성
            initData();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) { // 사용자가 권한 설정을 OK 했는지 확인하는 과정
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE :
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initData();
                }
        }
    }

    public void initData() {
        ArrayList<RecyclerData> datas = getMusicInfo();

        RecyclerView listView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerAdapter adapter = new RecyclerAdapter(datas, R.layout.recycler_item);
        listView.setAdapter(adapter);
        listView.setLayoutManager(new LinearLayoutManager(this));
    }

    public ArrayList<RecyclerData> getMusicInfo() {
        ArrayList<RecyclerData> datas = new ArrayList<>();

        // 노래 아이디, 타이틀, 아티스트 이름
        String projections[] = {
          MediaStore.Audio.Media._ID, MediaStore.Audio.Media.ALBUM_ID, MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.ARTIST
        };

        //getContentResolver().query( uri 주소, 검색해올컬럼명들, 조건절, 조건절에매핑되는값, 정렬 );
        Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projections, null, null, null);
        // 커서가 옮겨 가면서 데이터를 가져온다
        /*
            - uri : content://스키마 형태로 정해져 있는 곳의 데이터를 가져온다
            - projections : 가져올 컬럼 이름들의 배열. null을 입력하면 모든값을 가져온다(성능에 안좋을 수 있다)
            - selection : 조건절(where)에 해당하는 내용
            - selectionArgs : 조건절이 preparedstatement 형태일 때 ? 에 매핑되는 값의 배열
            - sort order : 정렬 조건

            ** 이미지를 가져와야 할 땐 MediaStore.Image. ... 으로 하면 된다
         */

        if(cursor != null) {
            // 커서를 다음 단계로 옮길 수 있으면 while문을 실행하라는 의미
            while (cursor.moveToNext()) {
                RecyclerData data = new RecyclerData();
                //data.name = String.valueOf(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                // 데이터에 가수 이름 입력
                // 1. 가수 이름 컬럼의 순서(index)를 가져온다
                int artistIdx = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
                // 2. 해당 index를 가진 컬럼의 실제값을 가져온다
                data.name = cursor.getString(artistIdx);

                int idx = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
                data.title = cursor.getString(idx);

                idx = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);
                data.albumId = cursor.getString(idx);

                idx = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
                data.musicId = cursor.getString(idx);

                datas.add(data);
            }
        }
        cursor.close();

        return datas;
    }
}
