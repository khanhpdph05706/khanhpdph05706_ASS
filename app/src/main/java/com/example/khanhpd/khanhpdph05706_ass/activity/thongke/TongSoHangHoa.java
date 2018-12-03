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
import com.example.khanhpd.khanhpdph05706_ass.database.OpenHelper;

import java.util.ArrayList;
import java.util.List;

import com.example.khanhpd.khanhpdph05706_ass.database.OpenHelper;

public class TongSoHangHoa extends Fragment {
    private TextView tvTongSo;
    private TextView tvXuatKhau;
    private TextView tvNhapKhau;
    private TextView tvLoaiA;
    private TextView tvLoaiB;
    private TextView tvLoaiC;
    private OpenHelper db;
    private AnyChartView anyChar1;
    private int TongSo, XuatKhau, NhapKhau, LoaiA, LoaiB, LoaiC;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(com.example.khanhpd.khanhpdph05706_ass.R.layout.activity_tong_so_hang_hoa, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvTongSo = (TextView) view.findViewById(com.example.khanhpd.khanhpdph05706_ass.R.id.tvTongSo);
        tvXuatKhau = (TextView) view.findViewById(com.example.khanhpd.khanhpdph05706_ass.R.id.tvXuatKhau);
        tvNhapKhau = (TextView) view.findViewById(com.example.khanhpd.khanhpdph05706_ass.R.id.tvNhapKhau);
        tvLoaiA = (TextView) view.findViewById(com.example.khanhpd.khanhpdph05706_ass.R.id.tvLoaiA);
        tvLoaiB = (TextView) view.findViewById(com.example.khanhpd.khanhpdph05706_ass.R.id.tvLoaiB);
        tvLoaiC = (TextView) view.findViewById(com.example.khanhpd.khanhpdph05706_ass.R.id.tvLoaiC);
        anyChar1 = (AnyChartView) view.findViewById(com.example.khanhpd.khanhpdph05706_ass.R.id.anyChar1);

        db = new OpenHelper(getContext());

        TongSo = db.getTongSoHangHoa();
        XuatKhau = db.getTongSoHangHoaXuatKhau();
        NhapKhau = db.getTongSoHangHoaNhapKhau();
        LoaiA = db.getSoLoaiA();
        LoaiB = db.getSoLoaiB();
        LoaiC = db.getSoLoaiC();

        tvTongSo.setText(TongSo + "");
        tvXuatKhau.setText(XuatKhau + "");
        tvNhapKhau.setText(NhapKhau + "");
        tvLoaiA.setText(LoaiA + "");
        tvLoaiB.setText(LoaiB + "");
        tvLoaiC.setText(LoaiC + "");

        Pie pie = AnyChart.pie();

        List<DataEntry> dataEntries = new ArrayList<>();
        dataEntries.add(new ValueDataEntry("Loại A", LoaiA));
        dataEntries.add(new ValueDataEntry("Loại B", LoaiB));
        dataEntries.add(new ValueDataEntry("Loại C", LoaiC));

        pie.setData(dataEntries);
        anyChar1.setChart(pie);
    }
}
