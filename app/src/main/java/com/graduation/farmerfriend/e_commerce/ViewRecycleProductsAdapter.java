package com.graduation.farmerfriend.e_commerce;

import android.content.Context;
import com.graduation.farmerfriend.R ;
import com.graduation.farmerfriend.e_commerce.ui.Item_description;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class ViewRecycleProductsAdapter extends RecyclerView.Adapter<ViewRecycleProductsAdapter.ViewHolder>{

    private Data[] data ;
    private Context context ;

    public ViewRecycleProductsAdapter(Context con, Data[] data){
        this.context = con ;
        this.data = data ;
    }

    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewrecycleviewproducts, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder( ViewHolder holder, int position) {
        holder.image.setImageResource(data[position].getImage());
        holder.text_name.setText(data[position].getName());
        holder.text_price.setText(data[position].getPrice());
        holder.text_discount.setText(data[position].getDiscount());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppCompatActivity activity = (AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,new Item_description()).addToBackStack(null).commit();
            }
    });
    }
    @Override
    public int getItemCount() {
        return data.length;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image ;
        TextView text_name ;
        TextView text_price ;
        TextView text_discount ;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image) ;
            text_name = itemView.findViewById(R.id.text_name);
            text_price = itemView.findViewById(R.id.text_price);
            text_discount = itemView.findViewById(R.id.text_discount);


        }
    }




}
