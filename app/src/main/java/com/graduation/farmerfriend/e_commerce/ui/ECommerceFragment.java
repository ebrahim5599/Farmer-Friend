package com.graduation.farmerfriend.e_commerce.ui;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.databinding.FragmentECommerceBinding;
import com.graduation.farmerfriend.e_commerce.Data;
import com.graduation.farmerfriend.e_commerce.ViewRecycleProductsAdapter;


public class ECommerceFragment extends Fragment {
    FragmentECommerceBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentECommerceBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        setHasOptionsMenu(true);



        binding.fragmentECommerceSeedsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.seedProductsFragment);
            }
        });

        binding.fragmentECommerceFertilizersView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.fertilizerProductsFragment);
            }
        });

        binding.fragmentECommerceToolsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).navigate(R.id.toolProductsFragment);
            }
        });



        binding.fragmentECommerceTextviewBestSellerViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.bestSellerFragment);
            }
        });

        binding.fragmentECommerceTextviewHotDealsViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.hotDealsFragment);
            }
        });

        binding.fragmentECommerceTextviewSeedsViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).navigate(R.id.seedProductsFragment);
            }
        });

        binding.fragmentECommerceTextviewFertilizersViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.fertilizerProductsFragment);
            }
        });

        binding.fragmentECommerceTextviewToolsViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.toolProductsFragment);
            }
        });

        Data[] data_best_seller = {
                new Data(R.drawable.image1, "rice", "50$", "no"),
                new Data(R.drawable.image2, "fyuegif", "100$", "50%"),
                new Data(R.drawable.image3, "rice", "50$", "no"),
                new Data(R.drawable.image4, "fyuegif", "100$", "50%"),
                new Data(R.drawable.image5, "rice", "50$", "no"),
                new Data(R.drawable.image6, "fyuegif", "100$", "50%"),
                new Data(R.drawable.image7, "rice", "50$", "no"),
                new Data(R.drawable.image8, "fyuegif", "100$", "50%")
        };

        binding.fragmentECommerceRecycleViewBestSeller.setLayoutManager((new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)));
        ViewRecycleProductsAdapter recycleViewAdapterBest = new ViewRecycleProductsAdapter(getContext(), data_best_seller);
        binding.fragmentECommerceRecycleViewBestSeller.setAdapter(recycleViewAdapterBest);

        Data[] data_hot_deals = {
                new Data(R.drawable.image1, "rice", "50$", "no"),
                new Data(R.drawable.image2, "fyuegif", "100$", "50%"),
                new Data(R.drawable.image3, "rice", "50$", "no"),
                new Data(R.drawable.image4, "fyuegif", "100$", "50%"),
                new Data(R.drawable.image5, "rice", "50$", "no"),
                new Data(R.drawable.image6, "fyuegif", "100$", "50%"),
                new Data(R.drawable.image7, "rice", "50$", "no"),
                new Data(R.drawable.image8, "fyuegif", "100$", "50%")
        };

        binding.fragmentECommerceRecycleViewHotDeals.setLayoutManager((new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)));
        ViewRecycleProductsAdapter recycleViewAdapterHot = new ViewRecycleProductsAdapter(getContext(), data_hot_deals);
        binding.fragmentECommerceRecycleViewHotDeals.setAdapter(recycleViewAdapterHot);

        Data[] data_seeds = {
                new Data(R.drawable.image1, "rice", "50$", "no"),
                new Data(R.drawable.image2, "fyuegif", "100$", "50%"),
                new Data(R.drawable.image3, "rice", "50$", "no"),
                new Data(R.drawable.image4, "fyuegif", "100$", "50%"),
                new Data(R.drawable.image5, "rice", "50$", "no"),
                new Data(R.drawable.image6, "fyuegif", "100$", "50%"),
                new Data(R.drawable.image7, "rice", "50$", "no"),
                new Data(R.drawable.image8, "fyuegif", "100$", "50%")
        };

        binding.fragmentECommerceRecycleviewSeeds.setLayoutManager((new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)));
        ViewRecycleProductsAdapter recycleViewAdapters = new ViewRecycleProductsAdapter(getContext(), data_seeds);
        binding.fragmentECommerceRecycleviewSeeds.setAdapter(recycleViewAdapters);

        Data[] data_fertilize = {
                new Data(R.drawable.image1, "rice", "50$", "no"),
                new Data(R.drawable.image2, "fyuegif", "100$", "50%"),
                new Data(R.drawable.image3, "rice", "50$", "no"),
                new Data(R.drawable.image4, "fyuegif", "100$", "50%"),
                new Data(R.drawable.image5, "rice", "50$", "no"),
                new Data(R.drawable.image6, "fyuegif", "100$", "50%"),
                new Data(R.drawable.image7, "rice", "50$", "no"),
                new Data(R.drawable.image8, "fyuegif", "100$", "50%")
        };

        binding.fragmentECommerceRecycleviewFertilizers.setLayoutManager((new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)));
        ViewRecycleProductsAdapter recycleViewAdapterf = new ViewRecycleProductsAdapter(getContext(), data_fertilize);
        binding.fragmentECommerceRecycleviewFertilizers.setAdapter(recycleViewAdapterf);

        Data[] data_tools = {
                new Data(R.drawable.image1, "rice", "50$", "no"),
                new Data(R.drawable.image2, "fyuegif", "100$", "50%"),
                new Data(R.drawable.image3, "rice", "50$", "no"),
                new Data(R.drawable.image4, "fyuegif", "100$", "50%"),
                new Data(R.drawable.image5, "rice", "50$", "no"),
                new Data(R.drawable.image6, "fyuegif", "100$", "50%"),
                new Data(R.drawable.image7, "rice", "50$", "no"),
                new Data(R.drawable.image8, "fyuegif", "100$", "50%")
        };

        binding.fragmentECommerceRecycleviewTools.setLayoutManager((new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)));
        ViewRecycleProductsAdapter recycleViewAdaptert = new ViewRecycleProductsAdapter(getContext(), data_tools);
        binding.fragmentECommerceRecycleviewTools.setAdapter(recycleViewAdaptert);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.shop_main_menu, menu);
        SearchManager searchManager =
                (SearchManager) requireActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(requireActivity().getComponentName()));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.cartFragment){
//            ECommerceFragmentDirections.ActionToCart action =
//                    ECommerceFragmentDirections.actionToCart();
//            action.setFromWhichFragment(Constants.FROM_E_COMMERCE_FRAGMENT);
            Navigation.findNavController(requireView()).navigate(R.id.cartFragment);
        }
        else if (item.getItemId() == R.id.wishlistFragment){
//            ECommerceFragmentDirections.ActionToWishlist action =
//                    ECommerceFragmentDirections.actionToWishlist();
//            action.setFromWhichFragment(Constants.FROM_E_COMMERCE_FRAGMENT);
            Navigation.findNavController(requireView()).navigate(R.id.wishlistFragment );
        }
        return super.onOptionsItemSelected(item);
    }

}