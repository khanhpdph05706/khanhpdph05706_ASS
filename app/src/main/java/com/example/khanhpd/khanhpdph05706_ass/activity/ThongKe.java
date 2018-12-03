package com.example.khanhpd.khanhpdph05706_ass.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.example.khanhpd.khanhpdph05706_ass.adapter.ThongKeAdapter;

public class ThongKe extends Fragment {
    private TabLayout tabLayoutThongKe;
    private ViewPager viewPagerThongKe;
    private ThongKeAdapter thongKeAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(com.example.khanhpd.khanhpdph05706_ass.R.layout.activity_thong_ke, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabLayoutThongKe = (TabLayout) view.findViewById(com.example.khanhpd.khanhpdph05706_ass.R.id.tabLayoutThongKe);
        viewPagerThongKe = (ViewPager) view.findViewById(com.example.khanhpd.khanhpdph05706_ass.R.id.viewPagerThongKe);

        thongKeAdapter = new ThongKeAdapter(getChildFragmentManager());
        viewPagerThongKe.setAdapter(thongKeAdapter);
        tabLayoutThongKe.setupWithViewPager(viewPagerThongKe);
    }
}
