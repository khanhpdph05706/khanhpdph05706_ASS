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
import com.example.khanhpd.khanhpdph05706_ass.adapter.LvHangHoaAdapter;
import com.example.khanhpd.khanhpdph05706_ass.database.OpenHelper;
import com.example.khanhpd.khanhpdph05706_ass.database.model.HangHoa;

public class DanhSachHangHoa extends Fragment {
    private Spinner spSapXepHangHoa;
    private RecyclerView recyclerViewHangHoa;
    private LvHangHoaAdapter lvHangHoaAdapter;
    private List<HangHoa> hangHoas = new ArrayList<>();
    private OpenHelper db;
    private EditText edMaSo;
    private EditText edTenHangHoa;
    private Spinner spLoaiHang;
    private Spinner spLoai;
    private EditText edNgayXuat;
    private Button btnChonNgayXuat;
    private EditText edNgayNhap;
    private Button btnChonNgayNhap;
    private SwipeRefreshLayout refreshLayout;
    private int Nam, Thang, Ngay;

    public String viTri1;
    private String MaSoHH, TenHH, Loai, LoaiHang, NgayNhap, NgayXuat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(com.example.khanhpd.khanhpdph05706_ass.R.layout.activity_danh_sach_hang_hoa, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spSapXepHangHoa = (Spinner) view.findViewById(R.id.spSapXepHangHoa);
        recyclerViewHangHoa = (RecyclerView) view.findViewById(R.id.recyclerViewHangHoa);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refreshHangHoa);

        db = new OpenHelper(getContext());

        final FloatingActionButton fabHH = (FloatingActionButton) view.findViewById(com.example.khanhpd.khanhpdph05706_ass.R.id.fabHH);
        fabHH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                final View view1 = getLayoutInflater().inflate(com.example.khanhpd.khanhpdph05706_ass.R.layout.dialog_them_hang_hoa, null);
                dialog.setView(view1);
                dialog.setCancelable(false);
                dialog.setTitle("Thêm hàng hoá");

                edMaSo = (EditText) view1.findViewById(com.example.khanhpd.khanhpdph05706_ass.R.id.edMaSo);
                edTenHangHoa = (EditText) view1.findViewById(com.example.khanhpd.khanhpdph05706_ass.R.id.edTenHangHoa);
                spLoaiHang = (Spinner) view1.findViewById(com.example.khanhpd.khanhpdph05706_ass.R.id.spLoaiHang);
                spLoai = (Spinner) view1.findViewById(com.example.khanhpd.khanhpdph05706_ass.R.id.spLoai);
                edNgayXuat = (EditText) view1.findViewById(com.example.khanhpd.khanhpdph05706_ass.R.id.edNgayXuat);
                btnChonNgayXuat = (Button) view1.findViewById(com.example.khanhpd.khanhpdph05706_ass.R.id.btnChonNgayXuat);
                edNgayNhap = (EditText) view1.findViewById(com.example.khanhpd.khanhpdph05706_ass.R.id.edNgayNhap);
                btnChonNgayNhap = (Button) view1.findViewById(com.example.khanhpd.khanhpdph05706_ass.R.id.btnChonNgayNhap);


                final Calendar calendar = Calendar.getInstance();
                Nam = calendar.get(Calendar.YEAR);
                Thang = calendar.get(Calendar.MONTH);
                Ngay = calendar.get(Calendar.DAY_OF_MONTH);

                btnChonNgayNhap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                edNgayNhap.setText(dayOfMonth + "/" + month + "/" + year);
                            }
                        }, Nam, Thang, Ngay);
                        datePickerDialog.show();
                    }
                });

                btnChonNgayXuat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                edNgayXuat.setText(dayOfMonth + "/" + month + "/" + year);
                            }
                        }, Nam, Thang, Ngay);
                        datePickerDialog.show();
                    }
                });

                dialog.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MaSoHH = edMaSo.getText().toString();
                        TenHH = edTenHangHoa.getText().toString();
                        LoaiHang = spLoaiHang.getSelectedItem().toString();
                        Loai = spLoai.getSelectedItem().toString();
                        NgayXuat = edNgayXuat.getText().toString();
                        NgayNhap = edNgayNhap.getText().toString();

                        if (MaSoHH.isEmpty() || TenHH.isEmpty() || NgayXuat.isEmpty() || NgayNhap.isEmpty()) {
                            Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                        } else {
                            if (db.checkMaHangHoa(MaSoHH) == false) {
                                Toast.makeText(getContext(), "Mã hàng hoá bị trùng", Toast.LENGTH_SHORT).show();
                            } else {
                                spSapXepHangHoa.setSelection(0);
                                addHangHoa(MaSoHH, TenHH, LoaiHang, Loai, NgayXuat, NgayNhap);
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

        lvHangHoaAdapter = new LvHangHoaAdapter(getContext(), hangHoas);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewHangHoa.setLayoutManager(layoutManager);
        recyclerViewHangHoa.setItemAnimator(new DefaultItemAnimator());
        recyclerViewHangHoa.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerViewHangHoa.setAdapter(lvHangHoaAdapter);

        getHangHoa(true, null);

        spSapXepHangHoa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                viTri1 = spSapXepHangHoa.getSelectedItem().toString();
                if (spSapXepHangHoa.getSelectedItemPosition() >= 1) {
                    getHangHoa(false, viTri1);
                } else {
                    getHangHoa(true, null);
                    lvHangHoaAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new DanhSachHangHoa.FetchFeedTask().execute((Void) null);
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
            if (hangHoas.size() == 0)
                return false;

            viTri1 = spSapXepHangHoa.getSelectedItem().toString();
            if (spSapXepHangHoa.getSelectedItemPosition() >= 1) {
                if (db.getCongNhanTheoViTri(viTri1) != null) {
                    hangHoas.clear();
                    hangHoas.addAll(db.getHangHoaTheoLoai(viTri1));
                } else {
                    Toast.makeText(getContext(), "Không có kết quả phù hợp", Toast.LENGTH_SHORT).show();
                }
            } else {
                getHangHoa(true, null);
            }

            return false;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            refreshLayout.setRefreshing(false);
        }
    }

    private void addHangHoa(String MaSoHH, String TenHH, String LoaiHang, String Loai, String NgayXuat, String NgayNhap) {
        long id = db.insertHangHoa(MaSoHH, TenHH, LoaiHang, Loai, NgayXuat, NgayNhap);

        HangHoa hangHoa = db.getHangHoa(id);

        if (hangHoa != null) {
            hangHoas.add(0, hangHoa);
            lvHangHoaAdapter.notifyDataSetChanged();
        }
    }

    public void getHangHoa(boolean check, String viTri) {
        if (check == true) {
            hangHoas.clear();
            hangHoas.addAll(db.getALLHangHoa());
        } else if (check == false) {
            if (db.getHangHoaTheoLoai(viTri) != null) {
                hangHoas.clear();
                hangHoas.addAll(db.getHangHoaTheoLoai(viTri));
                lvHangHoaAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getContext(), "Không có kết quả phù hợp", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
