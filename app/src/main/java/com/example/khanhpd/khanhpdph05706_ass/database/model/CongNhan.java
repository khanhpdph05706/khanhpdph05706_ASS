package com.example.khanhpd.khanhpdph05706_ass.database.model;

public class CongNhan {
    public static final String TABLE_CongNhan = "CongNhan";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_MaSoCN = "MaSo";
    public static final String COLUMN_TenCN = "Ten";
    public static final String COLUMN_GioiTinh = "GioiTinh";
    public static final String COLUMN_NgaySinh = "NgaySinh";
    public static final String COLUMN_Que = "Que";
    public static final String COLUMN_Vitri = "ViTri";

    private int id;
    private String MaSoCN, TenCn, GioiTinh, NgaySinh, Que, ViTri;

    public static final String CREATE_CongNhan = "CREATE TABLE " + TABLE_CongNhan + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_MaSoCN + " TEXT,"
            + COLUMN_TenCN + " TEXT,"
            + COLUMN_GioiTinh + " TEXT,"
            + COLUMN_NgaySinh + " TEXT,"
            + COLUMN_Que + " TEXT,"
            + COLUMN_Vitri + " TEXT"
            + ")";

    public CongNhan(){}

    public CongNhan(int id, String maSoCN, String tenCn, String gioiTinh, String ngaySinh, String que, String viTri) {
        this.id = id;
        MaSoCN = maSoCN;
        TenCn = tenCn;
        GioiTinh = gioiTinh;
        NgaySinh = ngaySinh;
        Que = que;
        ViTri = viTri;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaSoCN() {
        return MaSoCN;
    }

    public void setMaSoCN(String maSoCN) {
        MaSoCN = maSoCN;
    }

    public String getTenCn() {
        return TenCn;
    }

    public void setTenCn(String tenCn) {
        TenCn = tenCn;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public String getQue() {
        return Que;
    }

    public void setQue(String que) {
        Que = que;
    }

    public String getViTri() {
        return ViTri;
    }

    public void setViTri(String viTri) {
        ViTri = viTri;
    }
}
