package com.example.khanhpd.khanhpdph05706_ass.adapter;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import com.example.khanhpd.khanhpdph05706_ass.ItemClickListener;
import com.example.khanhpd.khanhpdph05706_ass.R;
import com.example.khanhpd.khanhpdph05706_ass.database.OpenHelper;
import com.example.khanhpd.khanhpdph05706_ass.database.model.CongNhan;

public class LvCongNhanAdapter extends RecyclerView.Adapter<LvCongNhanAdapter.RecyclerViewHolder> {
    private Context context;
    private List<CongNhan> congNhans;
    private OpenHelper db;
    private EditText edMaCongNhan;
    private EditText edTenCongNhan;
    private Spinner spGioiTinh;
    private EditText edNgaySinh;
    private Spinner spQue;
    private Spinner spVitri;
    private Button btnChonNgaySinh;
    private int Nam, Thang, Ngay;

    private String MaSoCN, TenCN, GioiTinh, NgaySinh, Que, ViTri;

    public LvCongNhanAdapter(Context context, List<CongNhan> congNhans) {
        this.context = context;
        this.congNhans = congNhans;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_ds_cong_nhan, viewGroup, false);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder recyclerViewHolder, final int i) {
        final CongNhan congNhan = congNhans.get(i);

        db = new OpenHelper(context);

        recyclerViewHolder.tvMaSoCongNhan1.setText(congNhan.getMaSoCN());
        recyclerViewHolder.tvTenCongNhan1.setText(congNhan.getTenCn());

        recyclerViewHolder.setItemClickListener(new ItemClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                    showDialogChon(position);
                } else {
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                    final View view1 = View.inflate(context, R.layout.dialog_chi_tiet_cong_nhan, null);
                    dialog.setView(view1);

                    TextView tvMaSoCongNhan;
                    TextView tvTenCongNhan;
                    TextView tvGioiTinh;
                    TextView tvTuoi;
                    TextView tvQue;
                    TextView tvVitri;

                    tvMaSoCongNhan = (TextView) view1.findViewById(R.id.tvMaSoCongNhan);
                    tvTenCongNhan = (TextView) view1.findViewById(R.id.tvTenCongNhan);
                    tvGioiTinh = (TextView) view1.findViewById(R.id.tvGioiTinh);
                    tvTuoi = (TextView) view1.findViewById(R.id.tvTuoi);
                    tvQue = (TextView) view1.findViewById(R.id.tvQue);
                    tvVitri = (TextView) view1.findViewById(R.id.tvVitri);

                    tvMaSoCongNhan.setText(congNhan.getMaSoCN());
                    tvTenCongNhan.setText(congNhan.getTenCn());
                    tvGioiTinh.setText(congNhan.getGioiTinh());
                    tvTuoi.setText(congNhan.getNgaySinh());
                    tvQue.setText(congNhan.getQue());
                    tvVitri.setText(congNhan.getViTri());

                    dialog.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    dialog.setTitle("Thông tin");
                    dialog.show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return congNhans.size();
    }

    private void showDialogChon(final int i) {
        CharSequence chon[] = new CharSequence[]{"Sửa", "Xoá"};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Chọn chức năng");
        builder.setItems(chon, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    dialogUpdate(i);
                } else {
                    deleteCongNhan(i);
                }
            }
        });
        builder.show();
    }

    private void updateCongNhan(String MaSoCN, String TenCN, String GioiTinh, String NgaySinh, String Que, String ViTri, int i) {
        CongNhan congNhan = congNhans.get(i);

        congNhan.setMaSoCN(MaSoCN);
        congNhan.setTenCn(TenCN);
        congNhan.setGioiTinh(GioiTinh);
        congNhan.setNgaySinh(NgaySinh);
        congNhan.setQue(Que);
        congNhan.setViTri(ViTri);

        db.updateCongNhan(congNhan);

        congNhans.set(i, congNhan);
    }

    private void deleteCongNhan(int i) {
        db.deleteCongNhan(congNhans.get(i));

        congNhans.remove(i);
        notifyDataSetChanged();
    }

    private void dialogUpdate(final int position) {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        View view1 = LayoutInflater.from(context).inflate(R.layout.dialog_them_cong_nhan, null);
        dialog.setView(view1);
        dialog.setCancelable(false);

        edMaCongNhan = (EditText) view1.findViewById(R.id.edMaCongNhan);
        edTenCongNhan = (EditText) view1.findViewById(R.id.edTenCongNhan);
        spGioiTinh = (Spinner) view1.findViewById(R.id.spGioiTinh);
        edNgaySinh = (EditText) view1.findViewById(R.id.edNgaySinh);
        spQue = (Spinner) view1.findViewById(R.id.spQue);
        spVitri = (Spinner) view1.findViewById(R.id.spVitri);
        btnChonNgaySinh = (Button) view1.findViewById(R.id.btnChonNgaySinh);

        edMaCongNhan.setText(congNhans.get(position).getMaSoCN());
        edTenCongNhan.setText(congNhans.get(position).getTenCn());
        edNgaySinh.setText(congNhans.get(position).getNgaySinh());

        String[] listGioiTinh = context.getResources().getStringArray(R.array.GioiTinh);
        String[] listViTri = context.getResources().getStringArray(R.array.ViTri);
        String[] listQue = context.getResources().getStringArray(R.array.Que);

        for (int i = 0; i < listQue.length - 1; i++) {
            if (congNhans.get(position).getQue().equalsIgnoreCase(listQue[i]))
                spQue.setSelection(i);
        }

        for (int i = 0; i < listGioiTinh.length - 1; i++) {
            if (congNhans.get(position).getGioiTinh().equalsIgnoreCase(listGioiTinh[i]))
                spGioiTinh.setSelection(i);
        }

        for (int i = 0; i < listViTri.length; i++) {
            if (congNhans.get(position).getViTri().equalsIgnoreCase(listViTri[i]))
                spVitri.setSelection(i);
        }


        final Calendar calendar = Calendar.getInstance();
        Nam = calendar.get(Calendar.YEAR);
        Thang = calendar.get(Calendar.MONTH);
        Ngay = calendar.get(Calendar.DAY_OF_MONTH);

        btnChonNgaySinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edNgaySinh.setText(dayOfMonth + "/" + month + "/" + year);
                    }
                }, Nam, Thang, Ngay);
                datePickerDialog.show();
            }
        });

        dialog.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MaSoCN = edMaCongNhan.getText().toString();
                TenCN = edTenCongNhan.getText().toString();
                GioiTinh = spGioiTinh.getSelectedItem().toString();
                NgaySinh = edNgaySinh.getText().toString();
                Que = spQue.getSelectedItem().toString();
                ViTri = spVitri.getSelectedItem().toString();

                if (MaSoCN.isEmpty() || TenCN.isEmpty() || GioiTinh.isEmpty() || NgaySinh.isEmpty() || Que.isEmpty() || ViTri.isEmpty()) {
                    Toast.makeText(context, "Bạn không được để trống", Toast.LENGTH_SHORT).show();
                } else {
                    updateCongNhan(MaSoCN, TenCN, GioiTinh, NgaySinh, Que, ViTri, position);
                    notifyDataSetChanged();
                }
            }
        });

        dialog.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.setCancelable(true);
            }
        });

        AlertDialog dialog1 = dialog.create();
        dialog1.setTitle("Sửa thông tin");
        dialog1.show();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView tvMaSoCongNhan1;
        public TextView tvTenCongNhan1;
        private ItemClickListener itemClickListener;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMaSoCongNhan1 = (TextView) itemView.findViewById(R.id.tvMaSoCongNhan1);
            tvTenCongNhan1 = (TextView) itemView.findViewById(R.id.tvTenCongNhan1);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), true);
            return true;
        }
    }
}
