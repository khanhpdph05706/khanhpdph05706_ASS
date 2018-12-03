package com.example.khanhpd.khanhpdph05706_ass.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.khanhpd.khanhpdph05706_ass.activity.thongke.TongSoHangHoa;
import com.example.khanhpd.khanhpdph05706_ass.activity.thongke.TongSoLuongCongNhan;

public class ThongKeAdapter extends FragmentStatePagerAdapter {
    public ThongKeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new TongSoHangHoa();
            case 1:
                return new TongSoLuongCongNhan();
            default:
                    return new TongSoHangHoa();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Hàng hoá";
            case 1:
                return "Công nhân";
                default:
                    return "Hàng hoá";
        }
    }
}
