package com.example.doan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PizzaDetailActivity extends AppCompatActivity {
    private View backBtn;
    private TextView titleTextView,priceTxt,timeTxt;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pizza_detail);

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),MainActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        double price = intent.getDoubleExtra("price", 0);
        int time = intent.getIntExtra("time", 0);
        int imageResourceId = intent.getIntExtra("image", 0);


        titleTextView = findViewById(R.id.titleTxt); // Giả sử bạn có một TextView trong layout của DetailActivity
        titleTextView.setText(title);

        priceTxt = findViewById(R.id.priceTxt);
        priceTxt.setText(String.valueOf(price) + "$");

        timeTxt = findViewById(R.id.timeTxt);
        timeTxt.setText(String.valueOf(time));

        ImageView pic = findViewById(R.id.pic);
        pic.setImageResource(imageResourceId);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}