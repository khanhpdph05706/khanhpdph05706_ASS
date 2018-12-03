package com.example.khanhpd.khanhpdph05706_ass.activity.thongke;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anychart.anychart.AnyChart;
import com.anychart.anychart.AnyChartView;
import com.anychart.anychart.DataEntry;
import com.anychart.anychart.Pie;
import com.anychart.anychart.ValueDataEntry;

import java.util.ArrayList;
import java.util.List;

import com.example.khanhpd.khanhpdph05706_ass.database.OpenHelper;

public class TongSoLuongCongNhan extends Fragment {
    private TextView tvTongSoCongNhan;
    private TextView tvNhapLieu;
    private TextView tvLaiXe;
    private TextView tvXepHang;
    private OpenHelper db;
    private AnyChartView anyChar;
    private int TongSoCongNhan, SoNhapLieu, SoLaiXe, SoXepHang;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(com.example.khanhpd.khanhpdph05706_ass.R.layout.activity_tong_so_luong_cong_nhan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvTongSoCongNhan = (TextView) view.findViewById(com.example.khanhpd.khanhpdph05706_ass.R.id.tvTongSoCongNhan);
        tvNhapLieu = (TextView) view.findViewById(com.example.khanhpd.khanhpdph05706_ass.R.id.tvNhapLieu);
        tvLaiXe = (TextView) view.findViewById(com.example.khanhpd.khanhpdph05706_ass.R.id.tvLaiXe);
        tvXepHang = (TextView) view.findViewById(com.example.khanhpd.khanhpdph05706_ass.R.id.tvXepHang);
        anyChar = (AnyChartView) view.findViewById(com.example.khanhpd.khanhpdph05706_ass.R.id.anyChar);

        db = new OpenHelper(getContext());

        TongSoCongNhan = db.getTongSoCongNhan();
        SoNhapLieu = db.getSoCongNhanNhapLieu();
        SoLaiXe = db.getSoCongNhanLaiXe();
        SoXepHang = db.getSoCongNhanXepHang();

        tvTongSoCongNhan.setText(TongSoCongNhan + "");
        tvNhapLieu.setText(SoNhapLieu + "");
        tvLaiXe.setText(SoLaiXe + "");
        tvXepHang.setText(SoXepHang + "");

        Pie pie = AnyChart.pie();

        List<DataEntry> dataEntries = new ArrayList<>();
        dataEntries.add(new ValueDataEntry("Nhập liệu", SoNhapLieu));
        dataEntries.add(new ValueDataEntry("Lái xe", SoLaiXe));
        dataEntries.add(new ValueDataEntry("Xếp hàng", SoXepHang));

        pie.setData(dataEntries);
        anyChar.setChart(pie);
    }
}
