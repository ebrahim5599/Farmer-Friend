package com.graduation.farmerfriend.e_commerce;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.graduation.farmerfriend.R;

public class ViewRecycleCategoriesAdapter extends RecyclerView.Adapter<ViewRecycleCategoriesAdapter.ViewHolder> {

    Category[] category;
    Context context ;

    public ViewRecycleCategoriesAdapter(Context con, Category[] category){
        this.context = con ;
        this.category = category;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewrecycleviewcategories, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.text_category.setText(category[position].getCategory());

    }

    @Override
    public int getItemCount() {
        return category.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView text_category;

    public ViewHolder(View itemView) {

        super(itemView);
        text_category = itemView.findViewById(R.id.category);
    }}
}
