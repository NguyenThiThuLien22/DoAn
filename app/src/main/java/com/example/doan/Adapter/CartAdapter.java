package com.example.doan.Adapter;



import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.Domain.CartItem;
import com.example.doan.R;
import com.google.gson.Gson;
import java.util.function.Consumer;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<CartItem> cartItems;
    private CartUpdateListener cartUpdateListener; // Thêm listener

    public CartAdapter(List<CartItem> cartItems, CartUpdateListener listener) {
        this.cartItems = cartItems;
        this.cartUpdateListener = listener; // Khởi tạo listener
    }

    public interface CartUpdateListener {
        void onCartUpdated();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        // Lấy sản phẩm hiện tại từ danh sách cartItems
        CartItem currentItem = cartItems.get(position);

        // Hiển thị thông tin sản phẩm trong các TextView và ImageView
        holder.titleTextView.setText(currentItem.getTitle());
        holder.priceTextView.setText(String.format("%.2f$", currentItem.getPrice()));
        holder.quantityTextView.setText(String.valueOf(currentItem.getQuantity()));
        holder.imageView.setImageResource(currentItem.getImageResourceId());

        // Hiển thị tổng giá trị của sản phẩm
        holder.feeEachItem.setText(String.format("%.2f$", currentItem.getTotalPrice()));


        // Tăng số lượng
        holder.plusCartBtn.setOnClickListener(view -> {
            currentItem.setQuantity(currentItem.getQuantity() + 1);
            holder.quantityTextView.setText(String.valueOf(currentItem.getQuantity()));
            holder.feeEachItem.setText(String.format("%.2f$", currentItem.getTotalPrice())); // Cập nhật giá
            notifyItemChanged(position); // Cập nhật mục hiện tại
            cartUpdateListener.onCartUpdated();
        });

        // Giảm số lượng
        holder.minusCartBtn.setOnClickListener(view -> {
            if (currentItem.getQuantity() > 1) {
                currentItem.setQuantity(currentItem.getQuantity() - 1);
                holder.quantityTextView.setText(String.valueOf(currentItem.getQuantity()));
                holder.feeEachItem.setText(String.format("%.2f$", currentItem.getTotalPrice())); // Cập nhật giá
                notifyItemChanged(position); // Cập nhật mục hiện tại
            } else {
                // Xóa sản phẩm nếu số lượng giảm về 0
                cartItems.remove(position);
                notifyItemRemoved(position); // Xóa mục khỏi RecyclerView
                notifyItemRangeChanged(position, cartItems.size()); // Cập nhật các mục còn lại
            }
            cartUpdateListener.onCartUpdated();
        });

    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView priceTextView;
        TextView quantityTextView,plusCartBtn,minusCartBtn;
        TextView feeEachItem;
        ImageView imageView;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTxt);
            priceTextView = itemView.findViewById(R.id.totalEachItem);
            quantityTextView = itemView.findViewById(R.id.numberItemTxt);
            imageView = itemView.findViewById(R.id.pic);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            plusCartBtn = itemView.findViewById(R.id.plusCartBtn);
            minusCartBtn = itemView.findViewById(R.id.minusCartBtn);
        }
    }


}