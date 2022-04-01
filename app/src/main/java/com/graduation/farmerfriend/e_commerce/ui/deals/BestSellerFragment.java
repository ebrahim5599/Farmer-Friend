package com.graduation.farmerfriend.e_commerce.ui.deals;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
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
import com.graduation.farmerfriend.databinding.FragmentBestSelersBinding;
import com.graduation.farmerfriend.ecommerce_models.Product;

import java.util.ArrayList;

public class BestSellerFragment extends Fragment {
    FragmentBestSelersBinding fragmentBestSelersBinding;
    BestSellerViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        fragmentBestSelersBinding = FragmentBestSelersBinding.inflate(inflater, container, false);
        View view = fragmentBestSelersBinding.getRoot();
        viewModel = new ViewModelProvider(this).get(BestSellerViewModel.class);
        viewModel.init();
        viewModel.allProductLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<Product>>() {
            @Override
            public void onChanged(ArrayList<Product> productArrayList) {
                BestSellerAdapter adapter = new BestSellerAdapter(productArrayList,requireContext());
                fragmentBestSelersBinding.fragmentBestSellersRecyclerView.setAdapter(adapter);
                fragmentBestSelersBinding.fragmentBestSellersRecyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 3));
            }
        });

        Log.i("Fragment", "Best sellers oncreateView");
        return view;
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
        if (item.getItemId() == R.id.cartFragment) {
            Navigation.findNavController(requireView()).navigate(R.id.cartFragment);
        } else if (item.getItemId() == R.id.wishlistFragment) {
            Navigation.findNavController(requireView()).navigate(R.id.wishlistFragment);
        }
        return super.onOptionsItemSelected(item);
    }

}