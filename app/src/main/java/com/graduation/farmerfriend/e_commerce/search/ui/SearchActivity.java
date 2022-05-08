package com.graduation.farmerfriend.e_commerce.search.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.databinding.ActivitySearchBinding;
import com.graduation.farmerfriend.e_commerce.search.pojo.SearchResultPojo;
import com.graduation.farmerfriend.e_commerce.search.pojo.Search_Item;
import com.graduation.farmerfriend.e_commerce.search.ui.SearchViewModel;
import com.graduation.farmerfriend.e_commerce.search.ui.ViewRecycleSearchAdapter;

import java.util.List;

public class SearchActivity extends AppCompatActivity {

    ActivitySearchBinding binding ;
    private SearchViewModel searchViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.activitySearchTextViewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
//                startActivity(intent);
            }
        });

        binding.activitySearchTextViewWishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), WishlistActivity.class);
//                startActivity(intent);
            }
        });

        Search_Item[] search ={
                new Search_Item(R.drawable.corn,"Corn","100$","corn is a heat_loving with varieties for eating"),
                new Search_Item(R.drawable.corn,"Corn","100$","corn is a heat_loving with varieties for eating"),
                new Search_Item(R.drawable.corn,"Corn","100$","corn is a heat_loving with varieties for eating"),
                new Search_Item(R.drawable.corn,"Corn","100$","corn is a heat_loving with varieties for eating"),
                new Search_Item(R.drawable.corn,"Corn","100$","corn is a heat_loving with varieties for eating"),
                new Search_Item(R.drawable.corn,"Corn","100$","corn is a heat_loving with varieties for eating"),
                new Search_Item(R.drawable.corn,"Corn","100$","corn is a heat_loving with varieties for eating"),
                new Search_Item(R.drawable.corn,"Corn","100$","corn is a heat_loving with varieties for eating"),
                new Search_Item(R.drawable.corn,"Corn","100$","corn is a heat_loving with varieties for eating"),
                new Search_Item(R.drawable.corn,"Corn","100$","corn is a heat_loving with varieties for eating"),
                new Search_Item(R.drawable.corn,"Corn","100$","corn is a heat_loving with varieties for eating"),
                new Search_Item(R.drawable.corn,"Corn","100$","corn is a heat_loving with varieties for eating"),
                new Search_Item(R.drawable.corn,"Corn","100$","corn is a heat_loving with varieties for eating"),
                new Search_Item(R.drawable.corn,"Corn","100$","corn is a heat_loving with varieties for eating"),
                new Search_Item(R.drawable.corn,"Corn","100$","corn is a heat_loving with varieties for eating")
        };


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        binding.activitySearchRecycleviewSearchItem.setLayoutManager(linearLayoutManager);
        ViewRecycleSearchAdapter recycleViewAdapter = new ViewRecycleSearchAdapter(getApplicationContext());
        binding.activitySearchRecycleviewSearchItem.setAdapter(recycleViewAdapter);

        searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        searchViewModel.searchResultMutableLiveData.observe(this, new Observer<List<SearchResultPojo>>() {
            @Override
            public void onChanged(List<SearchResultPojo> searchResultPojo) {
                // observe any change in response.
                recycleViewAdapter.setList(searchResultPojo);
            }
        });
        searchViewModel.errorMessage.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getBaseContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

