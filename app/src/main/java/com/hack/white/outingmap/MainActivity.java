package com.hack.white.outingmap;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.hack.white.outingmap.mypage.MyPageFragment;
import com.hack.white.outingmap.home.HomeFragment;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setElevation(0f);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0:
                        return new HomeFragment();
                    case 1:
                        return new MyPageFragment();
                    case 2:
                        return new MyPageFragment();
                    case 3:
                        return new MyPageFragment();
                    case 4:
                        return new MyPageFragment();
                    case 5:
                        return new MyPageFragment();
                }
                return null;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position){
                    case 0:
                        return getString(R.string.home_tab);
                    case 1:
                        return getString(R.string.mypage_tab);
                    case 2:
                        return getString(R.string.mypage_tab);
                    case 3:
                        return getString(R.string.mypage_tab);
                    case 4:
                        return getString(R.string.mypage_tab);
                    case 5:
                        return getString(R.string.mypage_tab);
                }
                return "";
            }

            @Override
            public int getCount() {
                return 6;
            }
        };

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d("MainActivity", "onPageSelected() position=" + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}