package com.example.doan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.Adapter.FoodAdapter;
import com.example.doan.Domain.Foods;

import java.util.ArrayList;
import java.util.List;

public class ChickenActivity extends AppCompatActivity {
    private RecyclerView foodListView;
    private ImageView backBtn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chicken);

        foodListView = findViewById(R.id.foodListView);
        FoodAdapter foodAdapter = new FoodAdapter();

        List<Foods> foodList = getListFood();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        foodListView.setLayoutManager(gridLayoutManager);

        foodAdapter.setData(foodList);
        foodListView.setAdapter(foodAdapter);


        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),MainActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }


    private List<Foods> getListFood(){
        List<Foods> list = new ArrayList<>();
        list.add(new Foods(10,R.drawable.garlicparmesanchicken,4.9,10,"Garlic Parmesan Chicken"));
        list.add(new Foods(10,R.drawable.koreanfriedchicken,4.9,10,"Korean Fried Chicken"));
        list.add(new Foods(12.99,R.drawable.lemonpepperchicken,4.7,18,"Lemon Pepper Chicken"));
        list.add(new Foods(11.99,R.drawable.originalcrispychicken,4.5,20,"Original Crispy Chicken"));
        list.add(new Foods(11.99,R.drawable.teriyakichickenwings,4.3,17,"Teriyaki Chicken Wings"));
        list.add(new Foods(12.99,R.drawable.teriyakiglazedchickenthighs,4.9,10,"Teriyaki Glazed Chicken Thighs"));



        return list;
    }
}