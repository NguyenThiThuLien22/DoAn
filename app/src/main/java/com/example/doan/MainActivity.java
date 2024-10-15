package com.example.doan;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.Adapter.CategoryAdapter;
import com.example.doan.Adapter.FoodAdapter;
import com.example.doan.Domain.Category;
import com.example.doan.Domain.Foods;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Spinner locationSp;
    private Spinner timeSp;
    private Spinner printSP;
    private RecyclerView rcyFood,rcyCategory;
    private FoodAdapter foodAdapter;
    private CategoryAdapter categoryAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        locationSp = findViewById(R.id.locationSp);
        timeSp = findViewById(R.id.timeSp);
        printSP = findViewById(R.id.printSP);

        rcyFood = findViewById(R.id.bestFoodView);
        foodAdapter = new FoodAdapter();

        rcyCategory = findViewById(R.id.recyclerView2);
        categoryAdapter = new CategoryAdapter();

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
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Thay đổi để sử dụng spinner_dropdown_item chuẩn

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
                // Hiển thị item đã chọn bằng Toast
                Toast.makeText(MainActivity.this, timeSp.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Không làm gì khi không có item nào được chọn
            }
        });

        printSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Hiển thị item đã chọn bằng Toast
                Toast.makeText(MainActivity.this, printSP.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Không làm gì khi không có item nào được chọn
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private List<Foods> getListFood(){
        List<Foods> list = new ArrayList<>();
        list.add(new Foods(10,R.drawable.bach,4.9,10,"Bacon and Cheese Heaven"));
        list.add(new Foods(10,R.drawable.margherita,4.9,10,"Margherita"));
        list.add(new Foods(10,R.drawable.bach,4.9,10,"Bacon and Cheese Heaven"));
        list.add(new Foods(10,R.drawable.bach,4.9,10,"Bacon and Cheese Heaven"));


        return list;
    }

    private List<Category> getListCategory(){
        List<Category> list = new ArrayList<>();
        list.add(new Category(R.drawable.btn_1,"Burger"));
        list.add(new Category(R.drawable.btn_2,"Burger"));
        list.add(new Category(R.drawable.btn_3,"Burger"));
        list.add(new Category(R.drawable.btn_4,"Burger"));
        list.add(new Category(R.drawable.btn_5,"Burger"));
        list.add(new Category(R.drawable.btn_6,"Burger"));
        list.add(new Category(R.drawable.btn_7,"Burger"));
        list.add(new Category(R.drawable.btn_8,"Burger"));




        return list;
    }
}