package com.graduation.farmerfriend.e_commerce.search.data;

import com.graduation.farmerfriend.e_commerce.search.pojo.SearchResultPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchApiInterface {

    @GET("api/Products/SearchActivity")
    public Call<List<SearchResultPojo>> getSearchResult(@Query("name") String name);
}
