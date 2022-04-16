package com.graduation.farmerfriend.e_commerce.ui.product_categories;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.ecommerce_models.Product;
import com.graduation.farmerfriend.home.HomeFragmentDirections;

import java.util.ArrayList;

public class ProductItemAdapter extends RecyclerView.Adapter<ProductItemAdapter.ProductItemViewHolder> {
    private static final String TAG = "ProductItemAdapter";
    private final ArrayList<Product> productArrayList;
    Context context;
    private String fragment_Name;

    ProductItemAdapter(ArrayList<Product> productArrayList, Context context, String fragment_Name) {
        this.productArrayList = productArrayList;
        this.context = context;
        this.fragment_Name = fragment_Name;
        Log.d(TAG, "ProductItemAdapter: " + productArrayList);
    }

    @NonNull
    @Override
    public ProductItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductItemViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.product_container, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductItemViewHolder holder, int position) {
        holder.textViewProductName.setText(productArrayList.get(position).productName);
        holder.textViewProductPrice.setText(String.format("%deg", productArrayList.get(position).price));
        if (productArrayList.get(position).productImage != null) {
            String[] imageName = productArrayList.get(position).productImage.split("s/");
            Glide.with(context).load("http://teamweb2022-001-site1.itempurl.com/productImages/" + imageName[1]).into(holder.imageView);
        }
        int productId = productArrayList.get(position).productId;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (fragment_Name) {
                    case "fer": {
                        FertilizerProductsFragmentDirections.ActionFertilizerProductsFragmentToItemDescriptionFragment action = FertilizerProductsFragmentDirections.actionFertilizerProductsFragmentToItemDescriptionFragment();
                        action.setId(productId);
                        Navigation.findNavController(holder.itemView).navigate(action);
                        break;
                    }
                    case "seed": {
                        SeedProductsFragmentDirections.ActionSeedProductsFragmentToItemDescriptionFragment action = SeedProductsFragmentDirections.actionSeedProductsFragmentToItemDescriptionFragment();
                        action.setId(productId);
                        Navigation.findNavController(holder.itemView).navigate(action);
                        break;
                    }
                    case "tool": {
                        ToolProductsFragmentDirections.ActionToolProductsFragmentToItemDescriptionFragment action = ToolProductsFragmentDirections.actionToolProductsFragmentToItemDescriptionFragment();
                        action.setId(productId);
                        Navigation.findNavController(holder.itemView).navigate(action);
                        break;
                    }
                    default:{
                        Log.d(TAG, "onClick: ");
                    }
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public class ProductItemViewHolder extends RecyclerView.ViewHolder {
        TextView textViewProductName;
        TextView textViewProductPrice;
        ImageView imageView;

        public ProductItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewProductName = itemView.findViewById(R.id.product_name);
            textViewProductPrice = itemView.findViewById(R.id.product_price);
            imageView = itemView.findViewById(R.id.product_image);
        }
    }
}
