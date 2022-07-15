package com.graduation.farmerfriend.repos;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.graduation.farmerfriend.apis.ECommerceInterface;
import com.graduation.farmerfriend.caching_room.Product.ProductDatabase;
import com.graduation.farmerfriend.control.iot_fragments.hasIoTSystem.Data_HasIoT;
import com.graduation.farmerfriend.ecommerce_models.CartRoot;
import com.graduation.farmerfriend.ecommerce_models.IOTStatus;
import com.graduation.farmerfriend.ecommerce_models.PatchCart;
import com.graduation.farmerfriend.ecommerce_models.PostCart;
import com.graduation.farmerfriend.ecommerce_models.Product;

import java.util.ArrayList;
import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;

public class EcommerceRepo {
    private static EcommerceRepo Instance;
    private static final String ECOMMERCE_SERVICE_BASE_URL = "http://newweb19992022-001-site1.ftempurl.com/";
    private  ECommerceInterface eCommerceInterface;
    private CompositeDisposable compositeDisposable;
    private static final String TAG = "EcommerceRepo";
    private final MutableLiveData<ArrayList<Product>> allProductsLiveData;
    private final MutableLiveData<ArrayList<Product>> seedProductsLiveData;
    private final MutableLiveData<ArrayList<Product>> toolProductsLiveData;
    private final MutableLiveData<ArrayList<Product>> ferProductsLiveData;
    private final MutableLiveData<Product> singleProductLiveData;

    private Boolean internet ;

    private final MutableLiveData<ArrayList<CartRoot>> cartLiveData;
    final MutableLiveData<IOTStatus> iotStatusMutableLiveData;
    private Context context;
    ProductDatabase productDatabase;
    ArrayList<Product> productsarray,seedsarray,fertsarray,toolsarray;
    int i,j;


    public static EcommerceRepo getInstance() {
        if (Instance == null) {
            Instance = new EcommerceRepo();
        }
        return Instance;
    }


    public void init(Context context){
        this.context = context ;
        productDatabase = ProductDatabase.getInstance(context) ;

         productsarray = new ArrayList<>();
         seedsarray = new ArrayList<>();
         fertsarray = new ArrayList<>();
         toolsarray = new ArrayList<>();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        eCommerceInterface = new retrofit2.Retrofit.Builder()
                .baseUrl(ECOMMERCE_SERVICE_BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ECommerceInterface.class);

        productDatabase.productDao().getProducts()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Product>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }
                    @Override
                    public void onSuccess(@NonNull List<Product> products) {
                        for(i= 0 ; i<products.size() ;i++ ) {
                            productsarray.add(i, products.get(i));
                        }
                        for (j = 0 ; j<productsarray.size() ; j++){
                            if (productsarray.get(j).categoryId == 1) {
                                seedsarray.add(productsarray.get(j));
                            }else if (productsarray.get(j).categoryId == 2) {
                                toolsarray.add(productsarray.get(j));
                            }else if (productsarray.get(j).categoryId == 3){
                                fertsarray.add(productsarray.get(j));
                            }
                        }
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                    }
                });

        }

    EcommerceRepo() {


        allProductsLiveData = new MutableLiveData<>();
        seedProductsLiveData = new MutableLiveData<>();
        toolProductsLiveData = new MutableLiveData<>();
        ferProductsLiveData = new MutableLiveData<>();
        singleProductLiveData = new MutableLiveData<>();
        iotStatusMutableLiveData = new MutableLiveData<>();

        cartLiveData = new MutableLiveData<>();
    }

    public void getEcommerceAllProducts() {

        if (getConnectivityStatus(context)) {
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
                    productDatabase.productDao().insertProduct(product)
                            .subscribeOn(Schedulers.io())
                            .subscribe(new CompletableObserver() {
                                @Override
                                public void onSubscribe(@NonNull Disposable d) {

                                }

                                @Override
                                public void onComplete() {
                                    Log.d(TAG, "yes");
                                }

                                @Override
                                public void onError(@NonNull Throwable e) {

                                }
                            });
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    Log.d(TAG, "onError: " + e.getMessage());
                }
            };
            allProductsObservable.subscribe(allProductsObserver);


        }else{

            Log.d(TAG, "yes");

            productDatabase.productDao().getProducts()
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(products -> { allProductsLiveData.postValue((ArrayList<Product>) products) ;}, throwable -> {});


        }
    }

    public void getEcommerceSeedProducts() {

        if (getConnectivityStatus(context)) {
            @NonNull Single<ArrayList<Product>> seedProductsObservable = eCommerceInterface.getSeedProducts()
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
                    Log.d(TAG, String.valueOf(product.get(0).productName));

                }

                @Override
                public void onError(@NonNull Throwable e) {
                    Log.d(TAG, "onError:" + e.getMessage());
                }
            };
            seedProductsObservable.subscribe(seedProductsObserver);
        }

        else{
            seedProductsLiveData.postValue(seedsarray);
        }
    }

    public void getEcommerceFerProducts() {
        if (getConnectivityStatus(context)) {
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

                }

                @Override
                public void onError(@NonNull Throwable e) {
                    Log.d(TAG, "onError: " + e.getMessage());
                }
            };
            ferProductsObservable.subscribe(ferProductsObserver);
        }
        else {
            ferProductsLiveData.postValue(fertsarray);
        }
    }

    public void getEcommerceToolProducts() {

        if (getConnectivityStatus(context)) {
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

                }

                @Override
                public void onError(@NonNull Throwable e) {
                    Log.d(TAG, "onError: " + e.getMessage());
                }
            };
            toolProductsObservable.subscribe(toolProductsObserver);
        }
        else{
            toolProductsLiveData.postValue(toolsarray);
        }

    }

    public void getProductByID(int id) {
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
                Log.d(TAG, "onError: " + e.getMessage());
            }
        };
        productSingle.subscribe(productSingleObserver);

    }

    public void getCartItems(String userID) {
        Single<ArrayList<CartRoot>> cartSingle = eCommerceInterface.getCartProducts(userID).subscribeOn(Schedulers.io());

        Log.d(TAG, "onSuccess: ");
        SingleObserver<ArrayList<CartRoot>> cartSingleObserver = new SingleObserver<ArrayList<CartRoot>>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull ArrayList<CartRoot> carts) {
                cartLiveData.postValue(carts);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError: " + e.getMessage());
            }
        };
        cartSingle.subscribe(cartSingleObserver);

    }

    public void patchQuantity(int cartID, ArrayList<PatchCart> patchCarts) {
        Single<Object> objectSingle = eCommerceInterface.patchCartProductQuantity(cartID, patchCarts).subscribeOn(Schedulers.io());
        SingleObserver<Object> observer = new SingleObserver<Object>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull Object o) {
                Log.d(TAG, "onSuccess: patching");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError: " + e.toString());
            }
        };
        objectSingle.subscribe(observer);
    }

    public void deleteProduct(int cartItemsId) {
        Single<Object> single = eCommerceInterface.deleteProductFromCart(cartItemsId).subscribeOn(Schedulers.io());
        SingleObserver<Object> observer = new SingleObserver<Object>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull Object o) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        };
        single.subscribe(observer);
    }

    public void addToCart(PostCart postCart) {
        Single<PostCart> cartSingle = eCommerceInterface.postProductToCart(postCart).subscribeOn(Schedulers.io());
        SingleObserver<PostCart> cartSingleObserver = new SingleObserver<PostCart>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull PostCart postCart) {

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError: "+e);
            }
        };
        cartSingle.subscribe(cartSingleObserver);
    }

    @NonNull
    public Single<IOTStatus> checkIotStatus(String userName) {
        Single<IOTStatus> iotStatusSingle = eCommerceInterface.getIotStatus(userName).subscribeOn(Schedulers.io());
        SingleObserver<IOTStatus> iotStatusSingleObserver = new SingleObserver<IOTStatus>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull IOTStatus iotStatus) {
                iotStatusMutableLiveData.postValue(iotStatus);
                Log.d(TAG, "onSuccess: iotstatus "+iotStatus.hasIotSystem);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                iotStatusMutableLiveData.postValue(null);
                Log.d(TAG,"Iot"+e.toString());
            }
        };
        iotStatusSingle.subscribe(iotStatusSingleObserver);
        return iotStatusSingle;
    }

//    public Single<Object> editIotStatus(String userName, ArrayList<Data_HasIoT> data_hasIoT) {
//        Single<Object> objectSingle = eCommerceInterface.changeIotStatus(userName, data_hasIoT).subscribeOn(Schedulers.io());
//        return objectSingle;
//    }

    public LiveData<ArrayList<Product>> getAllLiveDataProducts() {
        return allProductsLiveData;
    }

    public LiveData<ArrayList<Product>> getSeedLiveDataProducts() {
        return seedProductsLiveData;
    }

    public LiveData<ArrayList<Product>> getToolLiveDataProducts() {
        return toolProductsLiveData;
    }

    public LiveData<ArrayList<Product>> getFerLiveDataProducts() {
        return ferProductsLiveData;
    }

    public LiveData<Product> getProductLiveData() {
        return singleProductLiveData;
    }

    public LiveData<ArrayList<CartRoot>> getCartLiveData() {
        return cartLiveData;
    }

    public Boolean getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                internet = true ;
                return internet;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                internet = true ;
                return internet;
            }
        } else {
            internet = false ;
            return internet;
        }
        return internet;
    }
    public LiveData<IOTStatus> getIotStatusLiveData() {
        return iotStatusMutableLiveData;
    }

}
