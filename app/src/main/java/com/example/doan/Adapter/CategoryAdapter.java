package com.example.doan.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.Domain.Category;
import com.example.doan.Domain.Foods;
import com.example.doan.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.FoodViewHolder> {

    private List<Category> mCategory;

//    public FoodAdapter(List<Foods> mFoods) {
//        this.mFoods = mFoods;
//    }

    public void setData(List<Category> list){
        this.mCategory = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category,parent,false);
        return new FoodViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Category category = mCategory.get(position);
        if (category == null) {
            return;
        }

        // Hiển thị tiêu đề
        holder.catNameTxt.setText(category.getTitle());
        // Hiển thị ảnh từ resource ID
        holder.imgCat.setImageResource(category.getResourceId());

        switch (position){
            case 0:
            {
                holder.imgCat.setBackgroundResource(R.drawable.cat1_background);
                break;
            }
            case 1:
            {
                holder.imgCat.setBackgroundResource(R.drawable.cat2_background);
                break;
            }
            case 2:
            {
                holder.imgCat.setBackgroundResource(R.drawable.cat3_background);
                break;
            }
            case 3:
            {
                holder.imgCat.setBackgroundResource(R.drawable.cat4_background);
                break;
            }
            case 4:
            {
                holder.imgCat.setBackgroundResource(R.drawable.cat5_background);
                break;
            }
            case 5:
            {
                holder.imgCat.setBackgroundResource(R.drawable.cat6_background);
                break;
            }
            case 6:
            {
                holder.imgCat.setBackgroundResource(R.drawable.cat7_background);
                break;
            }case 7:
            {
                holder.imgCat.setBackgroundResource(R.drawable.cat8_background);
                break;
            }


        }
    }

    @Override
    public int getItemCount() {
        if(mCategory != null ){
            return mCategory.size();
        }
        return 0;
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder{

        private TextView catNameTxt;
        private ImageView imgCat;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            catNameTxt = itemView.findViewById(R.id.catNameTxt);
            imgCat = itemView.findViewById(R.id.imgCat);
        }
    }
}
