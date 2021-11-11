package com.graduation.farmerfriend.e_commerce;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.graduation.farmerfriend.R;

public class ViewRecycleSearchAdapter extends RecyclerView.Adapter<ViewRecycleSearchAdapter.ViewHolder>{

    Search_Item[] search ;
    Context context ;

    public ViewRecycleSearchAdapter(Context context,Search_Item[] search) {
        this.search = search;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewRecycleSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.text_name.setText(search[position].getName());
        holder.text_price.setText(search[position].getPrice());
        holder.image.setImageResource(search[position].getImage());
    }

    @Override
    public int getItemCount() {
        return search.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView text_name ;
        TextView text_price;
        ImageView image;

        public ViewHolder(View itemView) {

            super(itemView);
            text_name = itemView.findViewById(R.id.search_name);
            text_price = itemView.findViewById(R.id.search_price);
            image = itemView.findViewById(R.id.search_image);
        }}
}
