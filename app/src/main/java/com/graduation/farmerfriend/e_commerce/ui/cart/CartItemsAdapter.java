package com.graduation.farmerfriend.e_commerce.ui.cart;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.bitmaps.BitmapHandling;
import com.graduation.farmerfriend.ecommerce_models.Cart;
import com.graduation.farmerfriend.ecommerce_models.PatchCart;

import java.util.ArrayList;
import java.util.Locale;

public class CartItemsAdapter extends RecyclerView.Adapter<CartItemsAdapter.CartItemViewHolder> {

    Context context;
    ArrayList<Cart> carts;
    private static final String TAG = "CartItemsAdapter";
    private final CartViewModel cartViewModel;
    String userId;

    public CartItemsAdapter(Fragment fragment, Context context, ArrayList<Cart> carts, String userId) {
        this.context = context;
        this.carts = carts;
        this.userId = userId;
        cartViewModel = new ViewModelProvider(fragment).get(CartViewModel.class);
    }

    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartItemViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.cart_container, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
        holder.buttonMinus.setOnClickListener(view -> {
            int numOfItems = carts.get(holder.getAbsoluteAdapterPosition()).quantity;

            carts.get(holder.getAbsoluteAdapterPosition()).quantity -= 1;
            int cartId = carts.get(holder.getAbsoluteAdapterPosition()).cartId;
            if (numOfItems > 1) {
                holder.textViewNumberOfItems.setText(String.valueOf(numOfItems - 1));
                ArrayList<PatchCart> patchCarts = new ArrayList<>();
                PatchCart patchCart = new PatchCart();
                patchCart.value = numOfItems - 1;
                patchCarts.add(patchCart);
                cartViewModel.changeQuantity(cartId, patchCarts);
                holder.textViewTotalPrice.setText(String.format(Locale.US,"%.2f eg", carts.get(holder.getAbsoluteAdapterPosition()).product.price * carts.get(holder.getAbsoluteAdapterPosition()).quantity));
            }
            else {
                cartViewModel.deleteProduct(carts.get(holder.getAbsoluteAdapterPosition()).productId,userId);
                carts.remove(holder.getAbsoluteAdapterPosition());
                notifyItemRemoved(holder.getAbsoluteAdapterPosition());
            }
        });


        holder.buttonPlus.setOnClickListener(view -> {
            int numOfItems = carts.get(holder.getAbsoluteAdapterPosition()).quantity;
            carts.get(holder.getAbsoluteAdapterPosition()).quantity += 1;
            int cartId = carts.get(holder.getAbsoluteAdapterPosition()).cartId;
            holder.textViewNumberOfItems.setText(String.valueOf(numOfItems + 1));
            ArrayList<PatchCart> patchCarts = new ArrayList<>();
            PatchCart patchCart = new PatchCart();
            patchCart.value = numOfItems + 1;
            patchCarts.add(patchCart);
            cartViewModel.changeQuantity(cartId, patchCarts);
            holder.textViewTotalPrice.setText(String.format(Locale.US,"%.2f eg", carts.get(holder.getAbsoluteAdapterPosition()).product.price * carts.get(holder.getAbsoluteAdapterPosition()).quantity));
        });
        holder.imageViewItem.setImageBitmap(BitmapHandling.decodeSampledBitmapFromResource(context.getResources(), R.drawable.no_product, 250, 100));
        holder.textViewPrice.setText(String.format(Locale.US,"%.2f eg", carts.get(position).product.price));
        holder.textViewNumberOfItems.setText(String.format(Locale.US,"%d", carts.get(position).quantity));
        holder.textViewItemName.setText(carts.get(position).product.productName);
        holder.textViewTotalPrice.setText(String.format(Locale.US,"%.2f eg", carts.get(position).product.price * carts.get(position).quantity));
        Log.d(TAG, "onBindViewHolder: " + carts.get(position).quantity);


        holder.buttonDelete.setOnClickListener(view -> {
            cartViewModel.deleteProduct(carts.get(holder.getAbsoluteAdapterPosition()).productId, userId);
            carts.remove(holder.getAbsoluteAdapterPosition());
            notifyItemRemoved(holder.getAbsoluteAdapterPosition());
        });
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    static class CartItemViewHolder extends RecyclerView.ViewHolder {
        TextView textViewPrice;
        ShapeableImageView imageViewItem;
        ImageButton buttonPlus;
        ImageButton buttonMinus;
        TextView textViewNumberOfItems;
        TextView buttonWishlist;
        TextView buttonDelete;
        TextView textViewItemName;
        TextView textViewTotalPrice;

        public CartItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPrice = itemView.findViewById(R.id.textView_price);
            imageViewItem = itemView.findViewById(R.id.imageViewItem);
            buttonPlus = itemView.findViewById(R.id.imageButton_plus);
            buttonDelete = itemView.findViewById(R.id.wishlist_button_add_to_cart);
            buttonWishlist = itemView.findViewById(R.id.wishlist_button_wishlist);
            buttonMinus = itemView.findViewById(R.id.imageButton_minus);
            textViewNumberOfItems = itemView.findViewById(R.id.textViewNumberItems);
            textViewItemName = itemView.findViewById(R.id.textViewItemName);
            textViewTotalPrice = itemView.findViewById(R.id.TextviewTotalPrice);
        }

    }
}
