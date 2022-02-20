//package com.graduation.farmerfriend.e_commerce.ui.wishlist;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import android.os.Bundle;
//import com.graduation.farmerfriend.databinding.ActivityWishlistBinding;
//
//public class WishlistActivity extends AppCompatActivity {
//    ActivityWishlistBinding binding;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivityWishlistBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//        WishlistAdapter wishlistAdapter = new WishlistAdapter();
//
//        binding.wishlistRecyclerView.setAdapter(wishlistAdapter);
//        binding.wishlistRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//    }
//}