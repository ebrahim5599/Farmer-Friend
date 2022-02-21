package com.graduation.farmerfriend.e_commerce.ui.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.bitmaps.BitmapHandling;

public class CartItemsAdapter extends RecyclerView.Adapter<CartItemsAdapter.CartItemViewHolder> {

    Context context;

    public CartItemsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartItemViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.cart_container, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
        holder.buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numOfItems = Integer.parseInt((String) holder.textViewNumberOfItems.getText());
                if (numOfItems > 0) {
                    holder.textViewNumberOfItems.setText(String.valueOf(numOfItems - 1));
                }
            }
        });
        holder.buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numOfItems = Integer.parseInt((String) holder.textViewNumberOfItems.getText());
                holder.textViewNumberOfItems.setText(String.valueOf(numOfItems + 1));
            }
        });
        holder.imageViewItem.setImageBitmap(BitmapHandling.decodeSampledBitmapFromResource(context.getResources(), R.drawable.corn00, 250, 100));
//        holder.imageViewItem.setImageBitmap(BitmapHandling.getRoundedCornerBitmap(BitmapHandling.decodeSampledBitmapFromResource(context.getResources(), R.drawable.corn, 250, 100), 16));
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class CartItemViewHolder extends RecyclerView.ViewHolder {
        TextView textViewPrice;
        ShapeableImageView imageViewItem;
        ImageButton buttonPlus;
        ImageButton buttonMinus;
        TextView textViewNumberOfItems;
        TextView buttonWishlist;
        TextView buttonDelete;

        public CartItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPrice = itemView.findViewById(R.id.textView_price);
            imageViewItem = itemView.findViewById(R.id.imageViewItem);
            buttonPlus = itemView.findViewById(R.id.imageButton_plus);
            buttonDelete = itemView.findViewById(R.id.wishlist_button_add_to_cart);
            buttonWishlist = itemView.findViewById(R.id.wishlist_button_wishlist);
            buttonMinus = itemView.findViewById(R.id.imageButton_minus);
            textViewNumberOfItems = itemView.findViewById(R.id.textViewNumberItems);
        }

    }
}
