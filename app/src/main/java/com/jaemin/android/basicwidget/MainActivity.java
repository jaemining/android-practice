package com.jaemin.android.basicwidget;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    TextView tv;// 결과값이 출력되는 텍스트뷰
    RadioGroup rg;// 전역변수로 라디오그룹 선언

    // 체크박스는 독립적으로 동작하기때문에 세 개 모두 선언해줘야 한다
    CheckBox cb1;
    CheckBox cb2;
    CheckBox cb3;

    Switch sw;

    ToggleButton tb;

    ProgressBar pb;
    Switch sw2;

    SeekBar sb;
    TextView sb_tv;//seekBar의 값을 받는 View

    RatingBar rb;
    TextView rb_tv;

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

        sw = (Switch) findViewById(R.id.switch1);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()) {
                    tv.setText("Switch On");
                } else {
                    tv.setText("Switch Off");
                }
            }
        });

        tb = (ToggleButton) findViewById(R.id.toggleButton);
        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()) {
                    tv.setText("ToggleButton On");
                } else {
                    tv.setText("ToggleButton Off");
                }
            }
        });

        pb = (ProgressBar) findViewById(R.id.progressBar);
        sw2 = (Switch) findViewById(R.id.switch2);

        sw2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()) {
                    pb.setVisibility(View.VISIBLE);
                } else {
                    pb.setVisibility(View.INVISIBLE);
                }
            }
        });

        // activity_text
        pb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TextActivity.class);
                startActivity(intent);
            }
        });

        sb = (SeekBar) findViewById(R.id.seekBar1);
        sb_tv = (TextView) findViewById(R.id.sb_textView);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sb_tv.setText(progress+"%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, seekBar.getProgress()+"위치에서 터치가 시작됨", Toast.LENGTH_SHORT).show();
                // 현재 위치값을 넘겨준다

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, seekBar.getProgress()+"위치에서 터치가 종료됨", Toast.LENGTH_SHORT).show();
            }
        });

        rb = (RatingBar) findViewById(R.id.ratingBar1);
        rb_tv = (TextView) findViewById(R.id.rb_textView);

        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rb_tv.setText(rating+"/5");
            }
        });




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
