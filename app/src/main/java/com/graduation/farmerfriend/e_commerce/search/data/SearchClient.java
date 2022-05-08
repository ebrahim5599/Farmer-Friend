package com.graduation.farmerfriend.e_commerce.search.data;

import com.graduation.farmerfriend.e_commerce.search.pojo.SearchResultPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchClient {
    private static final String BASE_URL = "http://teamweb992022-001-site1.htempurl.com/";
    private static SearchClient INSTANCE;
    private SearchApiInterface searchApiInterface;

    public SearchClient() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        searchApiInterface = retrofit.create(SearchApiInterface.class);
    }

    public static SearchClient getINSTANCE(){
        if ( INSTANCE == null)
            INSTANCE = new SearchClient();
        return INSTANCE;
    }

    public Call<List<SearchResultPojo>> getSearchResult(String itemName){
        return searchApiInterface.getSearchResult(itemName);
    }
}
