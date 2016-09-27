package com.jaemin.android.basicwidget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;// 결과값이 출력되는 텍스트뷰
    RadioGroup rg;// 전역변수로 라디오그룹 선언

    // 체크박스는 독립적으로 동작하기때문에 세 개 모두 선언해줘야 한다
    CheckBox cb1;
    CheckBox cb2;
    CheckBox cb3;

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

        cb1 = (CheckBox) findViewById(R.id.checkBoxDog);
        cb2 = (CheckBox) findViewById(R.id.checkBoxPig);
        cb3 = (CheckBox) findViewById(R.id.checkBoxChicken);

        cb1.setOnCheckedChangeListener(checkedChangeListener);
        cb2.setOnCheckedChangeListener(checkedChangeListener);
        cb3.setOnCheckedChangeListener(checkedChangeListener);


    }
    // 컴파운드 계열(체크박스, 토글, 스위치, ...) 버튼에서 사용되는 체크 변화를 감지하는 리스너
    CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            StringBuilder sb = new StringBuilder();

            if(cb1.isChecked()) {
                sb.append("Dog ");
            }
            if(cb2.isChecked()) {
                sb.append("Pig ");
            }
            if(cb3.isChecked()) {
                sb.append("Chicken ");
            }

            tv.setText(sb.toString());
        }
    };//변수로인식되기때문에 ; 붙여줘야 한다
}
