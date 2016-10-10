package com.jaemin.android.fragmentbasic_tablayout_viewpager;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    static final int FRAGMENT_COUNT = 4;

    HomeFragment home;
    MapFragment map;
    EtcFragment etc;

    BlankFragment settings;

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        home = new HomeFragment();
        map = new MapFragment();
        etc = new EtcFragment();
        //settings = new SettingsFragment();
        settings = new BlankFragment();

        TabLayout tab = (TabLayout) findViewById(R.id.tab);
        tab.addTab(tab.newTab().setText("Home")); // 탭을 하나씩 추가하는 코드
        tab.addTab(tab.newTab().setText("Map"));
        tab.addTab(tab.newTab().setText("Etc"));
        tab.addTab(tab.newTab().setText("Settings"));

        viewPager = (ViewPager) findViewById(R.id.pager);

        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());

        // pager에 adapter를 넣어주면 된다
        viewPager.setAdapter(adapter);

        // viewPager가 변경되었을 때 tab을 변경해주는 리스너
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));

        // tab에 viewPager를 연결해주는 리스너
        tab.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
//        tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });

    }

    @Override
    public void onFragmentInteraction(Fragment fragment) { // 이 리스너를 구현해 주면 된다
        if(fragment instanceof HomeFragment){
            Toast.makeText(this, "HomeFragment", Toast.LENGTH_SHORT).show();
        } else if(fragment instanceof BlankFragment){
            Toast.makeText(this, "BlankFragment", Toast.LENGTH_SHORT).show();
        }
    }

    class PagerAdapter extends FragmentStatePagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            Fragment fragment = null;

            switch(position) {
                case 0:
                    fragment = home;
                    break;
                case 1:
                    fragment = map;
                    break;
                case 2:
                    fragment = etc;
                    break;
                case 3:
                    fragment = settings;
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return FRAGMENT_COUNT;
        }
    }
}
