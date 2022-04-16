package com.graduation.farmerfriend.home;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.e_commerce.ui.ECommerceFragmentDirections;
import com.graduation.farmerfriend.ecommerce_models.Product;

import java.util.ArrayList;

public class EcommerceAdapter extends RecyclerView.Adapter<EcommerceAdapter.EcommerceViewHolder> {
    ArrayList<Product> productArrayList;
    private static final String TAG = "EcommerceAdapter";
    Context context;

    EcommerceAdapter(ArrayList<Product> productArrayList, Context context) {
        this.productArrayList = productArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public EcommerceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new EcommerceViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.e_commerce_container, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EcommerceViewHolder holder, int position) {
        holder.textViewPrice.setText(String.format("%deg", productArrayList.get(position).price));
        holder.textViewName.setText(productArrayList.get(position).productName);
        if (productArrayList.get(position).productImage != null) {
            String[] imageName = productArrayList.get(position).productImage.split("s/");
            Glide.with(context).load("http://teamweb2022-001-site1.itempurl.com/productImages/" + imageName[1]).into(holder.imageView);
        }
        int productId = productArrayList.get(position).productId;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragmentDirections.ActionHomeFragmentToItemDescriptionFragment action = HomeFragmentDirections.actionHomeFragmentToItemDescriptionFragment();
                action.setId(productId);
                Navigation.findNavController(holder.itemView).navigate(action);

            }
        });

    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    class EcommerceViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewPrice;
        TextView textViewName;

        public EcommerceViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.product_image);
            textViewName = itemView.findViewById(R.id.product_name);
            textViewPrice = itemView.findViewById(R.id.product_price);

        }

    }
}
