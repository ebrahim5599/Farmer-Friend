package com.graduation.farmerfriend.e_commerce.ui.Search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.graduation.farmerfriend.databinding.ItemSearchBinding;

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
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemSearchBinding binding = ItemSearchBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.binding.itemsearchTextviewName.setText(search[position].getName());
        holder.binding.itemsearchTextviewPrice.setText(search[position].getPrice());
        holder.binding.itemsearchImageviewItem.setImageResource(search[position].getImage());
        holder.binding.itemsearchTextviewDescription.setText(search[position].getDescription());
    }

    @Override
    public int getItemCount() {
        return search.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ItemSearchBinding binding;

        public ViewHolder(ItemSearchBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
