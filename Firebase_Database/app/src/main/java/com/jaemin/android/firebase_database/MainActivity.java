package com.jaemin.android.firebase_database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference rootRef;

    EditText etName;
    EditText etEmail;
    EditText etUserID;
    Button btnAddUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Database Connection
        database = FirebaseDatabase.getInstance();
        // 참조 포인트
        //DatabaseReference myRef = database.getReference("message");
        rootRef = database.getReference();
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot root) {
                Toast.makeText(MainActivity.this, "등록되었습니다", Toast.LENGTH_SHORT).show();

                // depth1 : message, users
                for(DataSnapshot depth1 : root.getChildren()) {
                    Log.e("snapshot", "depth1 === "+depth1.getKey());
                    for (DataSnapshot depth2 : depth1.getChildren()) {
                        Log.e("snapshot", "depth2 === "+depth2.getKey());
                        for (DataSnapshot depth3 : depth2.getChildren()) {
                            Log.e("snapshot", "depth3 === "+depth3.getValue());
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        etName = (EditText) findViewById(R.id.editText_name);
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

    }
    private void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email);

        rootRef.child("users").child(userId).setValue(user);

        /*

         */
    }
}
