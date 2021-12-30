package com.graduation.farmerfriend.store;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.graduation.farmerfriend.R;

import java.util.ArrayList;


public class StoreItemsAdapter extends RecyclerView.Adapter<StoreItemsAdapter.ViewHolder>{

    private ArrayList<StoreItems> storeItemsArrayList;
    private Context context;

    public StoreItemsAdapter(Context context, ArrayList<StoreItems> storeItemsArrayList) {
        this.storeItemsArrayList = storeItemsArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.store_item_container, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.item_image.setImageResource(storeItemsArrayList.get(position).getImageResource());
        holder.item_name.setText(storeItemsArrayList.get(position).getItemName());
        holder.item_details.setText(storeItemsArrayList.get(position).getItemDetails());
        holder.item_numbers.setText(String.valueOf(storeItemsArrayList.get(position).getNumberOfItems()));

        holder.buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numOfItems = Integer.parseInt((String) holder.item_numbers.getText());
                holder.item_numbers.setText(String.valueOf(numOfItems + 1));
            }
        });

        holder.buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numOfItems = Integer.parseInt((String) holder.item_numbers.getText());
                if (numOfItems > 0) {
                    holder.item_numbers.setText(String.valueOf(numOfItems - 1));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return storeItemsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView item_image;
        TextView item_name, item_details, item_numbers;
        ImageButton buttonPlus, buttonMinus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item_image = itemView.findViewById(R.id.store_item_image);
            item_name  = itemView.findViewById(R.id.activity_store_item_name);
            item_details = itemView.findViewById(R.id.activity_store_item_details);
            item_numbers = itemView.findViewById(R.id.activity_store_item_numbers);

            buttonPlus = itemView.findViewById(R.id.activity_store_imageButton_plus);
            buttonMinus= itemView.findViewById(R.id.activity_store_imageButton_minus);
        }
    }
}
