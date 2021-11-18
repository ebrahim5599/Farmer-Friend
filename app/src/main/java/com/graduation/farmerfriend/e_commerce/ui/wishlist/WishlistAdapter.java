package com.graduation.farmerfriend.e_commerce.ui.wishlist;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.graduation.farmerfriend.R;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder> {


    @NonNull
    @Override
    public WishlistAdapter.WishlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WishlistAdapter.WishlistViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.wishlist_conntainer, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WishlistAdapter.WishlistViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    protected class WishlistViewHolder extends RecyclerView.ViewHolder {

        public WishlistViewHolder(@NonNull View itemView) {
            super(itemView);
        }

    }
}
