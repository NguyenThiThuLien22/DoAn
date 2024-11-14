package com.example.doan;

import static com.example.doan.CartActivity.cartItems;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

public class Dathangthanhcong extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dathangthanhcong);

        // Tìm kiếm nút "Trang chủ" bằng ID và đặt sự kiện nhấn
        TextView veTrangChuButton = findViewById(R.id.trangchu);
        veTrangChuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến TíchDiem
                Intent intent = new Intent(Dathangthanhcong.this, MainActivity.class);
                startActivity(intent);
                finish(); // Kết thúc activity hiện tại
            }
        });

        // Tìm kiếm nút "Xem đơn hàng" bằng ID và đặt sự kiện nhấn
        TextView xemDonHangButton = findViewById(R.id.xemdonhang);
        xemDonHangButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển dữ liệu giỏ hàng sang XemDonHangActivity
                Intent intent = new Intent(Dathangthanhcong.this, Xem_don_hangActivity.class);
                intent.putExtra("cart_items", new Gson().toJson(cartItems));
                startActivity(intent);
            }
        });

    }
}

