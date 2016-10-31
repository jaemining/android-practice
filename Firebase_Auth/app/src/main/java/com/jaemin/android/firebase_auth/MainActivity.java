package com.jaemin.android.firebase_auth;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView etEmail;
    TextView etPassword;
    Button btnSignUp;
    Button btnSignIn;
    TextView etLog;

    ListView listView;
    ArrayList<Map<String, User>> datas = new ArrayList<>();

    FirebaseDatabase database;
    DatabaseReference userRef;


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private static final String TAG = "EmailPassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = (TextView) findViewById(R.id.editText_email);
        etPassword = (TextView) findViewById(R.id.editText_password);
        btnSignUp = (Button) findViewById(R.id.button_signUp);
        btnSignIn = (Button) findViewById(R.id.button_signIn);

        listView = (ListView) findViewById(R.id.listView);
        ListAdapter adapter = new ListAdapter();
        listView.setAdapter(adapter);

        // 1. 인증객체 가져오기
        mAuth = FirebaseAuth.getInstance();

        // 2. 리스너 설정
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) { // 인증상태가 변경되면 자동으로 호출된다
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        // 4. 신규 계정 생성
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString().trim();
                String pwd = etPassword.getText().toString().trim();
                if(!"".equals(email) && !"".equals(pwd)) {
                    addUser(email, pwd);
                } else {
                    Toast.makeText(MainActivity.this, "Email과 Password를 입력해주세요",Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 5. 이메일, 비밀번호로 로그인(sign in)
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString().trim();
                String pwd = etPassword.getText().toString().trim();
                if(!"".equals(email) && !"".equals(pwd)) {
                    signInUser(email, pwd);
                } else {
                    Toast.makeText(MainActivity.this, "Email과 Password를 입력해주세요",Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Database Connection
        database = FirebaseDatabase.getInstance();

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
    }

    public void addUser(String email, String pwd) {
        mAuth.createUserWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "사용자 등록에 실패하였습니다",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "사용자 등록 성공",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Log.w(TAG, "User ==== " + authResult.getUser());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "User ==== " + e.getMessage());

                    }
                });
    }

    public void signInUser(String email, String pwd) {

        mAuth.signInWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(MainActivity.this, "실 패",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "성 공",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    // 3. 리스너 해제 및 재등록 처리
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
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
