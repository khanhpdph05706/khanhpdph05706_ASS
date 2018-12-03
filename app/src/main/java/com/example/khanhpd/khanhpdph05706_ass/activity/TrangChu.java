package com.example.khanhpd.khanhpdph05706_ass.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.khanhpd.khanhpdph05706_ass.R;
import com.example.khanhpd.khanhpdph05706_ass.adapter.ActivityThongKeAdapter;
import com.example.khanhpd.khanhpdph05706_ass.adapter.CongNhanAdapter;
import com.example.khanhpd.khanhpdph05706_ass.adapter.HangHoaAdapter;
import com.example.khanhpd.khanhpdph05706_ass.database.OpenHelper;

public class TrangChu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ViewPager viewPager;
    private CongNhanAdapter congNhanAdapter;
    private HangHoaAdapter hangHoaAdapter;
    private ActivityThongKeAdapter thongKeAdapter;
    private Toolbar toolbar;
    private OpenHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.khanhpd.khanhpdph05706_ass.R.layout.activity_trang_chu);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewPager);

        db = new OpenHelper(getApplicationContext());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.title_open_navigation, com.example.khanhpd.khanhpdph05706_ass.R.string.title_close_navigation);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(com.example.khanhpd.khanhpdph05706_ass.R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        thongKeAdapter = new ActivityThongKeAdapter(getSupportFragmentManager());
        viewPager.setAdapter(thongKeAdapter);
        toolbar.setTitle("Thống kê");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(com.example.khanhpd.khanhpdph05706_ass.R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == com.example.khanhpd.khanhpdph05706_ass.R.id.nav_ThongKe) {
            thongKeAdapter = new ActivityThongKeAdapter(getSupportFragmentManager());
            viewPager.setAdapter(thongKeAdapter);
            toolbar.setTitle("Thống kê");
        } else if (id == com.example.khanhpd.khanhpdph05706_ass.R.id.nav_DsCongNhan) {
            congNhanAdapter = new CongNhanAdapter(getSupportFragmentManager());
            viewPager.setAdapter(congNhanAdapter);
            toolbar.setTitle("Danh sách công nhân");
        } else if (id == com.example.khanhpd.khanhpdph05706_ass.R.id.nav_DsHangHoa) {
            hangHoaAdapter = new HangHoaAdapter(getSupportFragmentManager());
            viewPager.setAdapter(hangHoaAdapter);
            toolbar.setTitle("Danh sách hàng hoá");
        } else if (id == com.example.khanhpd.khanhpdph05706_ass.R.id.nav_GioiThieu) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            View view = getLayoutInflater().inflate(com.example.khanhpd.khanhpdph05706_ass.R.layout.dialog_gioi_thieu, null);
            dialog.setView(view);
            dialog.show();
        } else if (id == com.example.khanhpd.khanhpdph05706_ass.R.id.nav_Logout) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            View view = getLayoutInflater().inflate(com.example.khanhpd.khanhpdph05706_ass.R.layout.dialog_thoat, null);
            dialog.setView(view);

            dialog.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            dialog.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(com.example.khanhpd.khanhpdph05706_ass.R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
