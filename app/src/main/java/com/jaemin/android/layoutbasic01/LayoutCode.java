package com.jaemin.android.layoutbasic01;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class LayoutCode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_layout_code);

        // 레이아웃을 생성한다
        RelativeLayout layout = new RelativeLayout(this);
        // 내부에 들어가는 위젯들을 생성한다
        Button button = new Button(this);
        // 위젯의 속성도 정의할 수 있다
        button.setText("Button A");
        button.setBackgroundColor(Color.MAGENTA);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LayoutCode.this, "button click", Toast.LENGTH_LONG).show();
            }
        });

        // 레이아웃을 설정한다
        RelativeLayout.LayoutParams buttonParam = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        //
        buttonParam.addRule(RelativeLayout.CENTER_HORIZONTAL);
        buttonParam.addRule(RelativeLayout.CENTER_VERTICAL);

        // 레이아웃에 위에서 생성한 위젯들을 더해준다
        layout.addView(button, buttonParam);

        // 최종적으로 액티비티에 최상위 레이아웃 개체를 세팅한다
        setContentView(layout);
    }
}
