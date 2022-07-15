package com.graduation.farmerfriend.caching_room.Seed;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.constants.Constants;
import com.graduation.farmerfriend.e_commerce.ui.ECommerceFragmentDirections;
import com.graduation.farmerfriend.ecommerce_models.PostCart;
import com.graduation.farmerfriend.home.HomeFragmentDirections;
import com.graduation.farmerfriend.repos.EcommerceRepo;
import com.graduation.farmerfriend.sharedPreferences.SharedPref;

import java.util.ArrayList;
import java.util.Locale;

public class ViewRecycleSeedsAdapter extends RecyclerView.Adapter<ViewRecycleSeedsAdapter.ViewHolder> {
    private ArrayList<Seed> data;

    private final Context context;
    private final EcommerceRepo ecommerceRepo;
    private final SharedPref sharedPref;
    private final String fragment_Name;
    private Boolean internet;

    public ViewRecycleSeedsAdapter(Context con, ArrayList<Seed> data, String fragment_Name) {
        this.context = con;
        this.data = data;
        this.fragment_Name = fragment_Name;
        ecommerceRepo = EcommerceRepo.getInstance();
        sharedPref = new SharedPref(con, Constants.MAIN_SHARED_PREFERENCES);
    }


    public ViewRecycleSeedsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_container, parent, false);
        return new ViewRecycleSeedsAdapter.ViewHolder(view);
    }

    public void onBindViewHolder(ViewRecycleSeedsAdapter.ViewHolder holder, int position) {
//        holder.image.setImageResource(data[position].getImage());
        holder.text_name.setText(data.get(position).productName);
        holder.text_price.setText(String.format(Locale.US, "%.2feg", data.get(position).price));
        if (data.get(position).productImage != null) {
            String[] imageName = data.get(position).productImage.split("s/");
            Glide.with(context).load("http://teamweb992022-001-site1.htempurl.com/productImages/" + imageName[1]).into(holder.image);
        }
//        holder.text_discount.setText(data[position].getDiscount());

//        Glide.with(context).load("https://img-s-msn-com.akamaized.net/tenant/amp/entityid/AAUEAjj.img?h=748&w=1119&m=6&q=60&o=f&l=f").into(holder.image);
        int productId = data.get(position).productId;

        holder.itemView.setOnClickListener(view -> {
            switch (fragment_Name) {
                case "Ecommerce": {
                    ECommerceFragmentDirections.ActionECommerceFragmentToItemDescriptionFragment action = ECommerceFragmentDirections.actionECommerceFragmentToItemDescriptionFragment();
                    action.setId(productId);
                    Navigation.findNavController(holder.itemView).navigate(action);
                    break;
                }
                case "Home": {
                    HomeFragmentDirections.ActionHomeFragmentToItemDescriptionFragment actionHomeFragmentToItemDescriptionFragment = HomeFragmentDirections.actionHomeFragmentToItemDescriptionFragment();
                    actionHomeFragmentToItemDescriptionFragment.setId(productId);
                    Navigation.findNavController(holder.itemView).navigate(actionHomeFragmentToItemDescriptionFragment);
                    break;
                }
                default: {
                    break;
                }
            }
        });
        holder.addToCart.setOnClickListener(view -> {
            if (getConnectivityStatus(context)) {
                if (!sharedPref.getStringPref(Constants.USER_ID).isEmpty() && sharedPref.getStringPref(Constants.USER_ID) != null) {
                    PostCart postCart = new PostCart(data.get(holder.getAbsoluteAdapterPosition()).productId, sharedPref.getStringPref(Constants.USER_ID), 1);
                    ecommerceRepo.addToCart(postCart);
                    Toast.makeText(context, "تمت اضافة " + data.get(holder.getAbsoluteAdapterPosition()).productName + " لشنطة التسوق", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "يرجى تسجيل الدخول حتى تستطيع الاضافة الى العربة ", Toast.LENGTH_SHORT).show();
                    // Navigation.findNavController(view).navigate(R.id.loginFragment);
                }
            } else {
                Toast.makeText(context, "لا يوجد اتصال بالانترنت ", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView text_name;
        TextView text_price;
        TextView addToCart;
//        TextView text_discount ;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.product_image);
            text_name = itemView.findViewById(R.id.product_name);
            text_price = itemView.findViewById(R.id.product_price);
            addToCart = itemView.findViewById(R.id.btnAddToCart);
        }
    }

    public Boolean getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                internet = true;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                internet = true;
            }
        } else {
            internet = false;
        }
        return internet;
    }

}
