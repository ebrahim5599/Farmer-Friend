package com.graduation.farmerfriend.store.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.store.data.StoreDatabase;
import com.graduation.farmerfriend.store.pojo.StoreItems;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;


public class StoreItemsAdapter extends RecyclerView.Adapter<StoreItemsAdapter.ViewHolder>{

    ArrayList<StoreItems> storeItemsArrayList;
    private Context context;
    StoreDatabase myDatabase;

    public StoreItemsAdapter(@NonNull Context context, @NonNull ArrayList<StoreItems> storeItemsArrayList) {
        this.storeItemsArrayList = storeItemsArrayList;
        this.context = context;
        myDatabase = new StoreDatabase(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.store_item_container, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.item_name.setText(storeItemsArrayList.get(position).getItemName());
        holder.item_details.setText(storeItemsArrayList.get(position).getItemDetails());
        holder.item_numbers.setText(String.valueOf(storeItemsArrayList.get(position).getNumberOfItems()));

        if(storeItemsArrayList.get(position).getImageResource() != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(storeItemsArrayList.get(position).getImageResource(),
                    0, storeItemsArrayList.get(position).getImageResource().length);
            holder.item_image.setImageBitmap(bitmap);
        }

        holder.buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numOfItems = Integer.parseInt((String) holder.item_numbers.getText());
                myDatabase.updateItemAmount(storeItemsArrayList.get(holder.getAbsoluteAdapterPosition()), numOfItems+1);
                holder.item_numbers.setText(String.valueOf(numOfItems + 1));
            }
        });

        holder.buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numOfItems = Integer.parseInt((String) holder.item_numbers.getText());
                if (numOfItems > 1) {
                    myDatabase.updateItemAmount(storeItemsArrayList.get(holder.getAbsoluteAdapterPosition()), numOfItems-1);
                    holder.item_numbers.setText(String.valueOf(numOfItems - 1));
                }
                if(numOfItems == 1){
                    myDatabase.deleteItem(storeItemsArrayList.get(holder.getAbsoluteAdapterPosition()).getItemID()+"");
                    holder.item_numbers.setText(String.valueOf(0));
                    storeItemsArrayList.remove(holder.getAbsoluteAdapterPosition());
                    notifyItemRemoved(holder.getAbsoluteAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return storeItemsArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView item_image;
        TextView item_name, item_details, item_numbers;
        ImageButton buttonPlus, buttonMinus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item_image = itemView.findViewById(R.id.store_item_image);
            item_name  = itemView.findViewById(R.id.activity_store_item_name);
            item_details = itemView.findViewById(R.id.activity_store_item_details);
            item_numbers = itemView.findViewById(R.id.activity_store_item_numbers);
//
            buttonPlus = itemView.findViewById(R.id.activity_store_imageButton_plus);
            buttonMinus= itemView.findViewById(R.id.activity_store_imageButton_minus);
        }
    }


}
