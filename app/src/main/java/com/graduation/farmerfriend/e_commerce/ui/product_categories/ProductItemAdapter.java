package com.graduation.farmerfriend.e_commerce.ui.product_categories;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import com.graduation.farmerfriend.constants.Constants;
import com.graduation.farmerfriend.ecommerce_models.PostCart;
import com.graduation.farmerfriend.ecommerce_models.Product;
import com.graduation.farmerfriend.home.HomeFragmentDirections;
import com.graduation.farmerfriend.repos.EcommerceRepo;
import com.graduation.farmerfriend.sharedPreferences.SharedPref;

import java.util.ArrayList;
import java.util.Locale;

public class ProductItemAdapter extends RecyclerView.Adapter<ProductItemAdapter.ProductItemViewHolder> {
    private static final String TAG = "ProductItemAdapter";
    private final ArrayList<Product> productArrayList;
    Context context;
    private final String fragment_Name;
    private final EcommerceRepo ecommerceRepo;
    private final SharedPref sharedPref;
    private Boolean internet ;

    ProductItemAdapter(ArrayList<Product> productArrayList, Context context, String fragment_Name) {
        this.productArrayList = productArrayList;
        this.context = context;
        this.fragment_Name = fragment_Name;
        ecommerceRepo = EcommerceRepo.getInstance();
        sharedPref = new SharedPref(context, Constants.MAIN_SHARED_PREFERENCES);
        Log.d(TAG, "ProductItemAdapter: " + productArrayList);
    }

    @NonNull
    @Override
    public ProductItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductItemViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_container, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductItemViewHolder holder, int position) {
        holder.textViewProductName.setText(productArrayList.get(position).productName);
        holder.textViewProductPrice.setText(String.format(Locale.US, "%5.2feg", productArrayList.get(position).price));

        if (productArrayList.get(position).productImage != null) {
            String[] imageName = productArrayList.get(position).productImage.split("s/");
            Glide.with(context).load("http://teamweb992022-001-site1.htempurl.com/productImages/" + imageName[1]).into(holder.imageView);
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
                    default: {
                        Log.d(TAG, "onClick: ");
                    }
                }

            }
        });
        holder.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getConnectivityStatus(context)) {
                    if (!sharedPref.getStringPref(Constants.USER_ID, "").isEmpty() && sharedPref.getStringPref(Constants.USER_ID, "") != null) {
                        PostCart postCart = new PostCart(productArrayList.get(holder.getAbsoluteAdapterPosition()).productId, sharedPref.getStringPref(Constants.USER_ID, ""), 1);
                        ecommerceRepo.addToCart(postCart);
                        Toast.makeText(context, "تمت اضافة " + productArrayList.get(holder.getAbsoluteAdapterPosition()).productName + " لشنطة التسوق", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "يرجى تسجيل الدخول حتى تستطيع الاضافة الى العربة ", Toast.LENGTH_SHORT).show();
                        //TODO navigate to sign in fragment
                    }
                }else {
                    Toast.makeText(context, "لا يوجد اتصال بالانترنت ", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public static class ProductItemViewHolder extends RecyclerView.ViewHolder {
        TextView textViewProductName;
        TextView textViewProductPrice;
        ImageView imageView;
        TextView btnAddToCart;

        public ProductItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewProductName = itemView.findViewById(R.id.product_name);
            textViewProductPrice = itemView.findViewById(R.id.product_price);
            imageView = itemView.findViewById(R.id.product_image);
            btnAddToCart = itemView.findViewById(R.id.btnAddToCart);
        }
    }

    public Boolean getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                internet = true ;
                return internet;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                internet = true ;
                return internet;
            }
        } else {
            internet = false ;
            return internet;
        }
        return internet;
    }
}
