package com.example.doan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.Adapter.CategoryAdapter;
import com.example.doan.Adapter.FoodAdapter;
import com.example.doan.Domain.Category;
import com.example.doan.Domain.CartItem;
import com.example.doan.Domain.Foods;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Spinner locationSp;
    private Spinner timeSp;
    private Spinner printSP;
    private RecyclerView rcyFood, rcyCategory;
    private FoodAdapter foodAdapter;
    private CategoryAdapter categoryAdapter;
    private ImageView cartBtn,logoutBtn;

    // Tên file SharedPreferences
    private static final String PREFS_NAME = "CartPrefs";
    private static final String KEY_CART_ITEMS = "cart_items";
    public static List<CartItem> cartItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationSp = findViewById(R.id.locationSp);
        timeSp = findViewById(R.id.timeSp);
        printSP = findViewById(R.id.printSP);

        rcyFood = findViewById(R.id.bestFoodView);
        foodAdapter = new FoodAdapter();

        rcyCategory = findViewById(R.id.recyclerView2);
        categoryAdapter = new CategoryAdapter(this);

        List<Foods> foodList = getListFood();
        List<Category> categoryList = getListCategory();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        rcyFood.setLayoutManager(linearLayoutManager);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        rcyCategory.setLayoutManager(gridLayoutManager);

        rcyCategory.setAdapter(categoryAdapter);
        categoryAdapter.setData(categoryList);

        foodAdapter.setData(foodList);

        // Set adapter cho RecyclerView
        rcyFood.setAdapter(foodAdapter);

        // Tạo danh sách dữ liệu cho Spinner
        List<String> list1 = new ArrayList<>();
        list1.add("Quảng Nam");
        list1.add("Đà Nẵng");

        List<String> list2 = new ArrayList<>();
        list2.add("0-10 phút");
        list2.add("10-30 phút");
        list2.add("hơn 30 phút");

        List<String> list3 = new ArrayList<>();
        list3.add("1$-10$");
        list3.add("10$-100$");
        list3.add("hơn 100$");

        // Tạo ArrayAdapter để kết nối dữ liệu với Spinner
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list3);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        locationSp.setAdapter(adapter1);
        timeSp.setAdapter(adapter2);
        printSP.setAdapter(adapter3);

        locationSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Hiển thị item đã chọn bằng Toast
                Toast.makeText(MainActivity.this, locationSp.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Không làm gì khi không có item nào được chọn
            }
        });

        timeSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Toast.makeText(MainActivity.this, timeSp.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        printSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Toast.makeText(MainActivity.this, printSP.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        cartBtn = findViewById(R.id.cartBtn);
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tạo Intent để chuyển sang CartActivity
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

        logoutBtn=findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tạo Intent để chuyển sang CartActivity
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
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

    @Override
    protected void onResume() {
        super.onResume();
        // Tải lại dữ liệu giỏ hàng khi MainActivity được hiển thị trở lại
        loadCartItems();
    }

    private List<Foods> getListFood(){
        List<Foods> list = new ArrayList<>();
        list.add(new Foods(10,R.drawable.bach,4.9,10,"Bacon and Cheese Heaven"));
        list.add(new Foods(10.99,R.drawable.margherita,4.9,11,"Margherita"));
        list.add(new Foods(10,R.drawable.koreanbbqshortribs,4.9,10,"Korean BBQ Short Ribs"));
        list.add(new Foods(10,R.drawable.chicagostylehotdog,4.9,10,"Chicago Style Hot Dog"));

        return list;
    }

    private List<Category> getListCategory(){
        List<Category> list = new ArrayList<>();
        list.add(new Category(R.drawable.btn_1,"Pizza"));
        list.add(new Category(R.drawable.btn_2,"Burger"));
        list.add(new Category(R.drawable.btn_3,"Chicken"));
        list.add(new Category(R.drawable.btn_4,"Shushi"));
        list.add(new Category(R.drawable.btn_5,"Meat"));
        list.add(new Category(R.drawable.btn_6,"HotDog"));
        list.add(new Category(R.drawable.btn_7,"Drink"));
        list.add(new Category(R.drawable.btn_8,"More"));

        return list;
    }
}
