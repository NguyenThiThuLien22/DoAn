package com.example.doan;


import static com.example.doan.MainActivity.cartItems;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
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
        ImageView btnDanhsach = findViewById(R.id.btnDanhsach);

        btnDanhsach.setOnClickListener(view -> {
            // Tạo PopupMenu
            PopupMenu popupMenu = new PopupMenu(Dathangthanhcong.this, btnDanhsach);
            popupMenu.getMenuInflater().inflate(R.menu.menu_banner, popupMenu.getMenu());

            // Xử lý sự kiện click vào các mục menu
            popupMenu.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.menu_home) {
                    // Chuyển sang màn hình Trang chủ
                    Intent homeIntent = new Intent(Dathangthanhcong.this, MainActivity.class);
                    startActivity(homeIntent);
                    return true;
                } else if (item.getItemId() == R.id.menu_account) {
                    // Chuyển sang màn hình Tài khoản
                    Intent accountIntent = new Intent(Dathangthanhcong.this, AccountActivity.class);
                    startActivity(accountIntent);
                    return true;
                } else if (item.getItemId() == R.id.menu_history) {
                    // Chuyển sang màn hình Lịch sử đơn hàng
                    Intent historyIntent = new Intent(Dathangthanhcong.this, LichSuThanhToan.class);
                    startActivity(historyIntent);
                    return true;
                } else {
                    // Trường hợp không phù hợp
                    return false;
                }
            });

            // Hiển thị menu
            popupMenu.show();
        });


    }
}

