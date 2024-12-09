package com.example.doan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class TichDiem extends AppCompatActivity {

    private ImageView imageLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tich_diem);  // Đảm bảo rằng bạn đang set layout đúng

        // Lấy tham chiếu đến image_logout
        imageLogout = findViewById(R.id.image_logout);

        // Đặt sự kiện cho ImageView
        imageLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khi người dùng nhấn vào imageLogout, quay về trang chủ
                goToHomePage();
            }
        });
    }

    // Hàm chuyển hướng về trang chủ (MainActivity)
    private void goToHomePage() {
        Intent intent = new Intent(TichDiem.this, MainActivity.class);  // Chắc chắn MainActivity là trang chủ của bạn
        startActivity(intent);
        finish();  // Kết thúc Activity hiện tại để không quay lại trang này khi nhấn nút back
    }
}