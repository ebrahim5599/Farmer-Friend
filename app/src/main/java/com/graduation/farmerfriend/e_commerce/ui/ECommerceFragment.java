package com.graduation.farmerfriend.e_commerce.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.databinding.FragmentECommerceBinding;
import com.graduation.farmerfriend.e_commerce.Data;
import com.graduation.farmerfriend.e_commerce.ViewRecycleProductsAdapter;
import com.graduation.farmerfriend.e_commerce.ui.cart.CartActivity;
import com.graduation.farmerfriend.e_commerce.ui.deals.DealsActivity;

import com.graduation.farmerfriend.e_commerce.ui.wishlist.WishlistActivity;

import com.graduation.farmerfriend.e_commerce.ui.product_activity.ProductActivity;



public class ECommerceFragment extends Fragment {
    FragmentECommerceBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentECommerceBinding.inflate(inflater,container,false);
        View view = binding.getRoot();


        binding.fragmentECommerceEditTextSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView,new Search()).addToBackStack(null).commit();
//                Intent intent = new Intent(getContext(),SearchActivity.class);
//                startActivity(intent);
            }
        });

        binding.fragmentECommerceSeedsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProductActivity.class);
                intent.putExtra("FRAGMENT_NO","1");
                startActivity(intent);
            }
        });

        binding.fragmentECommerceFertilizersView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProductActivity.class);
                intent.putExtra("FRAGMENT_NO","2");
                startActivity(intent);
            }
        });

        binding.fragmentECommerceToolsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProductActivity.class);
                intent.putExtra("FRAGMENT_NO","3");
                startActivity(intent);
            }
        });
        binding.fragmentECommerceTextViewWishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), WishlistActivity.class);
                startActivity(intent);
            }
        });


        binding.fragmentECommerceTextViewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CartActivity.class);
                startActivity(intent);
            }
        });

        binding.fragmentECommerceTextviewBestSellerViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getContext(), "Best Seller", Toast.LENGTH_SHORT).show();
                Intent n = new Intent(getActivity(), DealsActivity.class);
                n.putExtra("BEST_SELLER", "true");
                startActivity(n);
            }
        });

        binding.fragmentECommerceTextviewHotDealsViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getContext(), "Hot deals", Toast.LENGTH_SHORT).show();
                Intent n = new Intent(getActivity(), DealsActivity.class);
                n.putExtra("BEST_SELLER", "false");
                startActivity(n);
            }
        });

        binding.fragmentECommerceTextviewSeedsViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProductActivity.class);
                intent.putExtra("FRAGMENT_NO","1");
                startActivity(intent);
            }
        });

        binding.fragmentECommerceTextviewFertilizersViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProductActivity.class);
                intent.putExtra("FRAGMENT_NO","2");
                startActivity(intent);            }
        });

        binding.fragmentECommerceTextviewToolsViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProductActivity.class);
                intent.putExtra("FRAGMENT_NO","3");
                startActivity(intent);            }
        });

        Data[] data_best_seller = {
                new Data(R.drawable.image1, "rice", "50$", "no"),
                new Data(R.drawable.image2, "fyuegif", "100$", "50%"),
                new Data(R.drawable.image3,"rice","50$","no"),
                new Data(R.drawable.image4,"fyuegif","100$","50%"),
                new Data(R.drawable.image5,"rice","50$","no"),
                new Data(R.drawable.image6,"fyuegif","100$","50%"),
                new Data(R.drawable.image7,"rice","50$","no"),
                new Data(R.drawable.image8,"fyuegif","100$","50%")
        };

        binding.fragmentECommerceRecycleViewBestSeller.setLayoutManager((new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)));
        ViewRecycleProductsAdapter recycleViewAdapterBest = new ViewRecycleProductsAdapter(getContext(), data_best_seller);
        binding.fragmentECommerceRecycleViewBestSeller.setAdapter(recycleViewAdapterBest);

        Data[] data_hot_deals = {
                new Data(R.drawable.image1, "rice", "50$", "no"),
                new Data(R.drawable.image2, "fyuegif", "100$", "50%"),
                new Data(R.drawable.image3,"rice","50$","no"),
                new Data(R.drawable.image4,"fyuegif","100$","50%"),
                new Data(R.drawable.image5,"rice","50$","no"),
                new Data(R.drawable.image6,"fyuegif","100$","50%"),
                new Data(R.drawable.image7,"rice","50$","no"),
                new Data(R.drawable.image8,"fyuegif","100$","50%")
        };

        binding.fragmentECommerceRecycleViewHotDeals.setLayoutManager((new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)));
        ViewRecycleProductsAdapter recycleViewAdapterHot = new ViewRecycleProductsAdapter(getContext(), data_hot_deals);
        binding.fragmentECommerceRecycleViewHotDeals.setAdapter(recycleViewAdapterHot);

        Data[] data_seeds = {
            new Data(R.drawable.image1, "rice", "50$", "no"),
                new Data(R.drawable.image2, "fyuegif", "100$", "50%"),
                new Data(R.drawable.image3,"rice","50$","no"),
                new Data(R.drawable.image4,"fyuegif","100$","50%"),
                new Data(R.drawable.image5,"rice","50$","no"),
                new Data(R.drawable.image6,"fyuegif","100$","50%"),
                new Data(R.drawable.image7,"rice","50$","no"),
                new Data(R.drawable.image8,"fyuegif","100$","50%")
        };

        binding.fragmentECommerceRecycleviewSeeds.setLayoutManager((new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)));
        ViewRecycleProductsAdapter recycleViewAdapters = new ViewRecycleProductsAdapter(getContext(), data_seeds);
        binding.fragmentECommerceRecycleviewSeeds.setAdapter(recycleViewAdapters);

        Data[] data_fertilize = {
                new Data(R.drawable.image1, "rice", "50$", "no"),
                new Data(R.drawable.image2, "fyuegif", "100$", "50%"),
                new Data(R.drawable.image3,"rice","50$","no"),
                new Data(R.drawable.image4,"fyuegif","100$","50%"),
                new Data(R.drawable.image5,"rice","50$","no"),
                new Data(R.drawable.image6,"fyuegif","100$","50%"),
                new Data(R.drawable.image7,"rice","50$","no"),
                new Data(R.drawable.image8,"fyuegif","100$","50%")
        };

        binding.fragmentECommerceRecycleviewFertilizers.setLayoutManager((new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)));
        ViewRecycleProductsAdapter recycleViewAdapterf = new ViewRecycleProductsAdapter(getContext(),data_fertilize);
        binding.fragmentECommerceRecycleviewFertilizers.setAdapter(recycleViewAdapterf);

        Data[] data_tools = {
                new Data(R.drawable.image1, "rice", "50$", "no"),
                new Data(R.drawable.image2, "fyuegif", "100$", "50%"),
                new Data(R.drawable.image3,"rice","50$","no"),
                new Data(R.drawable.image4,"fyuegif","100$","50%"),
                new Data(R.drawable.image5,"rice","50$","no"),
                new Data(R.drawable.image6,"fyuegif","100$","50%"),
                new Data(R.drawable.image7,"rice","50$","no"),
                new Data(R.drawable.image8,"fyuegif","100$","50%")
        };

        binding.fragmentECommerceRecycleviewTools.setLayoutManager((new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)));
        ViewRecycleProductsAdapter recycleViewAdaptert = new ViewRecycleProductsAdapter(getContext(),data_tools);
        binding.fragmentECommerceRecycleviewTools.setAdapter(recycleViewAdaptert);

        return view;
    }
}