package com.example.khanhpd.khanhpdph05706_ass.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.khanhpd.khanhpdph05706_ass.activity.DanhSachHangHoa;

public class HangHoaAdapter extends FragmentStatePagerAdapter {
    public HangHoaAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return new DanhSachHangHoa();
    }

    @Override
    public int getCount() {
        return 1;
    }
}
