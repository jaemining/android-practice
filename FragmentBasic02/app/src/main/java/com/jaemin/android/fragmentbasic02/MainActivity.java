package com.jaemin.android.fragmentbasic02;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Fragment fragmentOne;
    Fragment fragmentTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentOne = new FragmentOne();
        fragmentTwo = new FragmentTwo();

        setOne();
    }

    // 처음에 들어오는 Fragment 지정 , 왜냐면 FrameLayout은 처음에 fragment를 지정해주는 기능이 없기때문
    public void setOne() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment, fragmentOne);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    public void goTwo() {
        FragmentManager manager = getSupportFragmentManager(); // getSupportFragmentManage를 사용하는 이유 ?
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        // 쌓았을 때
        transaction.replace(R.id.fragment, fragmentTwo); // fragment를 넣을 장소를 먼저 지정해준다
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void goOne() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);

        transaction.add(R.id.fragment, fragmentOne);
        transaction.addToBackStack(null);
        transaction.commit();

    }
}
