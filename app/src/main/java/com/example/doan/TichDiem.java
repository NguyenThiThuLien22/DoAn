package com.example.doan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TichDiem extends AppCompatActivity {

    private ImageView imageLogout;
    private TextView pointsTextView;  // TextView to display points

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tich_diem);

        // Reference to imageLogout
        imageLogout = findViewById(R.id.image_logout);

        // Reference to pointsTextView (Assuming there's a TextView in activity_tich_diem layout to display points)
        pointsTextView = findViewById(R.id.points_text_view);

        // Get the purchase amount passed from DanhGia activity
        double purchaseAmount = getIntent().getDoubleExtra("purchase_amount", 0.0);

        // Calculate points based on the purchase amount
        int points = calculatePoints(purchaseAmount);

        // Display the calculated points
        pointsTextView.setText("Your Points: " + points);
        Toast.makeText(this, "You have earned " + points + " points", Toast.LENGTH_SHORT).show();

        // Logout button event
        imageLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHomePage();
            }
        });
    }

    // Calculate points based on purchase amount
    private int calculatePoints(double amount) {
        if (amount > 0 && amount <= 10) {
            return 10;
        } else if (amount > 10 && amount <= 20) {
            return 20;
        } else if (amount > 20 && amount <= 30) {
            return 30;
        } else {
            return 50;  // Example for purchases above 30$
        }
    }

    // Navigate back to the home page (MainActivity)
    private void goToHomePage() {
        Intent intent = new Intent(TichDiem.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}