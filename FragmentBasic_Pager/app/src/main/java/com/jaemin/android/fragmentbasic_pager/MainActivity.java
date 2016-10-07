package com.jaemin.android.fragmentbasic_pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements BlankFragment.OnListFragmentInteractionListener {
    private ViewPager pager;

    BlankFragment bf = null;
    GalleryFragment gf = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = (ViewPager) findViewById(R.id.pager);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
    }

    private class PagerAdapter extends FragmentStatePagerAdapter {

        // fragment adapter를 생성하기 위해서는 fragment manager를 생성자에 넘겨주고
        // 부모 adapter가 초기화 하여야 한다
        public PagerAdapter(FragmentManager manager) {
            super(manager);
            bf = new BlankFragment();
            gf = new GalleryFragment();
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;

            switch (position) {
                case 0:
                    fragment = bf; // index에 따라 fragment를 넘겨준다
                    break;
                case 1:
                    fragment = gf;
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;// Fragment가 2개니까~
        }
    }

    @Override
    public void onListFragmentInteraction(RecyclerData item) {
        pager.setCurrentItem(1, true);
        gf.setItem(item);
    }
}
