package com.jaemin.android.activitybasic02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class SubActivity extends AppCompatActivity {
    EditText et;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        et = (EditText) findViewById(R.id.editText2);
        btn = (Button)findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = et.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("return1", result);
                intent.putExtra("return2", "결과값2");
                setResult(1, intent);
                finish();//종료를하면서 onActivityResult를 호출한다
            }
        });

        // 던져진(전달받은) intent를 받는다
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        //String str = eval(bundle.getString("key1"));

        et.setText(bundle.getString("key1"));//데이터를 넘기는것까지

    }

}
