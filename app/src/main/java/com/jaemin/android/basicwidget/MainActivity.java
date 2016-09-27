package com.jaemin.android.basicwidget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;// 결과값이 출력되는 텍스트뷰
    RadioGroup rg;// 전역변수로 라디오그룹 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.largeTextView);
        rg = (RadioGroup) findViewById(R.id.radioGroup);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                // 현재 체크된 라디오 버튼 아이디를 가져온다
                //int checked = rg.getCheckedRadioButtonId();

                switch (checkedId) {
                    case R.id.rdApple:
                        tv.setText("Apple checked");
                        break;
                    case R.id.rdOrange:
                        tv.setText("Orange checked");
                        break;
                    case R.id.rdBanana:
                        tv.setText("Banana checked");
                        break;
                }
            }
        });
    }
}
