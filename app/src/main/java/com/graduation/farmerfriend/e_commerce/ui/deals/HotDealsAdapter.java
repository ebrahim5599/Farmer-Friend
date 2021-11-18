package com.graduation.farmerfriend.e_commerce.ui.deals;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.graduation.farmerfriend.R;

public class HotDealsAdapter extends RecyclerView.Adapter<HotDealsAdapter.HotDealsViewHolder> {


    @NonNull
    @Override
    public HotDealsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HotDealsViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_container, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HotDealsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 25;
    }

    public class HotDealsViewHolder extends RecyclerView.ViewHolder {
        public HotDealsViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
