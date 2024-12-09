package com.example.doan;

import static com.example.doan.R.*;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.doan.R;


import androidx.annotation.NonNull;
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
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Spinner locationSp;
    private Spinner timeSp;
    private Spinner printSp;
    private RecyclerView rcyFood, rcyCategory;
    private FoodAdapter foodAdapter;
    private CategoryAdapter categoryAdapter;
    private ImageView cartBtn, logoutBtn;

    private static final String PREFS_NAME = "CartPrefs";
    private static final String KEY_CART_ITEMS = "cart_items";
    public static List<CartItem> cartItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ view
        initViews();

        // Cấu hình RecyclerView
        setupRecyclerView();

        // Cấu hình Spinner
        setupSpinners();

        // Xử lý sự kiện nút bấm
        setupButtonListeners();

        // Cấu hình BottomNavigationView
        setupBottomNavigation();
    }

    private void initViews() {
        locationSp = findViewById(R.id.locationSp);
        timeSp = findViewById(R.id.timeSp);
        printSp = findViewById(R.id.printSP);
        rcyFood = findViewById(R.id.bestFoodView);
        rcyCategory = findViewById(R.id.recyclerView2);
        cartBtn = findViewById(R.id.cartBtn);
        logoutBtn = findViewById(R.id.logoutBtn);
    }

    private void setupRecyclerView() {
        foodAdapter = new FoodAdapter();
        categoryAdapter = new CategoryAdapter(this);

        List<Foods> foodList = getListFood();
        List<Category> categoryList = getListCategory();

        rcyFood.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        rcyCategory.setLayoutManager(new GridLayoutManager(this, 4));

        foodAdapter.setData(foodList);
        categoryAdapter.setData(categoryList);

        rcyFood.setAdapter(foodAdapter);
        rcyCategory.setAdapter(categoryAdapter);
    }

    private void setupSpinners() {
        List<String> locations = Arrays.asList("Quảng Nam", "Đà Nẵng");
        List<String> times = Arrays.asList("0-10 phút", "10-30 phút", "Hơn 30 phút");
        List<String> prices = Arrays.asList("1$-10$", "10$-100$", "Hơn 100$");

        ArrayAdapter<String> locationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, locations);
        ArrayAdapter<String> timeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, times);
        ArrayAdapter<String> priceAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, prices);

        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        priceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        locationSp.setAdapter(locationAdapter);
        timeSp.setAdapter(timeAdapter);
        printSp.setAdapter(priceAdapter);

        locationSp.setOnItemSelectedListener(createSpinnerListener());
        timeSp.setOnItemSelectedListener(createSpinnerListener());
        printSp.setOnItemSelectedListener(createSpinnerListener());
    }

    private AdapterView.OnItemSelectedListener createSpinnerListener() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selected = parentView.getSelectedItem().toString();
                Toast.makeText(MainActivity.this, selected, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Không làm gì
            }
        };
    }

    private void setupButtonListeners() {
        cartBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(intent);
        });

        logoutBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }


    private void setupBottomNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                bottomNavigationView.setOnItemSelectedListener(menuItem -> {
                    int itemId = menuItem.getItemId(); // Đổi 'item' thành 'menuItem'

                    if (itemId == R.id.nav_home) {
                        return true;
                    } else if (itemId == R.id.nav_cart) {
                        Intent cartIntent = new Intent(MainActivity.this, CartActivity.class);
                        startActivity(cartIntent);
                        return true;
                    } else if (itemId == R.id.nav_activity) {
                        Intent ordersIntent = new Intent(MainActivity.this, Xem_don_hangActivity.class);
                        startActivity(ordersIntent);
                        return true;
                    } else if (itemId == R.id.nav_account) {
                        Intent accountIntent = new Intent(MainActivity.this, AccountActivity.class);
                        startActivity(accountIntent);
                        return true;
                    }
                    return false; // Đảm bảo luôn có giá trị trả về
                });


                return false;
            }
        });
    }

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
        loadCartItems();
    }

    private List<Foods> getListFood() {
        return Arrays.asList(
                new Foods(10, R.drawable.bach, 4.9, 10, "Bacon and Cheese Heaven"),
                new Foods(10.99, R.drawable.margherita, 4.9, 11, "Margherita"),
                new Foods(10, R.drawable.koreanbbqshortribs, 4.9, 10, "Korean BBQ Short Ribs"),
                new Foods(10, R.drawable.chicagostylehotdog, 4.9, 10, "Chicago Style Hot Dog")
        );
    }

    private List<Category> getListCategory() {
        return Arrays.asList(
                new Category(R.drawable.btn_1, "Pizza"),
                new Category(R.drawable.btn_2, "Burger"),
                new Category(R.drawable.btn_3, "Chicken"),
                new Category(R.drawable.btn_4, "Sushi"),
                new Category(R.drawable.btn_5, "Meat"),
                new Category(R.drawable.btn_6, "HotDog"),
                new Category(R.drawable.btn_7, "Drink"),
                new Category(R.drawable.btn_8, "More")
        );
    }
}
