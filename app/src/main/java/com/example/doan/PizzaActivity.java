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

public class PizzaActivity extends AppCompatActivity {
    private RecyclerView foodListView;
    private ImageView backBtn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pizza);

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
        list.add(new Foods(10,R.drawable.bbqchickendelight,4.9,10,"BBQ Chicken Delight"));
        list.add(new Foods(10,R.drawable.margherita,4.9,10,"Margherita"));
        list.add(new Foods(12.99,R.drawable.pepperonilovers,4.7,18,"Pepperoni Lovers"));
        list.add(new Foods(11.99,R.drawable.veggiesupreme,4.5,20,"Veggie Supreme"));
        list.add(new Foods(11.99,R.drawable.hawaiianparadise,4.3,17,"Hawaiian Paradise"));
        list.add(new Foods(12.99,R.drawable.meatfeast,4.9,10,"Meat Feast"));
        list.add(new Foods(10.99,R.drawable.mediterraneanjoy,4.7,15,"Mediterranean Joy"));
        list.add(new Foods(9.99,R.drawable.margheritaflatbread,4.9,10,"Margherita Flatbread"));


        return list;
    }
}