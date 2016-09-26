package com.jaemin.android.layoutbasic01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;

public class DynamicGrid extends AppCompatActivity {
    GridLayout grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_grid);

        Button button = (Button) findViewById(R.id.button);
        grid = (GridLayout) findViewById(R.id.gridLayout);

        // button을 클릭할 때 마다 추가되는 코드 작성

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 클릭될 때 마다 동적으로 버튼 생성
                Button newButton = new Button(DynamicGrid.this);
                grid.addView(newButton);
            }
        });
    }
}
