package com.example.doan;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DanhGia extends AppCompatActivity {

    private RatingBar tasteRatingBar, portionRatingBar, packagingRatingBar, overallRatingBar;
    private EditText reviewText;
    private Button submitButton;
    private TextView contactSupport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_gia); // Đảm bảo rằng layout tên là activity_danh_gia

        // Khởi tạo các view
        overallRatingBar = findViewById(R.id.overallRatingBar);
        tasteRatingBar = findViewById(R.id.tasteRatingBar);
        portionRatingBar = findViewById(R.id.portionRatingBar);
        packagingRatingBar = findViewById(R.id.packagingRatingBar);
        reviewText = findViewById(R.id.reviewText);
        submitButton = findViewById(R.id.submitButton);
        contactSupport = findViewById(R.id.contactSupport);

        // Thiết lập sự kiện cho nút "Contact Support"
        contactSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý sự kiện khi người dùng nhấn vào "Contact Support"
                Toast.makeText(DanhGia.this, "Support contact clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // Thiết lập sự kiện cho nút "Submit"
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float tasteRating = tasteRatingBar.getRating();
                float portionRating = portionRatingBar.getRating();
                float packagingRating = packagingRatingBar.getRating();
                float overallRating = overallRatingBar.getRating();
                String reviewContent = reviewText.getText().toString().trim();

                // Kiểm tra nếu chưa nhập nội dung đánh giá
                if (reviewContent.isEmpty()) {
                    Toast.makeText(DanhGia.this, "Vui lòng nhập nội dung đánh giá!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Xử lý hoặc gửi dữ liệu
                Toast.makeText(DanhGia.this, "Cảm ơn bạn đã đánh giá!", Toast.LENGTH_SHORT).show();

                // Hiển thị dữ liệu (tạm thời - có thể thay bằng API để gửi đến server)
                String result = "Taste: " + tasteRating + "\n" +
                        "Portion: " + portionRating + "\n" +
                        "Packaging: " + packagingRating + "\n" +
                        "Overall: " + overallRating + "\n" +
                        "Review: " + reviewContent;
                Toast.makeText(DanhGia.this, result, Toast.LENGTH_LONG).show();
            }
        });
    }
}
