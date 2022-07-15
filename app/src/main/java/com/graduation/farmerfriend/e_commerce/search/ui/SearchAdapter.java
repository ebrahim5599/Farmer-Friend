package com.graduation.farmerfriend.e_commerce.search.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.constants.Constants;
import com.graduation.farmerfriend.e_commerce.search.pojo.SearchResultPojo;
import com.graduation.farmerfriend.ecommerce_models.PostCart;
import com.graduation.farmerfriend.repos.EcommerceRepo;
import com.graduation.farmerfriend.sharedPreferences.SharedPref;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private List<SearchResultPojo> list = new ArrayList<>();
    private Context context;
    private final EcommerceRepo ecommerceRepo;
    private final SharedPref sharedPref;

    public SearchAdapter(Context context) {
        this.context = context;
        ecommerceRepo = EcommerceRepo.getInstance();
        sharedPref = new SharedPref(context, Constants.MAIN_SHARED_PREFERENCES);
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.product_name.setText(list.get(position).getProductName());
        holder.product_price.setText(String.valueOf(list.get(position).getPrice()));

        if (!String.valueOf(list.get(position).getDescription()).equals("null"))
            holder.product_description.setText(String.valueOf(list.get(position).getDescription()));
        else
            holder.product_description.setText(R.string.no_item_description);

        if (list.get(position).getProductImage() != null)
            Glide.with(context).load("http://teamweb992022-001-site1.htempurl.com/" + list.get(position)
                    .getProductImage()).into(holder.product_image);

        int productId = list.get(position).productId;

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, holder.product_name.getText(), Toast.LENGTH_SHORT).show();
                SearchFragmentDirections.ActionSearchFragmentToItemDescriptionFragment action = SearchFragmentDirections.actionSearchFragmentToItemDescriptionFragment();
                action.setId(productId);
                Navigation.findNavController(holder.itemView).navigate(action);
            }
        });
        holder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!sharedPref.getStringPref(Constants.USER_ID).isEmpty() && sharedPref.getStringPref(Constants.USER_ID) != null) {
                    PostCart postCart = new PostCart(list.get(holder.getAbsoluteAdapterPosition()).productId, sharedPref.getStringPref(Constants.USER_ID), 1);
                    ecommerceRepo.addToCart(postCart);
                    Toast.makeText(context, "تمت اضافة " + list.get(holder.getAbsoluteAdapterPosition()).productName + " لشنطة التسوق", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "يرجى تسجيل الدخول حتى تستطيع الاضافة الى العربة ", Toast.LENGTH_SHORT).show();
                    //TODO navigate to sign in fragment
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<SearchResultPojo> searchResultList) {
        list = searchResultList;
        notifyDataSetChanged();
    }

    public void setListEmpty() {
        list.clear();
        notifyDataSetChanged();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {

        ImageView product_image;
        TextView product_name, product_description, product_price, addToCart;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            product_image = itemView.findViewById(R.id.search_result_product_image_view);
            product_name = itemView.findViewById(R.id.search_result_product_name_text_view);
            product_description = itemView.findViewById(R.id.search_result_product_description_text_view);
            product_price = itemView.findViewById(R.id.search_result_product_price_text_view);
            addToCart = itemView.findViewById(R.id.search_button_add_to_cart);
        }
    }
}
