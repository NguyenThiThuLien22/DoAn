package com.example.doan;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.Adapter.ActivityAdapter;

import java.util.ArrayList;
import java.util.List;

public class LichSuThanhToan extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ActivityAdapter adapter;
    private List<ActivityItem> activityList;
    private ImageView btnBack, btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_thanh_toan);  // Đảm bảo layout là đúng

        // Tham chiếu các View
        recyclerView = findViewById(R.id.recyclerView);
        btnBack = findViewById(R.id.btnBack);
        btnHome = findViewById(R.id.btnHome);

        // Cấu hình RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Thêm dữ liệu mẫu
        activityList = new ArrayList<>();
        activityList.add(new ActivityItem("Kings Cart Coffee - Bishan Public Library", "27 Dec 2022, 5:03 PM", "$7.50"));
        activityList.add(new ActivityItem("Tip to driver", "27 Dec 2022, 4:12 PM", "$2.00"));
        activityList.add(new ActivityItem("Ride to Bishan Public Library", "27 Dec 2022, 3:53 PM", "$10.80"));
        activityList.add(new ActivityItem("Top-up to GrabPay Wallet", "27 Dec 2022, 3:10 PM", "$10.00"));

        // Gán Adapter
        adapter = new ActivityAdapter(activityList);
        recyclerView.setAdapter(adapter);

        // Xử lý sự kiện nút Back
        btnBack.setOnClickListener(view -> {
            Intent intent = new Intent(LichSuThanhToan.this, MainActivity.class);
            startActivity(intent);
            finish(); // Đóng Activity hiện tại
        });

        // Xử lý sự kiện nút Home
        btnHome.setOnClickListener(view -> {
            Intent intent = new Intent(LichSuThanhToan.this, MainActivity.class);
            startActivity(intent);
            finish(); // Đóng Activity hiện tại
        });
    }
}
