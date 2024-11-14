package com.example.doan;

import static com.example.doan.R.id.xemDonHangView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.Adapter.CartAdapter;
import com.example.doan.Domain.CartItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class Xem_don_hangActivity extends AppCompatActivity {


        private RecyclerView xemDonHangView;
        private CartAdapter cartAdapter;
        private List<CartItem> donHangItems;

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_xem_don_hang);

            // Nhận dữ liệu từ Intent
            String cartItemsJson = getIntent().getStringExtra("cart_items");
            donHangItems = new Gson().fromJson(cartItemsJson, new TypeToken<ArrayList<CartItem>>(){}.getType());

            xemDonHangView = findViewById(R.id.xemDonHangView);
            xemDonHangView.setLayoutManager(new LinearLayoutManager(this));

            // Thiết lập adapter
            cartAdapter = new CartAdapter(donHangItems, null);
            xemDonHangView.setAdapter(cartAdapter);
            // Button 'Đã nhận hàng'
            Button buttonReceivedOrder = findViewById(R.id.buttonReceivedOrder);
            buttonReceivedOrder.setOnClickListener(v -> showConfirmationDialog());
        }
    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn đã nhận được hàng?");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            // Handle Yes action
            dialog.dismiss();
            // Code here for what happens after clicking "Yes", such as updating status
        });
        builder.setNegativeButton("No", (dialog, which) -> {
            // Handle No action
            dialog.dismiss();
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

