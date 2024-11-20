package com.example.doan;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DanhGia extends AppCompatActivity {

    private int rating = 0; // Đánh giá sao
    private TextView textBinhLuan, textNhapNoiDung;
    private ImageView[] stars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_gia); // Chú ý tên layout của bạn

        // Lấy tham chiếu đến các thành phần giao diện
        textBinhLuan = findViewById(R.id.text_binh_luan);
        textNhapNoiDung = findViewById(R.id.text_nhap_noi_dung);

        // Danh sách các ImageView sao
        stars = new ImageView[]{
                findViewById(R.id.image_star),
                findViewById(R.id.image_star1),
                findViewById(R.id.image_star2),
                findViewById(R.id.image_star3),
                findViewById(R.id.image_star4)
        };

        // Xử lý sự kiện khi người dùng nhấn vào các sao
        for (int i = 0; i < stars.length; i++) {
            final int index = i;
            stars[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Cập nhật số sao khi người dùng nhấn vào một sao
                    rating = index + 1;
                    updateStarRating();
                }
            });
        }

        // Xử lý nút "Đánh giá"
        TextView btnDanhGia = findViewById(R.id.text_danh_gia1);
        btnDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String binhLuan = textNhapNoiDung.getText().toString();
                if (rating == 0) {
                    Toast.makeText(DanhGia.this, "Vui lòng chọn sao để đánh giá!", Toast.LENGTH_SHORT).show();
                } else if (binhLuan.isEmpty()) {
                    Toast.makeText(DanhGia.this, "Vui lòng nhập bình luận!", Toast.LENGTH_SHORT).show();
                } else {
                    // Gửi đánh giá và bình luận đi (hoặc lưu vào cơ sở dữ liệu, gửi lên server)
                    Toast.makeText(DanhGia.this, "Đánh giá: " + rating + " sao\nBình luận: " + binhLuan, Toast.LENGTH_LONG).show();
                }
            }
        });

        // Xử lý nút "Hủy"
        TextView btnHuy = findViewById(R.id.text_huy);
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Quay lại hoặc thực hiện hành động hủy
                finish(); // Đóng Activity và quay lại màn hình trước đó
            }
        });
    }

    // Cập nhật hình ảnh sao khi người dùng chọn sao
    private void updateStarRating() {
        for (int i = 0; i < stars.length; i++) {
            if (i < rating) {
                stars[i].setImageResource(R.drawable.star); // Sao đã chọn
            } else {
                stars[i].setImageResource(R.drawable.star); // Sao chưa chọn
            }
        }
    }
}
