package com.graduation.farmerfriend.e_commerce.search.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.graduation.farmerfriend.e_commerce.search.data.SearchClient;
import com.graduation.farmerfriend.e_commerce.search.pojo.SearchResultPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchViewModel extends ViewModel {

    public MutableLiveData<String> errorMessage = new MutableLiveData<>();
    public MutableLiveData<List<SearchResultPojo>> searchResultMutableLiveData =
            new MutableLiveData<>();

    public void getSearchResult(String name){
        SearchClient.getINSTANCE().getSearchResult(name).enqueue(new Callback<List<SearchResultPojo>>() {
            @Override
            public void onResponse(Call<List<SearchResultPojo>> call, Response<List<SearchResultPojo>> response) {
                searchResultMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<SearchResultPojo>> call, Throwable t) {
                errorMessage.postValue(t.getMessage());
            }
        });
    }
}
