package com.graduation.farmerfriend.e_commerce.search.ui;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.graduation.farmerfriend.e_commerce.search.data.SearchClient;
import com.graduation.farmerfriend.e_commerce.search.pojo.SearchResultPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchViewModel extends ViewModel {

    private static final String TAG = "SearchViewModel";
    private Call<List<SearchResultPojo>> call;
    public MutableLiveData<String> errorMessage = new MutableLiveData<>();
    public MutableLiveData<List<SearchResultPojo>> searchResultMutableLiveData =
            new MutableLiveData<>();

    public void getSearchResult(String name){
        call = SearchClient.getINSTANCE().getSearchResult(name);
        call.enqueue(new Callback<List<SearchResultPojo>>() {
            @Override
            public void onResponse(Call<List<SearchResultPojo>> call, Response<List<SearchResultPojo>> response) {
                if (response.body() != null) {
                    searchResultMutableLiveData.setValue(response.body());
                    Log.d(TAG, "onResponse: "+ response.body().toString());

                }else{
                    Log.d(TAG, "err: "+ response.errorBody());
                    errorMessage.setValue("Not Found");
                }
            }

            @Override
            public void onFailure(Call<List<SearchResultPojo>> call, Throwable t) {
                errorMessage.setValue(t.getMessage());
                Log.d(TAG, "onFailure: "+ t);
            }
        });
    }

    public void cancelRequest(){
        call.cancel();
    }
}
