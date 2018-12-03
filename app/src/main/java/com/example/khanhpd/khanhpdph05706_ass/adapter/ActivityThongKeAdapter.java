package com.example.khanhpd.khanhpdph05706_ass.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.khanhpd.khanhpdph05706_ass.activity.ThongKe;

public class ActivityThongKeAdapter extends FragmentStatePagerAdapter {
    public ActivityThongKeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return new ThongKe();
    }

    @Override
    public int getCount() {
        return 1;
    }
}
