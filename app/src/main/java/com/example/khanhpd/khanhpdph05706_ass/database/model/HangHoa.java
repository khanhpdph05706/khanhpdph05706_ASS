package com.example.khanhpd.khanhpdph05706_ass.database.model;

public class HangHoa {
    public static final String TABLE_HangHoa = "HangHoa";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_MaSoHH = "MaSo";
    public static final String COLUMN_TenHH = "Ten";
    public static final String COLUMN_LoaiHang = "LoaiHang";
    public static final String COLUMN_Loai = "Loai";
    public static final String COLUMN_NgayXuat = "NgayXuat";
    public static final String COLUMN_NgayNhap = "NgayNhap";

    private int id;
    private String MaSoHH, TenHH, LoaiHang, Loai, NgayXuat, NgayNhap;

    public static final String CREATE_HangHoa = "CREATE TABLE " + TABLE_HangHoa + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_MaSoHH + " TEXT,"
            + COLUMN_TenHH + " TEXT,"
            + COLUMN_LoaiHang + " TEXT,"
            + COLUMN_Loai + " TEXT,"
            + COLUMN_NgayXuat + " TEXT,"
            + COLUMN_NgayNhap + " TEXT"
            + ")";

    public HangHoa(){}

    public HangHoa(int id, String maSoHH, String tenHH, String loaiHang, String loai, String ngayXuat, String ngayNhap) {
        this.id = id;
        MaSoHH = maSoHH;
        TenHH = tenHH;
        LoaiHang = loaiHang;
        Loai = loai;
        NgayXuat = ngayXuat;
        NgayNhap = ngayNhap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaSoHH() {
        return MaSoHH;
    }

    public void setMaSoHH(String maSoHH) {
        MaSoHH = maSoHH;
    }

    public String getTenHH() {
        return TenHH;
    }

    public void setTenHH(String tenHH) {
        TenHH = tenHH;
    }

    public String getLoaiHang() {
        return LoaiHang;
    }

    public void setLoaiHang(String loaiHang) {
        LoaiHang = loaiHang;
    }

    public String getLoai() {
        return Loai;
    }

    public void setLoai(String loai) {
        Loai = loai;
    }

    public String getNgayXuat() {
        return NgayXuat;
    }

    public void setNgayXuat(String ngayXuat) {
        NgayXuat = ngayXuat;
    }

    public String getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        NgayNhap = ngayNhap;
    }
}
