package com.example.doan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Dathangthanhcong extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dathangthanhcong);

        // Tìm kiếm nút "Về Trang chủ" bằng ID và đặt sự kiện nhấn
        TextView veTrangChuButton = findViewById(R.id.tichdiem);
        veTrangChuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến TíchDiem
                Intent intent = new Intent(Dathangthanhcong.this, TichDiem.class);
                startActivity(intent);
                finish(); // Kết thúc activity hiện tại
            }
        });

        // Tìm kiếm nút "Đánh giá" bằng ID và đặt sự kiện nhấn
        TextView danhGiaButton = findViewById(R.id.danh_gia_button);
        danhGiaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến màn hình Đánh giá
                Intent intent = new Intent(Dathangthanhcong.this, DanhGia.class);
                startActivity(intent);
            }
        });
    }
}
