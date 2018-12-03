package com.example.khanhpd.khanhpdph05706_ass.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.khanhpd.khanhpdph05706_ass.database.OpenHelper;

public class DangNhap extends AppCompatActivity {
    private EditText edtTenDangNhap;
    private EditText edtMatKhau;
    private Button btnDangNhap;
    private OpenHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.khanhpd.khanhpdph05706_ass.R.layout.activity_dang_nhap);

        edtTenDangNhap = (EditText) findViewById(com.example.khanhpd.khanhpdph05706_ass.R.id.edtTenDangNhap);
        edtMatKhau = (EditText) findViewById(com.example.khanhpd.khanhpdph05706_ass.R.id.edtMatKhau);
        btnDangNhap = (Button) findViewById(com.example.khanhpd.khanhpdph05706_ass.R.id.btnDangNhap);

        db = new OpenHelper(getApplicationContext());

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (db.checkUser(edtTenDangNhap.getText().toString(), edtMatKhau.getText().toString()) == true){
                    Intent intent = new Intent(DangNhap.this, TrangChu.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(DangNhap.this, "Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
