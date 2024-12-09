package com.example.doan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TichDiem extends AppCompatActivity {

    private ImageView imageLogout;  // Nút logout
    private TextView pointsTextView;  // Hiển thị số điểm

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tich_diem);

        // Tham chiếu đến các view trong layout
        pointsTextView = findViewById(R.id.points_text_view);

        // Nhận dữ liệu từ Intent
        double purchaseAmount = getIntent().getDoubleExtra("purchase_amount", 0.0);

        // Tính điểm dựa trên số tiền mua hàng
        int points = calculatePoints(purchaseAmount);

        // Hiển thị số điểm
        pointsTextView.setText("Số điểm bạn nhận được: " + points);

        // Thông báo thành công
        Toast.makeText(this, "Bạn đã nhận được " + points + " điểm!", Toast.LENGTH_SHORT).show();

        // Xử lý sự kiện nút logout
        imageLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHomePage();
            }
        });
    }

    // Hàm tính điểm dựa trên số tiền mua hàng
    private int calculatePoints(double amount) {
        if (amount > 0 && amount <= 10) {
            return 10;
        } else if (amount > 10 && amount <= 20) {
            return 20;
        } else if (amount > 20 && amount <= 30) {
            return 30;
        } else if (amount > 30) {
            return 50;
        } else {
            return 0;  // Nếu số tiền không hợp lệ
        }
    }

    // Chuyển hướng về trang chính (MainActivity)
    private void goToHomePage() {
        Intent intent = new Intent(TichDiem.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
