package com.example.khanhpd.khanhpdph05706_ass.activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.example.khanhpd.khanhpdph05706_ass.R;
import com.example.khanhpd.khanhpdph05706_ass.adapter.LvCongNhanAdapter;
import com.example.khanhpd.khanhpdph05706_ass.database.OpenHelper;
import com.example.khanhpd.khanhpdph05706_ass.database.model.CongNhan;

public class DanhSachCongNhan extends Fragment {
    private RecyclerView recyclerViewCongNhan;
    private LvCongNhanAdapter congNhanAdapter;
    private List<CongNhan> congNhans = new ArrayList<>();
    private OpenHelper db;
    private EditText edMaCongNhan;
    private EditText edTenCongNhan;
    private Spinner spGioiTinh;
    private EditText edNgaySinh;
    private Spinner spQue;
    private Spinner spVitri;
    private Button btnChonNgaySinh;
    private Spinner spSapXepCongNhan;
    private SwipeRefreshLayout refreshLayout;
    private int Nam, Thang, Ngay;

    public String viTri;
    private String MaSoCN, TenCN, GioiTinh, NgaySinh, Que, ViTri;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(com.example.khanhpd.khanhpdph05706_ass.R.layout.activity_danh_sach_cong_nhan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewCongNhan = (RecyclerView) view.findViewById(com.example.khanhpd.khanhpdph05706_ass.R.id.recyclerViewCongNhan);
        spSapXepCongNhan = (Spinner) view.findViewById(com.example.khanhpd.khanhpdph05706_ass.R.id.spSapXepCongNhan);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(com.example.khanhpd.khanhpdph05706_ass.R.id.refreshCongNhan);

        db = new OpenHelper(getContext());

        final FloatingActionButton fab = (FloatingActionButton) view.findViewById(com.example.khanhpd.khanhpdph05706_ass.R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                final View view1 = getLayoutInflater().inflate(com.example.khanhpd.khanhpdph05706_ass.R.layout.dialog_them_cong_nhan, null);
                dialog.setView(view1);
                dialog.setCancelable(false);
                dialog.setTitle("Thêm công nhân");

                edMaCongNhan = (EditText) view1.findViewById(com.example.khanhpd.khanhpdph05706_ass.R.id.edMaCongNhan);
                edTenCongNhan = (EditText) view1.findViewById(com.example.khanhpd.khanhpdph05706_ass.R.id.edTenCongNhan);
                spGioiTinh = (Spinner) view1.findViewById(com.example.khanhpd.khanhpdph05706_ass.R.id.spGioiTinh);
                edNgaySinh = (EditText) view1.findViewById(com.example.khanhpd.khanhpdph05706_ass.R.id.edNgaySinh);
                spQue = (Spinner) view1.findViewById(com.example.khanhpd.khanhpdph05706_ass.R.id.spQue);
                spVitri = (Spinner) view1.findViewById(com.example.khanhpd.khanhpdph05706_ass.R.id.spVitri);
                btnChonNgaySinh = (Button) view1.findViewById(com.example.khanhpd.khanhpdph05706_ass.R.id.btnChonNgaySinh);

                final Calendar calendar = Calendar.getInstance();
                Nam = calendar.get(Calendar.YEAR);
                Thang = calendar.get(Calendar.MONTH);
                Ngay = calendar.get(Calendar.DAY_OF_MONTH);

                btnChonNgaySinh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                edNgaySinh.setText(dayOfMonth + "/" + month + "/" + year);
                            }
                        }, Nam, Thang, Ngay);
                        datePickerDialog.show();
                    }
                });

                dialog.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MaSoCN = edMaCongNhan.getText().toString();
                        TenCN = edTenCongNhan.getText().toString();
                        NgaySinh = edNgaySinh.getText().toString();
                        GioiTinh = spGioiTinh.getSelectedItem().toString();
                        Que = spQue.getSelectedItem().toString();
                        ViTri = spVitri.getSelectedItem().toString();

                        if (MaSoCN.isEmpty() || TenCN.isEmpty() || NgaySinh.isEmpty()){
                            Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                        }else {
                            if (db.checkMaCongNhan(MaSoCN) == false){
                                Toast.makeText(getContext(), "Mã nhân viên bị trùng", Toast.LENGTH_SHORT).show();
                            }else {
                                spSapXepCongNhan.setSelection(0);
                                addCongNhan(MaSoCN, TenCN, GioiTinh, NgaySinh, Que, ViTri);
                            }
                        }
                    }
                });

                dialog.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.setCancelable(true);
                    }
                });
                dialog.show();
            }
        });

        congNhanAdapter = new LvCongNhanAdapter(getContext(), congNhans);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewCongNhan.setLayoutManager(layoutManager);
        recyclerViewCongNhan.setItemAnimator(new DefaultItemAnimator());
        recyclerViewCongNhan.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerViewCongNhan.setAdapter(congNhanAdapter);

        getCongNhan(true, null);

        spSapXepCongNhan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                viTri = spSapXepCongNhan.getSelectedItem().toString();
                if (spSapXepCongNhan.getSelectedItemPosition() >= 1) {
                    getCongNhan(false, viTri);
                } else {
                    getCongNhan(true, null);
                    congNhanAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new FetchFeedTask().execute((Void) null);
            }
        });
    }

    private class FetchFeedTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            refreshLayout.setRefreshing(true);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            if (congNhans.size() == 0)
                return false;

            viTri = spSapXepCongNhan.getSelectedItem().toString();
            if (spSapXepCongNhan.getSelectedItemPosition() >= 1) {
                if (db.getCongNhanTheoViTri(viTri) != null) {
                    congNhans.clear();
                    congNhans.addAll(db.getCongNhanTheoViTri(viTri));
                } else {
                    Toast.makeText(getContext(), "Không có kết quả phù hợp", Toast.LENGTH_SHORT).show();
                }
            } else {
                getCongNhan(true, null);
            }

            return false;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            refreshLayout.setRefreshing(false);
        }
    }

    private void addCongNhan(String MaSoCN, String TenCN, String GioiTinh, String NgaySinh, String Que, String ViTri){
        long id = db.insertCongNhan(MaSoCN, TenCN, GioiTinh, NgaySinh, Que, ViTri);

        CongNhan congNhan = db.getCongNhan(id);

        if (congNhan != null){
            congNhans.add(0, congNhan);
            congNhanAdapter.notifyDataSetChanged();
        }
    }

    public void getCongNhan(boolean check, String viTri) {
        if (check == true) {
            congNhans.clear();
            congNhans.addAll(db.getALLCongNhan());
        } else if (check == false) {
            if (db.getCongNhanTheoViTri(viTri) != null) {
                congNhans.clear();
                congNhans.addAll(db.getCongNhanTheoViTri(viTri));
                congNhanAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getContext(), "Không có kết quả phù hợp", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
