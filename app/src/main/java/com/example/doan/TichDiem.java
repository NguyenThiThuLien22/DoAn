package com.example.doan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TichDiem extends AppCompatActivity {

    private ImageView imageLogout; // Biến cho nút logout
    private TextView pointsTextView; // Biến hiển thị điểm

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tich_diem);

        // Tham chiếu các View trong layout
        pointsTextView = findViewById(R.id.points_text_view); // Hiển thị điểm
        ImageView btnBack = findViewById(R.id.btnBack); // Nút Back
        ImageView btnDanhsach = findViewById(R.id.btnDanhsach); // Nút Danh sách

        // Nhận dữ liệu từ Intent (purchase_amount)
        double purchaseAmount = getIntent().getDoubleExtra("purchase_amount", 0.0);

        // Tính điểm và hiển thị
        int points = calculatePoints(purchaseAmount);
        pointsTextView.setText("Your Points: " + points);
        Toast.makeText(this, "You have earned " + points + " points", Toast.LENGTH_SHORT).show();

        // Xử lý sự kiện nút Back
        btnBack.setOnClickListener(view -> {
            Intent intent = new Intent(TichDiem.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        // Xử lý sự kiện nút Danh sách (PopupMenu)
        btnDanhsach.setOnClickListener(view -> {
            // Tạo PopupMenu
            PopupMenu popupMenu = new PopupMenu(TichDiem.this, btnDanhsach);
            popupMenu.getMenuInflater().inflate(R.menu.menu_banner, popupMenu.getMenu());

            // Xử lý sự kiện click vào các mục menu bằng if-else
            popupMenu.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.menu_home) {
                    // Chuyển sang màn hình Trang chủ
                    Intent homeIntent = new Intent(TichDiem.this, MainActivity.class);
                    startActivity(homeIntent);
                    return true;
                } else if (item.getItemId() == R.id.menu_account) {
                    // Chuyển sang màn hình Tài khoản
                    Intent accountIntent = new Intent(TichDiem.this, AccountActivity.class);
                    startActivity(accountIntent);
                    return true;
                } else if (item.getItemId() == R.id.menu_history) {
                    // Chuyển sang màn hình Lịch sử đơn hàng
                    Intent historyIntent = new Intent(TichDiem.this, LichSuThanhToan.class);
                    startActivity(historyIntent);
                    return true;
                } else {
                    // Nếu không phải các mục trên
                    return false;
                }
            });

            // Hiển thị menu
            popupMenu.show();
        });
    }

    // Hàm tính điểm dựa trên giá trị mua hàng
    private int calculatePoints(double amount) {
        if (amount > 0 && amount <= 10) {
            return 10;
        } else if (amount > 10 && amount <= 20) {
            return 20;
        } else if (amount > 20 && amount <= 30) {
            return 30;
        } else {
            return 50; // Trả về 50 điểm nếu giá trị > 30
        }
    }
}
