package com.example.doan.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.Domain.Foods;
import com.example.doan.PizzaDetailActivity;
import com.example.doan.R;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private List<Foods> mFoods;

//    public FoodAdapter(List<Foods> mFoods) {
//        this.mFoods = mFoods;
//    }

    public void setData(List<Foods> list){
        this.mFoods = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_best_deal,parent,false);
        return new FoodViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Foods foods = mFoods.get(position);
        if (foods == null) {
            return;
        }

        // Hiển thị tiêu đề
        holder.titleTxt.setText(foods.getTitle());

        // Hiển thị giá (double -> String)
        holder.priceTxt.setText(String.valueOf(foods.getPrice()) + "$" );

        // Hiển thị thời gian (int -> String)
        holder.timeTxt.setText(String.valueOf(foods.getTimeValue()));

        // Hiển thị sao (double -> String)
        holder.starTxt.setText(String.valueOf(foods.getStar()));

        // Hiển thị ảnh từ resource ID
        holder.pic.setImageResource(foods.getResourceId());

        // Thiết lập sự kiện nhấn vào item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang Activity khác
                Intent intent = new Intent(v.getContext(), PizzaDetailActivity.class);

                // Truyền dữ liệu qua Intent
                intent.putExtra("title", foods.getTitle());
                intent.putExtra("price", foods.getPrice()); // Price là kiểu double
                intent.putExtra("time", foods.getTimeValue()); // TimeValue là kiểu int
                intent.putExtra("image", foods.getResourceId()); // ResourceId là kiểu int

                // Gửi Intent để mở Activity
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mFoods != null ){
            return mFoods.size();
        }
        return 0;
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder{

        private TextView titleTxt,priceTxt,starTxt,timeTxt;
        private ImageView pic;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            timeTxt = itemView.findViewById(R.id.timeTxt);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            priceTxt = itemView.findViewById(R.id.priceTxt);
            starTxt = itemView.findViewById(R.id.starTxt);
            pic = itemView.findViewById(R.id.pic);
        }
    }
}
