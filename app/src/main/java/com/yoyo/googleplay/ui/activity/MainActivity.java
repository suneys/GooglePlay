package com.yoyo.googleplay.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.yoyo.googleplay.R;
import com.yoyo.googleplay.ui.fragment.BaseFragment;
import com.yoyo.googleplay.ui.fragment.FramentFactory;
import com.yoyo.googleplay.ui.widget.PagerTab;
import com.yoyo.googleplay.utils.UIUtils;

public class MainActivity extends BaseActivity {

    private PagerTab mPagerTab;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPagerTab = (PagerTab) findViewById(R.id.pager_tab);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        mPagerTab.setViewPager(mViewPager);
        mPagerTab.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                BaseFragment fragment = FramentFactory.createFragment(position);
                fragment.loadData();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    class MyAdapter extends FragmentPagerAdapter{

        String[] mTabNames;
        public MyAdapter(FragmentManager fm) {
            super(fm);
            mTabNames = UIUtils.getStringArray(R.array.tab_names);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabNames[position];
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = FramentFactory.createFragment(position);
            return fragment;
        }

        @Override
        public int getCount() {
            return mTabNames.length;
        }
    }
}
