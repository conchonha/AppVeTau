package com.cj.trainticks.src.adapter.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.cj.trainticks.src.fragment.home.HomeFragment;
import com.cj.trainticks.src.fragment.profile.ProfileFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mList = new ArrayList<>();
    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    public void addData(){
        mList.add(new HomeFragment());
        mList.add(new ProfileFragment());
        mList.add(new ProfileFragment());
    }
}
