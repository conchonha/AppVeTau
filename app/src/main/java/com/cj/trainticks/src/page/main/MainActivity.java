package com.cj.trainticks.src.page.main;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.cj.trainticks.R;
import com.cj.trainticks.src.adapter.main.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.uits.baseproject.base.BaseActivity;
import com.uits.baseproject.base.OnCurrentFragmentListener;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends BaseActivity implements OnCurrentFragmentListener {
    private ViewPager mViewPager;
    private BottomNavigationView mBottomNav;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onEvent() {

    }

    @Override
    public void onInit() {
        mViewPager = findViewById(R.id.mViewPager);
        mBottomNav = findViewById(R.id.mBottomNav);

        ViewPagerAdapter mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mAdapter.addData();
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBottomNav.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mBottomNav.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.menu_home) mViewPager.setCurrentItem(0);
            else if (item.getItemId() == R.id.menu_profile) mViewPager.setCurrentItem(2);
            return false;
        });
    }

    @Override
    public void onBackFragment() {

    }

    @Override
    public void onCurrentFragment(@NotNull Fragment fragment) {

    }
}