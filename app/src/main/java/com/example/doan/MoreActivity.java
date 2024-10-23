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

public class MoreActivity extends AppCompatActivity {
    private RecyclerView foodListView;
    private ImageView backBtn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_more);

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
        list.add(new Foods(10,R.drawable.beefstirfrywithbroccoli,4.9,10,"Beef Stir-Fry with Broccoli"));
        list.add(new Foods(10,R.drawable.fourcheesedelight,4.9,10,"Four Cheese Delight"));
        list.add(new Foods(10,R.drawable.honeymustardglazedtenders,4.9,10,"Honey Mustard Glazed Tenders"));
        list.add(new Foods(10,R.drawable.pastacarbonara,4.9,10,"Pasta Carbonara"));
        list.add(new Foods(10,R.drawable.thairedcurry,4.9,10,"Thai Red Curry"));
        list.add(new Foods(10,R.drawable.vegetarianpadthai,4.9,10,"Vegetarian Pad Thai"));




        return list;
    }
}