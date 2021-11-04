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

public class ViewRecycleCategoriesAdapter extends RecyclerView.Adapter<ViewRecycleCategoriesAdapter.ViewHolder> {

    Categorie[] categorie ;
    Context context ;

    public ViewRecycleCategoriesAdapter(Context con, Categorie[] categorie){
        this.context = con ;
        this.categorie = categorie ;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewrecycleviewcategories, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.text_categorie.setText(categorie[position].getCategorie());

    }

    @Override
    public int getItemCount() {
        return categorie.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView text_categorie ;

    public ViewHolder(View itemView) {

        super(itemView);
        text_categorie = itemView.findViewById(R.id.categorie);
    }}
}
