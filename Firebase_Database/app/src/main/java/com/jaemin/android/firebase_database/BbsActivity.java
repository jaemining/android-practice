package com.jaemin.android.firebase_database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class BbsActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference rootRef;
    DatabaseReference postRef;

    Button post;
    EditText etTitle;
    EditText etName;
    EditText etContents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bbs);

        database = FirebaseDatabase.getInstance();
        rootRef = database.getReference();
        postRef = database.getReference("posts");

        post = (Button) findViewById(R.id.button_post);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etTitle.getText().toString().trim();
                String name = etName.getText().toString().trim();
                String body = etContents.getText().toString().trim();

                writeNewPost("jaemin1234", name, title, body);
            }
        });

        etTitle = (EditText) findViewById(R.id.bbs_editText_title);
        etName = (EditText) findViewById(R.id.bbs_editText_name);
        etContents = (EditText) findViewById(R.id.bbs_editText_contents);
    }

    private void writeNewPost(String userId, String username, String title, String body) {

        // postRef가 가르키는 posts에 유일한 키값을 생성해서 가져온다
        String key = postRef.push().getKey();
        // 글을 쓴 내용을 Post 객체로 만들어 주고
        Post post = new Post(userId, username, title, body);
        // 해당 객체를 Map으로 변환한다
        Map<String, Object> postValues = post.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/posts/" + key, postValues);
        childUpdates.put("/user-posts/" + userId + "/" + key, postValues);

        rootRef.updateChildren(childUpdates);
    }
}
