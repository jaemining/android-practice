package com.jaemin.android.sqlitebasic_bbs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    EditFragment ef; // 쓰기
    ListFragment lf; // 목록
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ef = new EditFragment();
        lf = new ListFragment();

        pager = (ViewPager) findViewById(R.id.pager);
        CustomAdapter adapter = new CustomAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
    }

    @Override
    public void OnFragmentInteraction(Fragment fragment) {
        if (fragment instanceof ListFragment) {
            pager.setCurrentItem(0);
        } else if (fragment instanceof EditFragment) {
            pager.setCurrentItem(1);
        }
    }

    class CustomAdapter extends FragmentStatePagerAdapter {

        public CustomAdapter(FragmentManager fm) {
            super(fm);
            ef = new EditFragment();
            lf = new ListFragment();
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;

            switch (position) {
                case 0:
                    fragment = lf;
                    break;
                case 1:
                    fragment = ef;
                    break;
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
