package com.graduation.farmerfriend.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.graduation.farmerfriend.R;

public class EcommerceAdapter extends RecyclerView.Adapter<EcommerceAdapter.EcommerceViewHolder> {

    @NonNull
    @Override
    public EcommerceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EcommerceViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.e_commerce_container, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EcommerceViewHolder holder, int position) {
        holder.textViewPrice.setText(String.format("%d$", position));
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    class EcommerceViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewPrice;
        TextView textViewName;

        public EcommerceViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.product_image);
            textViewName = itemView.findViewById(R.id.product_name);
            textViewPrice = itemView.findViewById(R.id.product_price);

        }

    }
}
