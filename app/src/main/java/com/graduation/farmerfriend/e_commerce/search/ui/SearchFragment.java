package com.graduation.farmerfriend.e_commerce.search.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
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
import com.graduation.farmerfriend.databinding.FragmentSearchBinding;
import com.graduation.farmerfriend.e_commerce.search.pojo.SearchResultPojo;

import java.util.List;

public class SearchFragment extends Fragment {

    private SearchViewModel searchViewModel;
    private SearchAdapter searchAdapter;
    FragmentSearchBinding binding;
    private String emptyText = "empty_text|empty text";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentSearchBinding.inflate(inflater, container, false);
        setHasOptionsMenu(true);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.searchResultRecyclerView.setLayoutManager(linearLayoutManager);
        searchAdapter = new SearchAdapter(getContext());
        binding.searchResultRecyclerView.setAdapter(searchAdapter);

        searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        searchViewModel.searchResultMutableLiveData.observe(getViewLifecycleOwner(), new Observer<List<SearchResultPojo>>() {
            @Override
            public void onChanged(List<SearchResultPojo> searchResultPojo) {
                // observe any change in response.
                searchAdapter.setList(searchResultPojo);
                binding.loadingSearch.setVisibility(View.GONE);

            }
        });
        searchViewModel.errorMessage.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                binding.loadingSearch.setVisibility(View.GONE);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_menu, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.search_view).getActionView();
        searchView.setQueryHint(getString(R.string.type_here_to_search));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
//                Toast.makeText(getContext(), "onQueryTextSubmit "+s, Toast.LENGTH_SHORT).show();
                searchViewModel.getSearchResult(s);
                binding.loadingSearch.setVisibility(View.VISIBLE);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
//                Toast.makeText(getContext(),"onQueryTextChange "+ s, Toast.LENGTH_SHORT).show();
                emptyText = s;
                if (!s.isEmpty()) {
                    searchViewModel.getSearchResult(s);
                    binding.loadingSearch.setVisibility(View.VISIBLE);
                } else {
                    searchAdapter.setListEmpty();
                    binding.loadingSearch.setVisibility(View.GONE);
                    searchViewModel.cancelRequest();
                }

//                if (emptyText.isEmpty()) {
//                    searchViewModel.cancelRequest();
//                }
                return false;
            }
        });
    }
}