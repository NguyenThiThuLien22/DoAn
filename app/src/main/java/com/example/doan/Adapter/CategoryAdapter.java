package com.example.doan.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.ChickenActivity;
import com.example.doan.Domain.Category;
import com.example.doan.BurgerActivity;
import com.example.doan.DrinkActivity;
import com.example.doan.HotDogActivity;
import com.example.doan.MeatActivity;
import com.example.doan.MoreActivity;
import com.example.doan.PizzaActivity;
import com.example.doan.R;
import com.example.doan.ShuShiActivity;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<Category> mCategory;
    private Context mContext;  // Thêm Context để khởi tạo Intent

    // Constructor để nhận Context
    public CategoryAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<Category> list) {
        this.mCategory = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category, parent, false);
        return new CategoryViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Category category = mCategory.get(position);
        if (category == null) {
            return;
        }

        holder.catNameTxt.setText(category.getTitle());
        holder.imgCat.setImageResource(category.getResourceId());

        // Cài đặt background tùy thuộc vào vị trí
        switch (position) {
            case 0:
                holder.imgCat.setBackgroundResource(R.drawable.cat1_background);
                break;
            case 1:
                holder.imgCat.setBackgroundResource(R.drawable.cat2_background);
                break;
            case 2:
                holder.imgCat.setBackgroundResource(R.drawable.cat3_background);
                break;
            case 3:
                holder.imgCat.setBackgroundResource(R.drawable.cat4_background);
                break;
            case 4:
                holder.imgCat.setBackgroundResource(R.drawable.cat5_background);
                break;
            case 5:
                holder.imgCat.setBackgroundResource(R.drawable.cat6_background);
                break;
            case 6:
                holder.imgCat.setBackgroundResource(R.drawable.cat7_background);
                break;
            case 7:
                holder.imgCat.setBackgroundResource(R.drawable.cat8_background);
                break;
        }

        // Bắt sự kiện click vào mỗi item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;

                // Dựa vào vị trí để khởi tạo Intent tới Activity tương ứng
                if (position == 0) {
                    intent = new Intent(mContext, PizzaActivity.class);  // Sửa thành tên Activity bạn muốn
                }
                if (position == 1) {
                    intent = new Intent(mContext, BurgerActivity.class);  // Sửa thành tên Activity bạn muốn
                }
                if (position == 2) {
                    intent = new Intent(mContext, ChickenActivity.class);  // Sửa thành tên Activity bạn muốn
                }
                if (position == 3) {
                    intent = new Intent(mContext, ShuShiActivity.class);  // Sửa thành tên Activity bạn muốn
                }
                if (position == 4) {
                    intent = new Intent(mContext, MeatActivity.class);  // Sửa thành tên Activity bạn muốn
                }
                if (position == 5) {
                    intent = new Intent(mContext, HotDogActivity.class);  // Sửa thành tên Activity bạn muốn
                }
                if (position == 6) {
                    intent = new Intent(mContext, DrinkActivity.class);  // Sửa thành tên Activity bạn muốn
                }
                if (position == 7) {
                    intent = new Intent(mContext, MoreActivity.class);  // Sửa thành tên Activity bạn muốn
                }

                if (intent != null) {
                    mContext.startActivity(intent);  // Bắt đầu Activity mới
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mCategory != null) {
            return mCategory.size();
        }
        return 0;
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        private TextView catNameTxt;
        private ImageView imgCat;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            catNameTxt = itemView.findViewById(R.id.catNameTxt);
            imgCat = itemView.findViewById(R.id.imgCat);
        }
    }
}
