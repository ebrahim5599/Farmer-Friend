package com.graduation.farmerfriend.e_commerce.ui.deals;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.e_commerce.ui.ECommerceFragmentDirections;
import com.graduation.farmerfriend.ecommerce_models.Product;

import java.util.ArrayList;

public class HotDealsAdapter extends RecyclerView.Adapter<HotDealsAdapter.HotDealsViewHolder> {
    ArrayList<Product> productArrayList;
    Context context;
    private int productId;

    HotDealsAdapter(ArrayList<Product> productArrayList, Context context) {
        this.productArrayList = productArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public HotDealsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HotDealsViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_container, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull HotDealsViewHolder holder, int position) {
        holder.productPriceTextView.setText(String.format("%deg", productArrayList.get(position).price));
        holder.productNameTextView.setText(productArrayList.get(position).productName);

        if (productArrayList.get(position).productImage != null) {
            String[] imageName = productArrayList.get(position).productImage.split("s/");
            Glide.with(context).load("http://teamweb2022-001-site1.itempurl.com/productImages/" + imageName[1]).into(holder.productImageView);
        }
        productId = productArrayList.get(position).productId;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HotDealsFragmentDirections.ActionHotDealsFragmentToItemDescriptionFragment action = HotDealsFragmentDirections.actionHotDealsFragmentToItemDescriptionFragment();
                action.setId(productId);
                Navigation.findNavController(holder.itemView).navigate(action);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public class HotDealsViewHolder extends RecyclerView.ViewHolder {
        private TextView productNameTextView;
        private TextView productPriceTextView;
        private ShapeableImageView productImageView;

        public HotDealsViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(R.id.product_name);
            productPriceTextView = itemView.findViewById(R.id.product_price);
            productImageView = itemView.findViewById(R.id.product_image);
        }
    }
}
