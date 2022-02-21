package com.graduation.farmerfriend.e_commerce.ui.deals;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;


import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.databinding.FragmentHotDealsBinding;


public class HotDealsFragment extends Fragment {
    FragmentHotDealsBinding fragmentHotDealsBinding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        fragmentHotDealsBinding = FragmentHotDealsBinding.inflate(inflater, container, false);
        View view = fragmentHotDealsBinding.getRoot();
        HotDealsAdapter adapter = new HotDealsAdapter();
        fragmentHotDealsBinding.fragmentHotDealsRecyclerView.setAdapter(adapter);
        fragmentHotDealsBinding.fragmentHotDealsRecyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 3));
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

}