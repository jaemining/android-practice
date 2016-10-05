package com.jaemin.android.medialibrary_phonebook;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 100;

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
        if(checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            String permissionArray[] = {Manifest.permission.READ_CONTACTS};
            requestPermissions(permissionArray, REQUEST_CODE);
        } else {
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
        // ArrayList<RecyclerData> datas = getContacts();
        ArrayList<RecyclerData> datas = getPhoneNumbers();


        RecyclerView listView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerAdapter adapter = new RecyclerAdapter(datas, R.layout.recycler_item);
        listView.setAdapter(adapter);
        listView.setLayoutManager(new LinearLayoutManager(this));
    }

    // 전화번호만 가져올 때, 성능이 향상된 코드
    public ArrayList<RecyclerData> getPhoneNumbers() {
        ArrayList<RecyclerData> datas = new ArrayList<>();

        Cursor c = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, // 전화번호 안의 URI를 그대로 쓴다
                null,
                null,
                null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" desc"// asc 앞에 한 칸을 띄워주어야 명령어로 인식한다
        );

        if (c != null) {
            while (c.moveToNext()) {
                RecyclerData data = new RecyclerData();

                data.name = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                data.phoneNumber = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                datas.add(data);
            }
        }

        c.close();
        return datas;
    }

    public ArrayList<RecyclerData> getContacts() {
        ArrayList<RecyclerData> datas = new ArrayList<>();

        String projections[] = {
                ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts._ID
        };

        // 조건절
        // String selection = MediaStore.Audio.Media.TITLE + "!= '야생화' ";

        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, projections, null, null, null);

        if(cursor != null) {
            while (cursor.moveToNext()) {
                RecyclerData data = new RecyclerData();

                // 주소록의 커서 사용
                int idIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID);
                String contactId = cursor.getString(idIndex); // 주소록 아이디 꺼냄 !

                // 내부에서 커서를 돌려줘야한다!?!?!?
                Cursor phoneCursor = getContentResolver().query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null, // 전화번호 데이터 셋이기 때문에 필요 없다. null을 날리면 전체를 가져온다
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId,
                        null,
                        null);

                if(phoneCursor.moveToFirst()) {
                    String tel = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    data.phoneNumber = tel;
                }

                phoneCursor.close();

                data.name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                datas.add(data);
            }
        }
        cursor.close();

        return datas;
    }
}
