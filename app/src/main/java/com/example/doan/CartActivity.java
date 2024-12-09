package com.example.doan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.Adapter.CartAdapter;
import com.example.doan.Domain.CartItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView cartView;
    private CartAdapter cartAdapter;
    private ImageView backBtn;
    private TextView totalFeeTxt,taxTxt,deliveryTxt,totalTxt;

    // Tên file SharedPreferences
    private static final String PREFS_NAME = "CartPrefs";
    // Khóa để lưu trữ dữ liệu giỏ hàng
    private static final String KEY_CART_ITEMS = "cart_items";

    // Danh sách giỏ hàng
    public static List<CartItem> cartItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartView = findViewById(R.id.cartView);
        cartView.setLayoutManager(new LinearLayoutManager(this));
        cartAdapter = new CartAdapter(cartItems, new CartAdapter.CartUpdateListener() {
            @Override
            public void onCartUpdated() {
                saveCartItems();
            }
        });
        cartView.setAdapter(cartAdapter);

        loadCartItems();

        if (cartItems.isEmpty()) {
            findViewById(R.id.emptyTxt).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.emptyTxt).setVisibility(View.GONE);
        }

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveCartItems();
                Intent intent = new Intent(CartActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                Toast.makeText(CartActivity.this, "Quay lại MainActivity", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });

        // Lấy tham chiếu đến nút "Place Order"
        AppCompatButton placeOrderButton = findViewById(R.id.button2);

        // Thiết lập sự kiện OnClick cho nút "Place Order"
        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang Dathangthangcong
                Intent intent = new Intent(CartActivity.this, Dathangthanhcong.class);
                startActivity(intent);
            }
        });

        updateTotalValues();
    }



    private void updateTotalValues() {
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        double totalFee = calculateTotalFee();
        totalFeeTxt.setText(String.format("%.3f VND", totalFee));

        deliveryTxt = findViewById(R.id.deliveryTxt);
        double deliveryFee = 10;
        deliveryTxt.setText(String.format("%.3f VND", deliveryFee));

        totalTxt = findViewById(R.id.totalTxt);
        double total = totalFee + deliveryFee; // Tổng tiền = totalFee + tax + deliveryFee
        totalTxt.setText(String.format("%.3f VND", total));
    }



    // Hàm để lưu giỏ hàng vào SharedPreferences
    private void saveCartItems() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(cartItems);
        editor.putString(KEY_CART_ITEMS, json);
        editor.apply(); // Lưu dữ liệu vào SharedPreferences
    }



    // Hàm để tải giỏ hàng từ SharedPreferences
    private void loadCartItems() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_CART_ITEMS, null);
        Type type = new TypeToken<ArrayList<CartItem>>() {}.getType();
        cartItems = gson.fromJson(json, type);

        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }
    }

    private double calculateTotalFee() {
        double totalFee = 0.0;
        for (CartItem item : cartItems) {
            totalFee += item.getTotalPrice(); // Cộng tổng giá của mỗi món
        }
        return totalFee;
    }

    private double calculateTax(double totalFee) {
        return totalFee * 0.02; // 10% của tổng giá trị
    }


}