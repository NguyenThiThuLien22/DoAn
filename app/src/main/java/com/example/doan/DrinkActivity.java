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

public class DrinkActivity extends AppCompatActivity {
    private RecyclerView foodListView;
    private ImageView backBtn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_drink);

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
        list.add(new Foods(10,R.drawable.coconutwater,4.9,10,"Coconut Water"));
        list.add(new Foods(10,R.drawable.espressomartini,4.9,10,"Espresso Martini"));
        list.add(new Foods(10,R.drawable.freshorangejuice,4.9,10,"Fresh Orange Juice"));
        list.add(new Foods(10,R.drawable.greentealatte,4.9,10,"Green Tea Latte"));
        list.add(new Foods(10,R.drawable.icedcaramelmacchiato,4.9,10,"Iced Caramel Macchiato"));
        list.add(new Foods(10,R.drawable.mangotangoslush,4.9,10,"Mango Tango Slush"));
        list.add(new Foods(10,R.drawable.mintlemonade,4.9,10,"Mint Lemonade"));



        return list;
    }
}