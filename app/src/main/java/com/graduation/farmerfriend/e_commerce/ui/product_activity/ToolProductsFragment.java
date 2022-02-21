package com.graduation.farmerfriend.e_commerce.ui.product_activity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.constants.Constants;
import com.graduation.farmerfriend.databinding.FragmentToolProductsBinding;
import com.graduation.farmerfriend.e_commerce.ui.ECommerceFragmentDirections;

public class ToolProductsFragment extends Fragment {

    FragmentToolProductsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentToolProductsBinding.inflate(inflater, container, false);
        setHasOptionsMenu(true);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ProductItemAdapter adapter = new ProductItemAdapter();
        binding.fragmentToolProductsRecyclerView.setAdapter(adapter);
        binding.fragmentToolProductsRecyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 3));
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
            ToolProductsFragmentDirections.ActionToCart action =
                    ToolProductsFragmentDirections.actionToCart();
            action.setFromWhichFragment(Constants.FROM_TOOLS_FRAGMENT);
            Navigation.findNavController(requireView()).navigate(R.id.cartFragment);
        } else if (item.getItemId() == R.id.wishlistFragment) {
            ToolProductsFragmentDirections.ActionToWishlist action =
                    ToolProductsFragmentDirections.actionToWishlist();
            action.setFromWhichFragment(Constants.FROM_TOOLS_FRAGMENT);
            Navigation.findNavController(requireView()).navigate(R.id.wishlistFragment);
        }
        return super.onOptionsItemSelected(item);
    }
}