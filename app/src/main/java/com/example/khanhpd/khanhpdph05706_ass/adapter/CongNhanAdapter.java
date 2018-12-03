package com.example.khanhpd.khanhpdph05706_ass.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.khanhpd.khanhpdph05706_ass.activity.DanhSachCongNhan;

public class CongNhanAdapter extends FragmentStatePagerAdapter {
    public CongNhanAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return new DanhSachCongNhan();
    }

    @Override
    public int getCount() {
        return 1;
    }
}
