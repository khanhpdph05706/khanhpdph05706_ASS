package com.example.khanhpd.khanhpdph05706_ass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.khanhpd.khanhpdph05706_ass.activity.DangNhap;
import com.example.khanhpd.khanhpdph05706_ass.R;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, DangNhap.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}
