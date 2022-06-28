package com.graduation.farmerfriend.e_commerce.ui.deals;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.constants.Constants;
import com.graduation.farmerfriend.e_commerce.ui.ECommerceFragmentDirections;
import com.graduation.farmerfriend.ecommerce_models.PostCart;
import com.graduation.farmerfriend.ecommerce_models.Product;
import com.graduation.farmerfriend.repos.EcommerceRepo;
import com.graduation.farmerfriend.sharedPreferences.SharedPref;

import java.util.ArrayList;

public class BestSellerAdapter extends RecyclerView.Adapter<BestSellerAdapter.BestSellerViewHolder> {
    ArrayList<Product> productArrayList;

    Context context;
    private final SharedPref sharedPref;
    private final EcommerceRepo ecommerceRepo;

    BestSellerAdapter(ArrayList<Product> productArrayList,
                      Context context) {
        this.productArrayList = productArrayList;
        this.context = context;
        sharedPref = new SharedPref(context, Constants.MAIN_SHARED_PREFERENCES);
        ecommerceRepo = EcommerceRepo.getInstance();
    }

    @NonNull
    @Override
    public BestSellerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BestSellerViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_container, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BestSellerViewHolder holder, int position) {
        holder.productPriceTextView.setText(String.format("%.2f eg", productArrayList.get(position).price));
        holder.productNameTextView.setText(productArrayList.get(position).productName);

        if (productArrayList.get(position).productImage != null) {
            String[] imageName = productArrayList.get(position).productImage.split("s/");
            Glide.with(context).load("http://teamweb2022-001-site1.itempurl.com/productImages/" + imageName[1]).into(holder.productImageView);
        }else {
        }
        int productId = productArrayList.get(position).productId;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BestSellerFragmentDirections.ActionBestSellerFragmentToItemDescriptionFragment action = BestSellerFragmentDirections.actionBestSellerFragmentToItemDescriptionFragment();
                action.setId(productId);
                Navigation.findNavController(holder.itemView).navigate(action);
            }
        });
        holder.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!sharedPref.getStringPref(Constants.USER_ID, "").isEmpty() && sharedPref.getStringPref(Constants.USER_ID, "") != null) {
                    PostCart postCart = new PostCart(productArrayList.get(holder.getAbsoluteAdapterPosition()).productId, sharedPref.getStringPref(Constants.USER_ID, ""),1);
                    ecommerceRepo.addToCart(postCart);
                    Toast.makeText(context, "تمت اضافة " + productArrayList.get(holder.getAbsoluteAdapterPosition()).productName + " لشنطة التسوق", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "يرجى تسجيل الدخول حتى تستطيع الاضافة الى العربة ", Toast.LENGTH_SHORT).show();
                    //TODO navigate to sign in fragment
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public class BestSellerViewHolder extends RecyclerView.ViewHolder {

        private TextView productNameTextView;
        private TextView productPriceTextView;
        private ShapeableImageView productImageView;
        private TextView btnAddToCart;

        public BestSellerViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(R.id.product_name);
            productPriceTextView = itemView.findViewById(R.id.product_price);
            productImageView = itemView.findViewById(R.id.product_image);
            btnAddToCart = itemView.findViewById(R.id.btnAddToCart);

        }
    }

}
