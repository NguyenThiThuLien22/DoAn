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

public class MeatActivity extends AppCompatActivity {
    private RecyclerView foodListView;
    private ImageView backBtn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_meat);

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
        list.add(new Foods(10,R.drawable.spicymoroccanlambchops,4.9,10,"Spicy Moroccan Lamb Chops"));
        list.add(new Foods(10,R.drawable.spicybuffalowings,4.9,10,"Spicy Buffalo Wings"));
        list.add(new Foods(10,R.drawable.smokedbbqbrisket,4.9,10,"Smoked BBQ Brisket"));
        list.add(new Foods(10,R.drawable.pansearedgarlicbuttersirloin,4.9,10,"Pan-Seared Garlic Butter Sirloin"));
        list.add(new Foods(10,R.drawable.koreanbbqshortribs,4.9,10,"Korean BBQ Short Ribs"));
        list.add(new Foods(10,R.drawable.grilledribeyesteak,4.9,10,"Grilled Ribeye Steak"));
        list.add(new Foods(10,R.drawable.baconwrappedfiletmignon,4.9,10,"Bacon-Wrapped Filet Mignon"));



        return list;
    }
}