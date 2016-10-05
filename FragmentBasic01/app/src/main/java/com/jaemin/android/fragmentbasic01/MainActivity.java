package com.jaemin.android.fragmentbasic01;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    Fragment fragmentOne;
    Fragment fragmentTwo;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radioButton1:
                        goOne();
                        break;
                    case R.id.radioButton2:
                        goTwo();
                        break;
                }
            }
        });

        fragmentOne = new FragmentOne();
        fragmentTwo = new FragmentTwo();
    }

    public void goTwo() {
        FragmentManager manager = getSupportFragmentManager(); // getSupportFragmentManage를 사용하는 이유 ?
        FragmentTransaction transaction = manager.beginTransaction();

        // 쌓았을 때
        transaction.replace(R.id.fragment, fragmentTwo); // fragment를 넣을 장소를 먼저 지정해준다
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void goOne() {
        FragmentManager manager = getSupportFragmentManager(); // getSupportFragmentManage를 사용하는 이유 ?
        FragmentTransaction transaction = manager.beginTransaction();

        // 쌓았을 때
        transaction.add(R.id.fragment, fragmentOne); // fragment를 넣을 장소를 먼저 지정해준다
        transaction.addToBackStack(null);
        transaction.commit();

    }
}
