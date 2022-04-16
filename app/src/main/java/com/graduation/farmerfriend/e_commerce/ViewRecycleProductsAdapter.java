package com.graduation.farmerfriend.e_commerce;

import com.bumptech.glide.Glide;
import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.e_commerce.ui.ECommerceFragmentDirections;
import com.graduation.farmerfriend.ecommerce_models.Product;
import com.graduation.farmerfriend.home.HomeFragmentDirections;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewRecycleProductsAdapter extends RecyclerView.Adapter<ViewRecycleProductsAdapter.ViewHolder> {

    private ArrayList<Product> data;
    private final Context context;

    public ViewRecycleProductsAdapter(Context con, ArrayList<Product> data) {
        this.context = con;
        this.data = data;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_container, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.image.setImageResource(data[position].getImage());
        holder.text_name.setText(data.get(position).productName);
        holder.text_price.setText(String.format("%deg", data.get(position).price));
        if (data.get(position).productImage != null) {
            String[] imageName = data.get(position).productImage.split("s/");
            Glide.with(context).load("http://teamweb2022-001-site1.itempurl.com/productImages/" + imageName[1]).into(holder.image);
        }
//        holder.text_discount.setText(data[position].getDiscount());

//        Glide.with(context).load("https://img-s-msn-com.akamaized.net/tenant/amp/entityid/AAUEAjj.img?h=748&w=1119&m=6&q=60&o=f&l=f").into(holder.image);
        int productId = data.get(position).productId;

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ECommerceFragmentDirections.ActionECommerceFragmentToItemDescriptionFragment action = ECommerceFragmentDirections.actionECommerceFragmentToItemDescriptionFragment();
                action.setId(productId);
                Navigation.findNavController(holder.itemView).navigate(action);
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
//        TextView text_discount ;

        public ViewHolder(View itemView) {
            super(itemView);
//            image = itemView.findViewById(R.id.image) ;
//            text_name = itemView.findViewById(R.id.text_name);
//            text_price = itemView.findViewById(R.id.text_price);
//            text_discount = itemView.findViewById(R.id.text_discount);
            image = itemView.findViewById(R.id.product_image);
            text_name = itemView.findViewById(R.id.product_name);
            text_price = itemView.findViewById(R.id.product_price);


        }
    }


}
