package com.example.khanhpd.khanhpdph05706_ass.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import com.example.khanhpd.khanhpdph05706_ass.database.model.CongNhan;
import com.example.khanhpd.khanhpdph05706_ass.database.model.HangHoa;

public class OpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "QuanLyHangHoa";

    public OpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CongNhan.CREATE_CongNhan);
        db.execSQL(HangHoa.CREATE_HangHoa);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CongNhan.TABLE_CongNhan);
        db.execSQL("DROP TABLE IF EXISTS " + HangHoa.TABLE_HangHoa);

        onCreate(db);
    }

    public long insertCongNhan(String MaSoCN, String TenCN, String GioiTinh, String NgaySinh, String Que, String ViTri){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CongNhan.COLUMN_MaSoCN, MaSoCN);
        values.put(CongNhan.COLUMN_TenCN, TenCN);
        values.put(CongNhan.COLUMN_GioiTinh, GioiTinh);
        values.put(CongNhan.COLUMN_NgaySinh, NgaySinh);
        values.put(CongNhan.COLUMN_Que, Que);
        values.put(CongNhan.COLUMN_Vitri, ViTri);

        long id = db.insert(CongNhan.TABLE_CongNhan, null, values);

        db.close();

        return id;
    }

    public CongNhan getCongNhan(long id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(CongNhan.TABLE_CongNhan,
                new String[]{CongNhan.COLUMN_ID, CongNhan.COLUMN_MaSoCN, CongNhan.COLUMN_TenCN, CongNhan.COLUMN_GioiTinh, CongNhan.COLUMN_NgaySinh, CongNhan.COLUMN_Que, CongNhan.COLUMN_Vitri},
                CongNhan.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        CongNhan congNhan = new CongNhan(
                cursor.getInt(cursor.getColumnIndex(CongNhan.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(CongNhan.COLUMN_MaSoCN)),
                cursor.getString(cursor.getColumnIndex(CongNhan.COLUMN_TenCN)),
                cursor.getString(cursor.getColumnIndex(CongNhan.COLUMN_GioiTinh)),
                cursor.getString(cursor.getColumnIndex(CongNhan.COLUMN_NgaySinh)),
                cursor.getString(cursor.getColumnIndex(CongNhan.COLUMN_Que)),
                cursor.getString(cursor.getColumnIndex(CongNhan.COLUMN_Vitri))
        );

        cursor.close();

        return congNhan;
    }

    public List<CongNhan> getALLCongNhan(){
        List<CongNhan> congNhans = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + CongNhan.TABLE_CongNhan;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                CongNhan congNhan = new CongNhan();
                congNhan.setId(cursor.getInt(cursor.getColumnIndex(CongNhan.COLUMN_ID)));
                congNhan.setMaSoCN(cursor.getString(cursor.getColumnIndex(CongNhan.COLUMN_MaSoCN)));
                congNhan.setTenCn(cursor.getString(cursor.getColumnIndex(CongNhan.COLUMN_TenCN)));
                congNhan.setGioiTinh(cursor.getString(cursor.getColumnIndex(CongNhan.COLUMN_GioiTinh)));
                congNhan.setNgaySinh(cursor.getString(cursor.getColumnIndex(CongNhan.COLUMN_NgaySinh)));
                congNhan.setQue(cursor.getString(cursor.getColumnIndex(CongNhan.COLUMN_Que)));
                congNhan.setViTri(cursor.getString(cursor.getColumnIndex(CongNhan.COLUMN_Vitri)));

                congNhans.add(congNhan);
            } while (cursor.moveToNext());
        }
        db.close();

        return congNhans;
    }

    public List<CongNhan> getCongNhanTheoViTri(String ViTri){
        List<CongNhan> congNhans = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + CongNhan.TABLE_CongNhan + " WHERE " + CongNhan.COLUMN_Vitri + " = '" + ViTri + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                CongNhan congNhan = new CongNhan();
                congNhan.setId(cursor.getInt(cursor.getColumnIndex(CongNhan.COLUMN_ID)));
                congNhan.setMaSoCN(cursor.getString(cursor.getColumnIndex(CongNhan.COLUMN_MaSoCN)));
                congNhan.setTenCn(cursor.getString(cursor.getColumnIndex(CongNhan.COLUMN_TenCN)));
                congNhan.setGioiTinh(cursor.getString(cursor.getColumnIndex(CongNhan.COLUMN_GioiTinh)));
                congNhan.setNgaySinh(cursor.getString(cursor.getColumnIndex(CongNhan.COLUMN_NgaySinh)));
                congNhan.setQue(cursor.getString(cursor.getColumnIndex(CongNhan.COLUMN_Que)));
                congNhan.setViTri(cursor.getString(cursor.getColumnIndex(CongNhan.COLUMN_Vitri)));

                congNhans.add(congNhan);
            } while (cursor.moveToNext());
        }
        db.close();

        return congNhans;
    }

    public int getTongSoCongNhan(){
        String countQuery = "SELECT * FROM " + CongNhan.TABLE_CongNhan;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    public int getSoCongNhanNhapLieu(){
        String countQuery = "SELECT * FROM " + CongNhan.TABLE_CongNhan + " WHERE " + CongNhan.COLUMN_Vitri + " = 'Nhập liệu'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    public int getSoCongNhanLaiXe(){
        String countQuery = "SELECT * FROM " + CongNhan.TABLE_CongNhan + " WHERE " + CongNhan.COLUMN_Vitri + " = 'Lái xe'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    public int getSoCongNhanXepHang(){
        String countQuery = "SELECT * FROM " + CongNhan.TABLE_CongNhan + " WHERE " + CongNhan.COLUMN_Vitri + " = 'Xếp hàng'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    public boolean checkMaCongNhan(String MaSo){
        String countQuery = "SELECT * FROM " + CongNhan.TABLE_CongNhan + " WHERE " + CongNhan.COLUMN_MaSoCN + " = '" + MaSo + "'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);

        boolean check;
        if (cursor.getCount() == 0){
            check = true;
        }else {
            check = false;
        }
        cursor.close();

        return check;
    }

    public int updateCongNhan(CongNhan congNhan){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CongNhan.COLUMN_MaSoCN, congNhan.getMaSoCN());
        values.put(CongNhan.COLUMN_TenCN, congNhan.getTenCn());
        values.put(CongNhan.COLUMN_GioiTinh, congNhan.getGioiTinh());
        values.put(CongNhan.COLUMN_NgaySinh, congNhan.getNgaySinh());
        values.put(CongNhan.COLUMN_Que, congNhan.getQue());
        values.put(CongNhan.COLUMN_Vitri, congNhan.getViTri());

        return db.update(CongNhan.TABLE_CongNhan, values, CongNhan.COLUMN_ID + "=?", new String[]{String.valueOf(congNhan.getId())});
    }

    public void deleteCongNhan(CongNhan congNhan){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(CongNhan.TABLE_CongNhan, CongNhan.COLUMN_ID + "=?", new String[]{String.valueOf(congNhan.getId())});
        db.close();
    }

    public long insertHangHoa(String MaSoHH, String TenHH, String LoaiHang, String Loai, String NgayXuat, String NgayNhap){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HangHoa.COLUMN_MaSoHH, MaSoHH);
        values.put(HangHoa.COLUMN_TenHH, TenHH);
        values.put(HangHoa.COLUMN_LoaiHang, LoaiHang);
        values.put(HangHoa.COLUMN_Loai, Loai);
        values.put(HangHoa.COLUMN_NgayXuat, NgayXuat);
        values.put(HangHoa.COLUMN_NgayNhap, NgayNhap);

        long id = db.insert(HangHoa.TABLE_HangHoa, null, values);

        db.close();

        return id;
    }

    public HangHoa getHangHoa(long id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(HangHoa.TABLE_HangHoa,
                new String[]{HangHoa.COLUMN_ID, HangHoa.COLUMN_MaSoHH, HangHoa.COLUMN_TenHH, HangHoa.COLUMN_LoaiHang, HangHoa.COLUMN_Loai, HangHoa.COLUMN_NgayXuat, HangHoa.COLUMN_NgayNhap},
                HangHoa.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        HangHoa hangHoa = new HangHoa(
                cursor.getInt(cursor.getColumnIndex(HangHoa.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(HangHoa.COLUMN_MaSoHH)),
                cursor.getString(cursor.getColumnIndex(HangHoa.COLUMN_TenHH)),
                cursor.getString(cursor.getColumnIndex(HangHoa.COLUMN_LoaiHang)),
                cursor.getString(cursor.getColumnIndex(HangHoa.COLUMN_Loai)),
                cursor.getString(cursor.getColumnIndex(HangHoa.COLUMN_NgayXuat)),
                cursor.getString(cursor.getColumnIndex(HangHoa.COLUMN_NgayNhap))
        );

        cursor.close();

        return hangHoa;
    }

    public List<HangHoa> getALLHangHoa(){
        List<HangHoa> HangHoas = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + HangHoa.TABLE_HangHoa;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                HangHoa hangHoa = new HangHoa();
                hangHoa.setId(cursor.getInt(cursor.getColumnIndex(HangHoa.COLUMN_ID)));
                hangHoa.setMaSoHH(cursor.getString(cursor.getColumnIndex(HangHoa.COLUMN_MaSoHH)));
                hangHoa.setTenHH(cursor.getString(cursor.getColumnIndex(HangHoa.COLUMN_TenHH)));
                hangHoa.setLoaiHang(cursor.getString(cursor.getColumnIndex(HangHoa.COLUMN_LoaiHang)));
                hangHoa.setLoai(cursor.getString(cursor.getColumnIndex(HangHoa.COLUMN_Loai)));
                hangHoa.setNgayXuat(cursor.getString(cursor.getColumnIndex(HangHoa.COLUMN_NgayXuat)));
                hangHoa.setNgayNhap(cursor.getString(cursor.getColumnIndex(HangHoa.COLUMN_NgayNhap)));

                HangHoas.add(hangHoa);
            } while (cursor.moveToNext());
        }
        db.close();

        return HangHoas;
    }

    public List<HangHoa> getHangHoaTheoLoai(String Loai) {
        List<HangHoa> HangHoas = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + HangHoa.TABLE_HangHoa + " WHERE " + HangHoa.COLUMN_Loai + " = '" + Loai + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HangHoa hangHoa = new HangHoa();
                hangHoa.setId(cursor.getInt(cursor.getColumnIndex(HangHoa.COLUMN_ID)));
                hangHoa.setMaSoHH(cursor.getString(cursor.getColumnIndex(HangHoa.COLUMN_MaSoHH)));
                hangHoa.setTenHH(cursor.getString(cursor.getColumnIndex(HangHoa.COLUMN_TenHH)));
                hangHoa.setLoaiHang(cursor.getString(cursor.getColumnIndex(HangHoa.COLUMN_LoaiHang)));
                hangHoa.setLoai(cursor.getString(cursor.getColumnIndex(HangHoa.COLUMN_Loai)));
                hangHoa.setNgayXuat(cursor.getString(cursor.getColumnIndex(HangHoa.COLUMN_NgayXuat)));
                hangHoa.setNgayNhap(cursor.getString(cursor.getColumnIndex(HangHoa.COLUMN_NgayNhap)));

                HangHoas.add(hangHoa);
            } while (cursor.moveToNext());
        }
        db.close();

        return HangHoas;
    }

    public int getTongSoHangHoa(){
        String countQuery = "SELECT * FROM " + HangHoa.TABLE_HangHoa;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    public int getTongSoHangHoaXuatKhau() {
        String countQuery = "SELECT * FROM " + HangHoa.TABLE_HangHoa + " WHERE " + HangHoa.COLUMN_LoaiHang + " = 'Xuất khẩu'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    public int getTongSoHangHoaNhapKhau() {
        String countQuery = "SELECT * FROM " + HangHoa.TABLE_HangHoa + " WHERE " + HangHoa.COLUMN_Loai + " = 'Nhập khẩu'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    public int getSoLoaiA(){
        String countQuery = "SELECT * FROM " + HangHoa.TABLE_HangHoa + " WHERE " + HangHoa.COLUMN_Loai + " = 'Loại A'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    public int getSoLoaiB(){
        String countQuery = "SELECT * FROM " + HangHoa.TABLE_HangHoa + " WHERE " + HangHoa.COLUMN_Loai + " = 'Loại B'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    public int getSoLoaiC(){
        String countQuery = "SELECT * FROM " + HangHoa.TABLE_HangHoa + " WHERE " + HangHoa.COLUMN_Loai + " = 'Loại C'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    public boolean checkMaHangHoa(String MaSo) {
        String countQuery = "SELECT * FROM " + HangHoa.TABLE_HangHoa + " WHERE " + HangHoa.COLUMN_MaSoHH + " = '" + MaSo + "'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);

        boolean check;
        if (cursor.getCount() == 0) {
            check = true;
        } else {
            check = false;
        }
        cursor.close();

        return check;
    }

    public int updateHangHoa(HangHoa hangHoa){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HangHoa.COLUMN_MaSoHH, hangHoa.getMaSoHH());
        values.put(HangHoa.COLUMN_TenHH, hangHoa.getTenHH());
        values.put(HangHoa.COLUMN_LoaiHang, hangHoa.getLoaiHang());
        values.put(HangHoa.COLUMN_Loai, hangHoa.getLoai());
        values.put(HangHoa.COLUMN_NgayXuat, hangHoa.getNgayXuat());
        values.put(HangHoa.COLUMN_NgayNhap, hangHoa.getNgayNhap());

        return db.update(HangHoa.TABLE_HangHoa, values, HangHoa.COLUMN_ID + "=?", new String[]{String.valueOf(hangHoa.getId())});
    }

    public void deleteHangHoa(HangHoa hangHoa){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(HangHoa.TABLE_HangHoa, HangHoa.COLUMN_ID + "=?", new String[]{String.valueOf(hangHoa.getId())});
        db.close();
    }

    public boolean checkUser(String TenUser, String Password){
        String Name = "admin";
        String Pass = "admin";

        boolean check;

        if (TenUser.equalsIgnoreCase(Name) && Password.equalsIgnoreCase(Pass)){
            check = true;
        }else {
            check = false;
        }

        return check;
    }
}
