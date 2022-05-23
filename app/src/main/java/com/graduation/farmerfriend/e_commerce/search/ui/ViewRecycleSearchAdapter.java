package com.graduation.farmerfriend.e_commerce.search.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.graduation.farmerfriend.databinding.ItemSearchBinding;
import com.graduation.farmerfriend.e_commerce.search.pojo.SearchResultPojo;

import java.util.ArrayList;
import java.util.List;

public class ViewRecycleSearchAdapter extends RecyclerView.Adapter<ViewRecycleSearchAdapter.ViewHolder>{

    private List<SearchResultPojo> search = new ArrayList<>();
    private Context context ;

    public ViewRecycleSearchAdapter(Context context) {
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
//        holder.binding.itemsearchTextviewName.setText(search.get(position).getProductName());
//        holder.binding.itemsearchTextviewPrice.setText(String.valueOf(search.get(position).getPrice()));
////        Glide.with(context).load(search.get(position).getProductImage()).into(holder.binding.itemsearchImageviewItem);
//        holder.binding.itemsearchTextviewDescription.setText(search.get(position).getDescription()+"");
    }

    @Override
    public int getItemCount() {
        return search.size();
    }

    public void setList(List<SearchResultPojo> searchResultList){
        search = searchResultList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ItemSearchBinding binding;

        public ViewHolder(ItemSearchBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
