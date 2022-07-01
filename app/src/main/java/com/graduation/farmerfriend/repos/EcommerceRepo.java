package com.graduation.farmerfriend.repos;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.database.Observable;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.graduation.farmerfriend.apis.ECommerceInterface;
import com.graduation.farmerfriend.caching_room.Fert.Fert;
import com.graduation.farmerfriend.caching_room.Fert.FertDatabase;
import com.graduation.farmerfriend.caching_room.Product.ProductDatabase;
import com.graduation.farmerfriend.caching_room.Seed.Seed;
import com.graduation.farmerfriend.caching_room.Seed.SeedDatabase;
import com.graduation.farmerfriend.caching_room.Tool.Tool;
import com.graduation.farmerfriend.caching_room.Tool.ToolDatabase;
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


import okhttp3.Call;
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
    private final MutableLiveData<ArrayList<Seed>> seedProductsLiveData;
    private final MutableLiveData<ArrayList<Tool>> toolProductsLiveData;
    private final MutableLiveData<ArrayList<Fert>> ferProductsLiveData;
    private final MutableLiveData<Product> singleProductLiveData;

    private Boolean internet ;

    private final MutableLiveData<ArrayList<CartRoot>> cartLiveData;
    final MutableLiveData<IOTStatus> iotStatusMutableLiveData;
    private Context context;
    ProductDatabase productDatabase;
    SeedDatabase seedDatabase;
    FertDatabase ferDatabase ;
    ToolDatabase toolDatabase ;


    public static EcommerceRepo getInstance() {
        if (Instance == null) {
            Instance = new EcommerceRepo();
        }
        return Instance;
    }


    public void init(Context context){
        this.context = context ;
         productDatabase = ProductDatabase.getInstance(context) ;
         seedDatabase= SeedDatabase.getInstance(context) ;
         ferDatabase=  FertDatabase.getInstance(context) ;
         toolDatabase= ToolDatabase.getInstance(context) ;

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
            @NonNull Single<ArrayList<Seed>> seedProductsObservable = eCommerceInterface.getSeedProducts()
                    .subscribeOn(Schedulers.io());
            compositeDisposable = new CompositeDisposable();

            SingleObserver<ArrayList<Seed>> seedProductsObserver = new SingleObserver<ArrayList<Seed>>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    compositeDisposable.add(d);
                }

                @Override
                public void onSuccess(@NonNull ArrayList<Seed> product) {
                    seedProductsLiveData.postValue(product);
                    Log.d(TAG, String.valueOf(product.get(0).productName));

                    seedDatabase.seedDao().insertSeed(product).subscribeOn(Schedulers.computation())
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
                    Log.d(TAG, "onError:" + e.getMessage());
                }
            };
            seedProductsObservable.subscribe(seedProductsObserver);
        }

        else{
            seedDatabase.seedDao().getSeeds()
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(products -> { seedProductsLiveData.postValue((ArrayList<Seed>) products) ;}, throwable -> {});
        }
    }

    public void getEcommerceFerProducts() {
        if (getConnectivityStatus(context)) {
            Single<ArrayList<Fert>> ferProductsObservable = eCommerceInterface.getFerProducts()
                    .subscribeOn(Schedulers.io());
            compositeDisposable = new CompositeDisposable();


            SingleObserver<ArrayList<Fert>> ferProductsObserver = new SingleObserver<ArrayList<Fert>>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    compositeDisposable.add(d);
                }

                @Override
                public void onSuccess(@NonNull ArrayList<Fert> product) {
                    ferProductsLiveData.postValue(product);

                    ferDatabase.fertDao().insertFert(product).subscribeOn(Schedulers.computation())
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
            ferProductsObservable.subscribe(ferProductsObserver);
        }
        else {
            ferDatabase.fertDao().getFerts()
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(products -> { ferProductsLiveData.postValue((ArrayList<Fert>) products) ;}, throwable -> {});
        }
    }

    public void getEcommerceToolProducts() {

        if (getConnectivityStatus(context)) {
            Single<ArrayList<Tool>> toolProductsObservable = eCommerceInterface.getToolProducts()
                    .subscribeOn(Schedulers.io());
            compositeDisposable = new CompositeDisposable();


            SingleObserver<ArrayList<Tool>> toolProductsObserver = new SingleObserver<ArrayList<Tool>>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    compositeDisposable.add(d);
                }

                @Override
                public void onSuccess(@NonNull ArrayList<Tool> product) {
                    toolProductsLiveData.postValue(product);

                    toolDatabase.toolDao().insertTool(product).subscribeOn(Schedulers.computation())
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
            toolProductsObservable.subscribe(toolProductsObserver);
        }
        else{
            toolDatabase.toolDao().getTool()
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(products -> { toolProductsLiveData.postValue((ArrayList<Tool>) products) ;}, throwable -> {});
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

    public void checkIotStatus(String userName){
        Single<IOTStatus> iotStatusSingle = eCommerceInterface.getIotStatus(userName);
        SingleObserver<IOTStatus> iotStatusSingleObserver = new SingleObserver<IOTStatus>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull IOTStatus iotStatus) {
                    iotStatusMutableLiveData.postValue(iotStatus);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        };
    }

    public LiveData<ArrayList<Product>> getAllLiveDataProducts() {
        return allProductsLiveData;
    }

    public LiveData<ArrayList<Seed>> getSeedLiveDataProducts() {
        return seedProductsLiveData;
    }

    public LiveData<ArrayList<Tool>> getToolLiveDataProducts() {
        return toolProductsLiveData;
    }

    public LiveData<ArrayList<Fert>> getFerLiveDataProducts() {
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


}
