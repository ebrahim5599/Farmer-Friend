package com.graduation.farmerfriend.e_commerce.ui;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.databinding.FragmentECommerceBinding;
import com.graduation.farmerfriend.e_commerce.Data;
import com.graduation.farmerfriend.e_commerce.ViewRecycleProductsAdapter;
import com.graduation.farmerfriend.e_commerce.search.pojo.SearchResultPojo;
import com.graduation.farmerfriend.e_commerce.search.ui.SearchAdapter;
import com.graduation.farmerfriend.e_commerce.search.ui.SearchViewModel;
import com.graduation.farmerfriend.e_commerce.search.ui.ViewRecycleSearchAdapter;
import com.graduation.farmerfriend.ecommerce_models.Product;

import java.util.ArrayList;
import java.util.List;


public class ECommerceFragment extends Fragment {
    FragmentECommerceBinding binding;
    private SearchViewModel searchViewModel;
    private EcommerceFragmentViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentECommerceBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        setHasOptionsMenu(true);

        viewModel = new ViewModelProvider(requireActivity()).get(EcommerceFragmentViewModel.class);
        viewModel.init();


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

        viewModel.getAllProductsLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<Product>>() {
            @Override
            public void onChanged(ArrayList<Product> productArrayList) {

                binding.fragmentECommerceRecycleViewBestSeller.setLayoutManager((new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)));
                ViewRecycleProductsAdapter recycleViewAdapterBest = new ViewRecycleProductsAdapter(getContext(), productArrayList);
                binding.fragmentECommerceRecycleViewBestSeller.setAdapter(recycleViewAdapterBest);
            }
        });

        viewModel.getAllProductsLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<Product>>() {
                    @Override
                    public void onChanged(ArrayList<Product> productArrayList) {
                        binding.fragmentECommerceRecycleViewHotDeals.setLayoutManager((new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)));
                        ViewRecycleProductsAdapter recycleViewAdapterHot = new ViewRecycleProductsAdapter(getContext(), productArrayList);
                        binding.fragmentECommerceRecycleViewHotDeals.setAdapter(recycleViewAdapterHot);
                    }
                }
        );

        viewModel.getSeedProductsLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<Product>>() {
            @Override
            public void onChanged(ArrayList<Product> productArrayList) {
                binding.fragmentECommerceRecycleviewSeeds.setLayoutManager((new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)));
                ViewRecycleProductsAdapter recycleViewAdapters = new ViewRecycleProductsAdapter(getContext(), productArrayList);
                binding.fragmentECommerceRecycleviewSeeds.setAdapter(recycleViewAdapters);
            }
        });

        viewModel.getFerProductsLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<Product>>() {
            @Override
            public void onChanged(ArrayList<Product> productArrayList) {
                binding.fragmentECommerceRecycleviewFertilizers.setLayoutManager((new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)));
                ViewRecycleProductsAdapter recycleViewAdapterFer = new ViewRecycleProductsAdapter(getContext(), productArrayList);
                binding.fragmentECommerceRecycleviewFertilizers.setAdapter(recycleViewAdapterFer);
            }
        });

        viewModel.getToolProductsLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<Product>>() {
            @Override
            public void onChanged(ArrayList<Product> productArrayList) {
                binding.fragmentECommerceRecycleviewTools.setLayoutManager((new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)));
                ViewRecycleProductsAdapter recycleViewAdaptert = new ViewRecycleProductsAdapter(getContext(), productArrayList);
                binding.fragmentECommerceRecycleviewTools.setAdapter(recycleViewAdaptert);
            }
        });

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
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.cartFragment) {
//            ECommerceFragmentDirections.ActionToCart action =
//                    ECommerceFragmentDirections.actionToCart();
//            action.setFromWhichFragment(Constants.FROM_E_COMMERCE_FRAGMENT);
            Navigation.findNavController(requireView()).navigate(R.id.cartFragment);
        } else if (item.getItemId() == R.id.wishlistFragment) {
//            ECommerceFragmentDirections.ActionToWishlist action =
//                    ECommerceFragmentDirections.actionToWishlist();
//            action.setFromWhichFragment(Constants.FROM_E_COMMERCE_FRAGMENT);
            Navigation.findNavController(requireView()).navigate(R.id.wishlistFragment);
        } else if (item.getItemId() == R.id.search)
            Navigation.findNavController(requireView()).navigate(R.id.searchFragment);

        return super.onOptionsItemSelected(item);
    }

}