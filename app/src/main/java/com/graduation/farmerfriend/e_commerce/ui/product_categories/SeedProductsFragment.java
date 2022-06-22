package com.graduation.farmerfriend.e_commerce.ui.product_categories;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.databinding.FragmentSeedProductsBinding;
import com.graduation.farmerfriend.ecommerce_models.Product;

import java.util.ArrayList;

public class SeedProductsFragment extends Fragment {

    FragmentSeedProductsBinding binding;
    SeedViewModel seedViewModel;
    private ArrayList<Product> seedArrayList;
    private String TAG = "SeedProductsFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        seedViewModel = new ViewModelProvider(this).get(SeedViewModel.class);
        binding = FragmentSeedProductsBinding.inflate(inflater, container, false);
        setHasOptionsMenu(true);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        seedViewModel.init();
        seedViewModel.getSeedProductsLiveData()
                .observe(getViewLifecycleOwner(), new Observer<ArrayList<Product>>() {
                    @Override
                    public void onChanged(ArrayList<Product> productArrayList) {
                        seedArrayList = productArrayList;
                        Log.d(TAG, "onChanged: " + productArrayList.get(0).productName);
                        ProductItemAdapter adapter = new ProductItemAdapter(seedArrayList, requireContext(), "seed");
                        binding.fragmentSeedsProductsRecyclerView.setAdapter(adapter);
                        binding.fragmentSeedsProductsRecyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 3));
                    }
                });
        Log.i("Fragment", "seeds fragment oncreateview");
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        // This callback will only be called when MyFragment is at least Started.
//        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
//            @Override
//            public void handleOnBackPressed() {
//                Navigation.findNavController(requireView()).navigate(R.id.ECommerceFragment);
//            }
//        };
//        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
//        // The callback can be enabled or disabled here or in handleOnBackPressed()
//    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.shop_main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.cartFragment) {
            Navigation.findNavController(requireView()).navigate(R.id.cartFragment);
        } else if (item.getItemId() == R.id.wishlistFragment) {
            Navigation.findNavController(requireView()).navigate(R.id.wishlistFragment);
        } else if (item.getItemId() == R.id.search)
            Navigation.findNavController(requireView()).navigate(R.id.searchFragment);
        return super.onOptionsItemSelected(item);
    }
}