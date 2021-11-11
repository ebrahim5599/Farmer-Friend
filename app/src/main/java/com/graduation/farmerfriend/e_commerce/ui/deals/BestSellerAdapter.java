package com.graduation.farmerfriend.e_commerce.ui.deals;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.graduation.farmerfriend.R;

public class BestSellerAdapter extends RecyclerView.Adapter<BestSellerAdapter.BestSellerViewHolder> {


    @NonNull
    @Override
    public BestSellerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BestSellerViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_container, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BestSellerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 25;
    }

    public class BestSellerViewHolder  extends RecyclerView.ViewHolder{
        public BestSellerViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
