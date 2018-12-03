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
import com.example.khanhpd.khanhpdph05706_ass.database.model.HangHoa;

public class LvHangHoaAdapter extends RecyclerView.Adapter<LvHangHoaAdapter.RecyclerViewHolder> {
    private Context context;
    private List<HangHoa> hangHoas;
    private OpenHelper db;
    private EditText edMaSo;
    private EditText edTenHangHoa;
    private Spinner spLoaiHang;
    private Spinner spLoai;
    private EditText edNgayXuat;
    private EditText edNgayNhap;
    private Button btnChonNgayXuat;
    private Button btnChonNgayNhap;
    private int Nam, Thang, Ngay;

    private String MaSoHH, TenHH, Loai, LoaiHang, NgayNhap, NgayXuat;

    public LvHangHoaAdapter(Context context, List<HangHoa> hangHoas) {
        this.context = context;
        this.hangHoas = hangHoas;
    }

    @NonNull
    @Override
    public LvHangHoaAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_ds_hang_hoa, viewGroup, false);

        return new LvHangHoaAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final LvHangHoaAdapter.RecyclerViewHolder recyclerViewHolder, final int i) {
        final HangHoa hangHoa = hangHoas.get(i);

        db = new OpenHelper(context);

        recyclerViewHolder.tvMaSoHang1.setText(hangHoa.getMaSoHH());
        recyclerViewHolder.tvTenHang1.setText(hangHoa.getTenHH());

        recyclerViewHolder.setItemClickListener(new ItemClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                    showDialogChon(position);
                } else {
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                    final View view1 = View.inflate(context, R.layout.dialog_chi_tiet_hang_hoa, null);
                    dialog.setView(view1);

                    TextView tvMaSoHang;
                    TextView tvTenHang;
                    TextView tvLoaiHang;
                    TextView tvLoai;
                    TextView tvNgayXuat;
                    TextView tvNgayNhap;

                    tvMaSoHang = (TextView) view1.findViewById(R.id.tvMaSoHang);
                    tvTenHang = (TextView) view1.findViewById(R.id.tvTenHang);
                    tvLoaiHang = (TextView) view1.findViewById(R.id.tvLoaiHang);
                    tvLoai = (TextView) view1.findViewById(R.id.tvLoai);
                    tvNgayXuat = (TextView) view1.findViewById(R.id.tvNgayXuat);
                    tvNgayNhap = (TextView) view1.findViewById(R.id.tvNgayNhap);

                    tvMaSoHang.setText(hangHoa.getMaSoHH());
                    tvTenHang.setText(hangHoa.getTenHH());
                    tvLoaiHang.setText(hangHoa.getLoaiHang());
                    tvLoai.setText(hangHoa.getLoai());
                    tvNgayXuat.setText(hangHoa.getNgayXuat());
                    tvNgayNhap.setText(hangHoa.getNgayNhap());

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
        return hangHoas.size();
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
                    deleteHangHoa(i);
                }
            }
        });
        builder.show();
    }

    private void updateHangHoa(String MaSoHH, String TenHH, String LoaiHang, String Loai, String NgayXuat, String NgayNhap, int i) {
        HangHoa hangHoa = hangHoas.get(i);

        hangHoa.setMaSoHH(MaSoHH);
        hangHoa.setTenHH(TenHH);
        hangHoa.setLoaiHang(LoaiHang);
        hangHoa.setLoai(Loai);
        hangHoa.setNgayXuat(NgayXuat);
        hangHoa.setNgayNhap(NgayNhap);

        db.updateHangHoa(hangHoa);

        hangHoas.set(i, hangHoa);
    }

    private void deleteHangHoa(int i) {
        db.deleteHangHoa(hangHoas.get(i));

        hangHoas.remove(i);
        notifyDataSetChanged();
    }

    private void dialogUpdate(final int position) {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        View view1 = LayoutInflater.from(context).inflate(R.layout.dialog_them_hang_hoa, null);
        dialog.setView(view1);
        dialog.setCancelable(false);

        edMaSo = (EditText) view1.findViewById(R.id.edMaSo);
        edTenHangHoa = (EditText) view1.findViewById(R.id.edTenHangHoa);
        edNgayXuat = (EditText) view1.findViewById(R.id.edNgayXuat);
        edNgayNhap = (EditText) view1.findViewById(R.id.edNgayNhap);
        spLoaiHang = (Spinner) view1.findViewById(R.id.spLoaiHang);
        spLoai = (Spinner) view1.findViewById(R.id.spLoai);
        btnChonNgayXuat = (Button) view1.findViewById(R.id.btnChonNgayXuat);
        btnChonNgayNhap = (Button) view1.findViewById(R.id.btnChonNgayNhap);

        edMaSo.setText(hangHoas.get(position).getMaSoHH());
        edTenHangHoa.setText(hangHoas.get(position).getTenHH());
        edNgayXuat.setText(hangHoas.get(position).getNgayXuat());
        edNgayNhap.setText(hangHoas.get(position).getNgayNhap());

        String[] listLoaiHang = context.getResources().getStringArray(R.array.LoaiHang);
        String[] listLoai = context.getResources().getStringArray(R.array.Loai);

        for (int i = 0; i < listLoaiHang.length - 1; i++) {
            if (hangHoas.get(position).getLoaiHang().equalsIgnoreCase(listLoaiHang[i]))
                spLoaiHang.setSelection(i);
        }

        for (int i = 0; i < listLoai.length - 1; i++) {
            if (hangHoas.get(position).getLoai().equalsIgnoreCase(listLoai[i]))
                spLoai.setSelection(i);
        }


        final Calendar calendar = Calendar.getInstance();
        Nam = calendar.get(Calendar.YEAR);
        Thang = calendar.get(Calendar.MONTH);
        Ngay = calendar.get(Calendar.DAY_OF_MONTH);

        btnChonNgayNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edNgayXuat.setText(dayOfMonth + "/" + month + "/" + year);
                    }
                }, Nam, Thang, Ngay);
                datePickerDialog.show();
            }
        });

        dialog.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MaSoHH = edMaSo.getText().toString();
                TenHH = edTenHangHoa.getText().toString();
                Loai = spLoai.getSelectedItem().toString();
                LoaiHang = spLoaiHang.getSelectedItem().toString();
                NgayNhap = edNgayNhap.getText().toString();
                NgayXuat = edNgayXuat.getText().toString();

                if (MaSoHH.isEmpty() || TenHH.isEmpty() || Loai.isEmpty() || LoaiHang.isEmpty() || NgayNhap.isEmpty() || NgayXuat.isEmpty()) {
                    Toast.makeText(context, "Bạn không được để trống", Toast.LENGTH_SHORT).show();
                } else {
                    updateHangHoa(MaSoHH, TenHH, Loai, LoaiHang, NgayNhap, NgayXuat, position);
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
        public TextView tvMaSoHang1;
        public TextView tvTenHang1;
        private ItemClickListener itemClickListener;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMaSoHang1 = (TextView) itemView.findViewById(R.id.tvMaSoHang1);
            tvTenHang1 = (TextView) itemView.findViewById(R.id.tvTenHang1);

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
