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
    private TextView totalFeeTxt, taxTxt, deliveryTxt, totalTxt;

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

        // Khởi tạo các thành phần giao diện
        cartView = findViewById(R.id.cartView);
        backBtn = findViewById(R.id.backBtn);
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        deliveryTxt = findViewById(R.id.deliveryTxt);
        totalTxt = findViewById(R.id.totalTxt);

        // Đặt LayoutManager cho RecyclerView
        cartView.setLayoutManager(new LinearLayoutManager(this));

        // Khởi tạo adapter cho RecyclerView
        cartAdapter = new CartAdapter(cartItems, new CartAdapter.CartUpdateListener() {
            @Override
            public void onCartUpdated() {
                saveCartItems();
                updateTotalValues(); // Cập nhật giá trị tổng khi giỏ hàng thay đổi
            }
        });
        cartView.setAdapter(cartAdapter);

        // Tải giỏ hàng từ SharedPreferences
        loadCartItems();

        // Kiểm tra và hiển thị thông báo khi giỏ hàng trống
        if (cartItems.isEmpty()) {
            findViewById(R.id.emptyTxt).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.emptyTxt).setVisibility(View.GONE);
        }

        // Xử lý sự kiện nút "Quay lại"
        backBtn.setOnClickListener(view -> {
            saveCartItems(); // Lưu lại giỏ hàng khi quay lại
            Intent intent = new Intent(CartActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            Toast.makeText(CartActivity.this, "Quay lại MainActivity", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            finish();
        });

        // Xử lý sự kiện nút "Đặt hàng"
        AppCompatButton placeOrderButton = findViewById(R.id.button2);
        placeOrderButton.setOnClickListener(view -> {
            // Chuyển sang màn hình đặt hàng thành công
            Intent intent = new Intent(CartActivity.this, Dathangthanhcong.class);
            startActivity(intent);
        });

        // Cập nhật lại tổng giá trị khi mở màn hình
        updateTotalValues();
    }

    private void updateTotalValues() {
        // Cập nhật tổng giá trị của món ăn, phí vận chuyển và tổng tiền
        double totalFee = calculateTotalFee();
        totalFeeTxt.setText(String.format("%.3f VND", totalFee));

        double deliveryFee = 10; // Phí vận chuyển cố định
        deliveryTxt.setText(String.format("%.3f VND", deliveryFee));

        double total = totalFee + deliveryFee; // Tổng tiền = tổng giá trị món ăn + phí vận chuyển
        totalTxt.setText(String.format("%.3f VND", total));
    }

    // Hàm để tính tổng giá trị của các món trong giỏ hàng
    private double calculateTotalFee() {
        double totalFee = 0.0;
        for (CartItem item : cartItems) {
            totalFee += item.getTotalPrice(); // Cộng tổng giá của mỗi món
        }
        return totalFee;
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
}
