package com.example.doan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doan.Domain.CartItem;
import com.google.gson.Gson;

public class DetailActivity extends AppCompatActivity {
    private View backBtn;
    private TextView titleTextView, priceTxt, timeTxt, totalPriceTxt, numTxt;
    private Button minusBtn, plusBtn,addBtn; // Sử dụng Button thay vì TextView
    private int quantity = 1; // Biến lưu số lượng món ăn
    private double price; // Đưa biến price ra ngoài

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), MainActivity.class);
            view.getContext().startActivity(intent);
        });

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        price = intent.getDoubleExtra("price", 0); // Gán giá trị cho biến price
        int time = intent.getIntExtra("time", 0);
        int imageResourceId = intent.getIntExtra("image", 0);

        // Gán dữ liệu vào các View
        titleTextView = findViewById(R.id.titleTxt);
        titleTextView.setText(title);

        priceTxt = findViewById(R.id.priceTxt);
        priceTxt.setText(String.format("%.2f$", price));

        timeTxt = findViewById(R.id.timeTxt);
        timeTxt.setText(String.valueOf(time));

        ImageView pic = findViewById(R.id.pic);
        pic.setImageResource(imageResourceId);

        minusBtn = findViewById(R.id.minusBtn); // Đúng kiểu dữ liệu Button
        plusBtn = findViewById(R.id.plusBtn); // Đúng kiểu dữ liệu Button
        numTxt = findViewById(R.id.numTxt);
        totalPriceTxt = findViewById(R.id.totalPrice); // Đảm bảo ID chính xác của TextView "totalPrice"

        // Thiết lập giá trị ban đầu
        numTxt.setText(String.valueOf(quantity));
        updateTotalPrice(); // Cập nhật tổng giá ban đầu

        // Xử lý sự kiện click cho nút minus
        minusBtn.setOnClickListener(view -> {
            if (quantity > 1) { // Đảm bảo số lượng không nhỏ hơn 1
                quantity--;
                numTxt.setText(String.valueOf(quantity));
                updateTotalPrice(); // Cập nhật tổng giá sau khi giảm số lượng
            }
        });

        // Xử lý sự kiện click cho nút plus
        plusBtn.setOnClickListener(view -> {
            quantity++;
            numTxt.setText(String.valueOf(quantity));
            updateTotalPrice(); // Cập nhật tổng giá sau khi tăng số lượng
        });

        addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(view -> {
            // Tính tổng tiền cho sản phẩm hiện tại
            double totalPrice = quantity * price;

            // Tạo một đối tượng CartItem với các thuộc tính cần thiết
            CartItem cartItem = new CartItem(title, price, quantity, imageResourceId, totalPrice);

            // Thêm sản phẩm vào danh sách giỏ hàng
            CartActivity.cartItems.add(cartItem);

            // Lưu giỏ hàng vào SharedPreferences để đảm bảo dữ liệu được lưu trữ
            saveCartItems();

            // Hiển thị thông báo rằng sản phẩm đã được thêm vào giỏ hàng
            Toast.makeText(DetailActivity.this, "Đã thêm sản phẩm vào giỏ hàng!", Toast.LENGTH_SHORT).show();
        });
    }

    // Hàm cập nhật tổng giá trị
    private void updateTotalPrice() {
        double totalPrice = quantity * price; // Tính tổng giá trị
        totalPriceTxt.setText(String.format("%.2f$", totalPrice)); // Hiển thị tổng giá trị
    }

    private void saveCartItems() {
        SharedPreferences sharedPreferences = getSharedPreferences("CartPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(CartActivity.cartItems);
        editor.putString("cart_items", json);
        editor.apply();
    }
}
