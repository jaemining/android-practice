package com.jaemin.android.firebase_database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference rootRef;
    DatabaseReference userRef;

    EditText etName;
    EditText etEmail;
    EditText etUserID;
    Button btnAddUser;
    Button btnOpenbbs;

    ListView listView;

    ArrayList<Map<String, User>> datas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Database Connection
        database = FirebaseDatabase.getInstance();

        // 참조 포인트
        //DatabaseReference myRef = database.getReference("message");
//        rootRef = database.getReference();
//        rootRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot root) {
//                Toast.makeText(MainActivity.this, "등록되었습니다", Toast.LENGTH_SHORT).show();
//
//                // depth1 : message, users
//                for(DataSnapshot depth1 : root.getChildren()) {
//                    Log.e("snapshot", "depth1 === "+depth1.getKey());
//                    for (DataSnapshot depth2 : depth1.getChildren()) {
//                        Log.e("snapshot", "depth2 === "+depth2.getKey());
//                        for (DataSnapshot depth3 : depth2.getChildren()) {
//                            Log.e("snapshot", "depth3 === "+depth3.getValue());
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
        userRef = database.getReference("users");
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot users) {
                Log.e("FireBase","snapshot="+users.getValue());

                datas = new ArrayList<>();
                for(DataSnapshot userData : users.getChildren()){
                    try {
                        Map<String, User> data = new HashMap<>();
                        String userId = userData.getKey();
                        User user = userData.getValue(User.class);
                        data.put(userId, user);
                        datas.add(data);
                    }catch(Exception e){
                        // 데이터 구조가 달라서 매핑이 안될경우 예외처리
                        e.printStackTrace();
                    }
                }
                listView.deferNotifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        etName = (EditText) findViewById(R.id.bbs_editText_name);
        etEmail = (EditText) findViewById(R.id.editText_email);
        etUserID = (EditText) findViewById(R.id.editText_userID);
        btnAddUser = (Button) findViewById(R.id.button_addUser);

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String userID = etUserID.getText().toString().trim();

                if(!"".equals(name) && !"".equals(email) && !"".equals(userID)) {
                    writeNewUser(userID, name, email);
                    etUserID.setText("");
                    etName.setText("");
                    etEmail.setText("");
                    //Toast.makeText(MainActivity.this, "등록되었습니다", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "이름, 이메일, 아이디를 입력하세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnOpenbbs = (Button) findViewById(R.id.button_openBbs);
        btnOpenbbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BbsActivity.class);
                startActivity(intent);
            }
        });

        listView = (ListView) findViewById(R.id.listView);
        ListAdapter adapter = new ListAdapter();
        listView.setAdapter(adapter);
    }
    private void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email);

        // rootRef.child("users").child(userId).setValue(user);
        userRef.child("users").child(userId).setValue(user);


        /*

         */
    }

    class ListAdapter extends BaseAdapter {

        LayoutInflater inflater;
        public ListAdapter() {
            inflater = getLayoutInflater();
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int i) {
            return datas.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View converView, ViewGroup parent) {
            if(converView == null) {
                converView = inflater.inflate(R.layout.list_item, null);

            }
            TextView tvName = (TextView) converView.findViewById(R.id.textView_name);
            TextView tvUserID = (TextView) converView.findViewById(R.id.textView_UserID);
            TextView tvEmail = (TextView) converView.findViewById(R.id.textView_email);

            Map<String, User> data = datas.get(position);
            String userID = data.keySet().iterator().next();
            User user = data.get(userID);

            tvUserID.setText(userID);
            tvName.setText(user.username);
            tvEmail.setText(user.email);

            return converView;
        }
    }
}
