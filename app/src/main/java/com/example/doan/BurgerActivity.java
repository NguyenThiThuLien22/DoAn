package com.example.doan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.Adapter.FoodAdapter;
import com.example.doan.Domain.Foods;

import java.util.ArrayList;
import java.util.List;

public class BurgerActivity extends AppCompatActivity {
    private RecyclerView foodListView;
    private ImageView backBtn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_burger);

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
        list.add(new Foods(10,R.drawable.bach,4.9,10,"Bacon and Cheese Heaven"));
        list.add(new Foods(10,R.drawable.bbqranchdelight,4.9,10,"BBQ Ranch Delight"));
        list.add(new Foods(10,R.drawable.chickenavocadobliss,4.9,10,"Chicken Avocado Bliss"));
        list.add(new Foods(10,R.drawable.classicbeefburger,4.9,10,"Classic Beef Burger"));
        list.add(new Foods(10,R.drawable.mushroomswissdelight,4.9,10,"Mushroom Swiss Delight"));
        list.add(new Foods(10,R.drawable.spicyjalapenoburger,4.9,10,"Spicy Jalapeño Burger"));
        list.add(new Foods(10,R.drawable.veggieextravaganza,4.9,10,"Veggie Extravaganza"));



        return list;
    }
}