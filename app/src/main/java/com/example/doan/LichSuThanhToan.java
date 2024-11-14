package com.example.doan;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class LichSuThanhToan extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ActivityAdapter adapter;
    private List<ActivityItem> activityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_thanh_toan);  // Đảm bảo layout là đúng

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Dữ liệu mẫu
        activityList = new ArrayList<>();
        activityList.add(new ActivityItem("Kings Cart Coffee - Bishan Public Library", "27 Dec 2022, 5:03 PM", "$7.50"));
        activityList.add(new ActivityItem("Tip to driver", "27 Dec 2022, 4:12 PM", "$2.00"));
        activityList.add(new ActivityItem("Ride to Bishan Public Library", "27 Dec 2022, 3:53 PM", "$10.80"));
        activityList.add(new ActivityItem("Top-up to GrabPay Wallet", "27 Dec 2022, 3:10 PM", "$10.00"));

        adapter = new ActivityAdapter(activityList);
        recyclerView.setAdapter(adapter);
    }
}
