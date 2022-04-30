package com.graduation.farmerfriend.repos;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.graduation.farmerfriend.apis.ECommerceInterface;
import com.graduation.farmerfriend.ecommerce_models.Product;

import java.util.ArrayList;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;

public class EcommerceRepo {
    private static EcommerceRepo Instance;
    private static final String ECOMMERCE_SERVICE_BASE_URL = "http://teamweb992022-001-site1.htempurl.com/";
    private final ECommerceInterface eCommerceInterface;
    private CompositeDisposable compositeDisposable;
    private static final String TAG = "EcommerceRepo";
    private final MutableLiveData<ArrayList<Product>> allProductsLiveData ;
    private final MutableLiveData<ArrayList<Product>> seedProductsLiveData ;
    private final MutableLiveData<ArrayList<Product>> toolProductsLiveData ;
    private final MutableLiveData<ArrayList<Product>> ferProductsLiveData ;
    private final MutableLiveData<Product> singleProductLiveData ;

    public static EcommerceRepo getInstance() {
        if (Instance == null) {
            Instance = new EcommerceRepo();
        }
        return Instance;
    }

    EcommerceRepo(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        eCommerceInterface = new retrofit2.Retrofit.Builder()
                .baseUrl(ECOMMERCE_SERVICE_BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ECommerceInterface.class);

        allProductsLiveData = new MutableLiveData<>();
        seedProductsLiveData = new MutableLiveData<>();
        toolProductsLiveData = new MutableLiveData<>();
        ferProductsLiveData = new MutableLiveData<>();
        singleProductLiveData = new MutableLiveData<>();
    }

    public void getEcommerceAllProducts(){
        Single<ArrayList<Product>> allProductsObservable = eCommerceInterface.getAllProducts()
                .subscribeOn(Schedulers.io());
        compositeDisposable = new CompositeDisposable();


        SingleObserver<ArrayList<Product>> allProductsObserver = new SingleObserver<ArrayList<Product>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onSuccess(@NonNull ArrayList<Product> product) {
                allProductsLiveData.postValue(product);
                Log.d(TAG,String.valueOf(product.get(2).quantity));
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError: " + e.getMessage());
            }
        };
        allProductsObservable.subscribe(allProductsObserver);
    }

    public void getEcommerceSeedProducts(){
        Single<ArrayList<Product>> seedProductsObservable = eCommerceInterface.getSeedProducts()
                .subscribeOn(Schedulers.io());
        compositeDisposable = new CompositeDisposable();


        SingleObserver<ArrayList<Product>> seedProductsObserver = new SingleObserver<ArrayList<Product>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onSuccess(@NonNull ArrayList<Product> product) {
                seedProductsLiveData.postValue(product);
                Log.d(TAG,String.valueOf(product.get(0).productName));
            }
            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError:" + e.getMessage());
            }
        };
        seedProductsObservable.subscribe(seedProductsObserver);
    }

    public void getEcommerceFerProducts(){
        Single<ArrayList<Product>> ferProductsObservable = eCommerceInterface.getFerProducts()
                .subscribeOn(Schedulers.io());
        compositeDisposable = new CompositeDisposable();


        SingleObserver<ArrayList<Product>> ferProductsObserver = new SingleObserver<ArrayList<Product>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                compositeDisposable.add(d);
            }
            @Override
            public void onSuccess(@NonNull ArrayList<Product> product) {
                ferProductsLiveData.postValue(product);
                Log.d(TAG,String.valueOf(product.get(0).productName));
            }
            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError: " + e.getMessage());
            }
        };
        ferProductsObservable.subscribe(ferProductsObserver);
    }

    public void getEcommerceToolProducts(){
        Single<ArrayList<Product>> toolProductsObservable = eCommerceInterface.getToolProducts()
                .subscribeOn(Schedulers.io());
        compositeDisposable = new CompositeDisposable();


        SingleObserver<ArrayList<Product>> toolProductsObserver = new SingleObserver<ArrayList<Product>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                compositeDisposable.add(d);
            }
            @Override
            public void onSuccess(@NonNull ArrayList<Product> product) {
                toolProductsLiveData.postValue(product);
                Log.d(TAG,String.valueOf(product.get(0).productName));
            }
            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError: " + e.getMessage());
            }
        };
        toolProductsObservable.subscribe(toolProductsObserver);
    }
    public void getProductByID(int id){
        Single<Product> productSingle = eCommerceInterface.getProduct(id).subscribeOn(Schedulers.io());
        SingleObserver<Product> productSingleObserver = new SingleObserver<Product>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull Product product) {
                    singleProductLiveData.postValue(product);
//                Log.d(TAG, "onSuccess: "+product.productName);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError: "+e.getMessage());
            }
        };
        productSingle.subscribe(productSingleObserver);

    }

    public LiveData<ArrayList<Product>> getAllLiveDataProducts(){
        return allProductsLiveData;
    }

    public LiveData<ArrayList<Product>> getSeedLiveDataProducts(){
        return seedProductsLiveData;
    }
    public LiveData<ArrayList<Product>> getToolLiveDataProducts(){
        return toolProductsLiveData;
    }
    public LiveData<ArrayList<Product>> getFerLiveDataProducts(){
        return ferProductsLiveData;
    }
    public LiveData<Product> getProductLiveData(){
        return singleProductLiveData;
    }
}
