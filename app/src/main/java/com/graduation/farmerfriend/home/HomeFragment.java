package com.graduation.farmerfriend.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.graduation.farmerfriend.IOT.ui.main.IOTViewModel;
import com.graduation.farmerfriend.IOTModels.Control;
import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.Tips;
import com.graduation.farmerfriend.caching_room.Tips.TipsDatabase;
import com.graduation.farmerfriend.constants.Constants;
import com.graduation.farmerfriend.databinding.FragmentHomeBinding;
import com.graduation.farmerfriend.e_commerce.ViewRecycleProductsAdapter;
import com.graduation.farmerfriend.ecommerce_models.Product;
import com.graduation.farmerfriend.forecast_models.RootForeCast;
import com.graduation.farmerfriend.ui.MainActivity;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Locale;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeFragment extends Fragment {

    boolean isWaterPumpON = false;
    boolean isFertilizerPumpON = false;
    private IOTViewModel mViewModel;
    FragmentHomeBinding fragmentHomeBinding;
    HomeViewModel viewModel;
    TipsDatabase tipsDatabase;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    ArrayList<Product> productArrayList;
    Boolean internet ;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = fragmentHomeBinding.getRoot();
        sharedPreferences = requireActivity().getSharedPreferences(Constants.MAIN_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        tipsDatabase = TipsDatabase.getInstance(getContext());

        setHasOptionsMenu(true);
        productArrayList = new ArrayList<>();
        viewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        viewModel.init(requireContext());

        if (sharedPreferences.getBoolean(Constants.HAS_IOT_SYSTEM, false)) {
            mViewModel = new ViewModelProvider(requireActivity()).get(IOTViewModel.class);
            mViewModel.init(requireContext());
            mViewModel.getIOTSensorsLiveData().observe(getViewLifecycleOwner(), sensors -> {
                Log.d("TAG", "onChanged: " + sensors.soilTemp);
                fragmentHomeBinding.fragmentIOTSoilTextViewTemp.setText(MessageFormat.format("{0} C", sensors.soilTemp));
                fragmentHomeBinding.fragmentIOTSoilTextViewHumidity.setText(MessageFormat.format("{0} %", sensors.humidity));
                fragmentHomeBinding.fragmentIOTSoilTextViewAltitude.setText(MessageFormat.format("{0} M", sensors.altitude));

                fragmentHomeBinding.fragmentWeatherProgressBar.setProgress(sensors.airTemp);
                fragmentHomeBinding.fragmentIotWeatherTextViewTemp.setText(MessageFormat.format("{0} C", sensors.airTemp));
                fragmentHomeBinding.fragmentIotWeatherTextViewLuminousIntensity.setText(MessageFormat.format("{0} lux", sensors.luminous));
                fragmentHomeBinding.fragmentIotWeatherTextViewPressure.setText(MessageFormat.format("{0} P", sensors.pressure));
            });

            mViewModel.getIOTControlLiveData().observe(getViewLifecycleOwner(), new Observer<Control>() {
                @Override
                public void onChanged(Control control) {
                    putIOT(control);
                }
            });
        }
        else
        {
               fragmentHomeBinding.homeIOTTextView.setVisibility(View.GONE);
               fragmentHomeBinding.IOTDisplay.setVisibility(View.GONE);
        }

        return view;
    }

    private void putIOT(Control control) {
        Log.d("TAG", "putIOT: " + control.waterSwitch);

        if (control.isAuto) {
            fragmentHomeBinding.fregmentIotControlLambManual.setCardBackgroundColor(Color.WHITE);
            fragmentHomeBinding.fregmentIotControlLambAutomatic.setCardBackgroundColor(Color.RED);
        } else {
            fragmentHomeBinding.fregmentIotControlLambManual.setCardBackgroundColor(Color.RED);
            fragmentHomeBinding.fregmentIotControlLambAutomatic.setCardBackgroundColor(Color.WHITE);
        }

        if (control.fertSwitch) {
            isFertilizerPumpON = true;
            fragmentHomeBinding.fregmentIotControlLambFertilizerpump.setCardBackgroundColor(Color.RED);
        } else {
            fragmentHomeBinding.fregmentIotControlLambFertilizerpump.setCardBackgroundColor(Color.WHITE);
        }

        if (control.waterSwitch) {
            isWaterPumpON = true;
            fragmentHomeBinding.fregmentIotControlLambWaterpump.setCardBackgroundColor(Color.RED);

        } else {
            fragmentHomeBinding.fregmentIotControlLambWaterpump.setCardBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);

        final NavController navController = Navigation.findNavController(view);
        if (!sharedPreferences.getBoolean(Constants.LOGGED_IN, false) && !MainActivity.skipped)
            navController.navigate(R.id.action_homeFragment_to_welcomeScreenFragment);


//        if (getConnectivityStatus(getContext())== false){
//            fragmentHomeBinding.textViewDegree.setText(sharedPreferences.getString(Constants.current_temp_c,""));
//            fragmentHomeBinding.textViewLocation.setText(sharedPreferences.getString(Constants.LOCATION_ADDRESS, "Cairo, Egypt"));
//            fragmentHomeBinding.textViewConditionText.setText(sharedPreferences.getString(Constants.current_condition,""));
//            fragmentHomeBinding.textViewHumidity.setText(sharedPreferences.getString(Constants.current_humidity,""));
//            fragmentHomeBinding.textViewWind.setText(sharedPreferences.getString(Constants.current_wind,""));
//        }

        viewModel.getForecastModelLiveData().observe(getViewLifecycleOwner(), new Observer<RootForeCast>() {
            @Override
            public void onChanged(RootForeCast forecastModel) {

                    fragmentHomeBinding.textViewDegree.setText(String.format(Locale.US, "%d°", Math.round(forecastModel.current.temp_c)));

                    String imageUrl = "https://" + forecastModel.current.condition.icon;
                    Log.i("Glide error", forecastModel.forecast.forecastday.toString());
                    Glide.with(requireContext()).load(imageUrl).into(fragmentHomeBinding.imageView);
                    fragmentHomeBinding.textViewLocation.setText(sharedPreferences.getString(Constants.LOCATION_ADDRESS, "Cairo, Egypt"));
                    fragmentHomeBinding.textViewConditionText.setText(forecastModel.current.condition.text);
                    fragmentHomeBinding.textViewHumidity.setText(String.format(Locale.US, "%d %%", forecastModel.current.humidity));
                    fragmentHomeBinding.textViewWind.setText(String.format(Locale.US, "%d km/h", Math.round(forecastModel.current.wind_kph)));

                    editor.putString(Constants.current_temp_c, String.format(Locale.US, "%d°", Math.round(forecastModel.current.temp_c)));
                    editor.putString(Constants.current_condition,forecastModel.current.condition.text);
                    editor.putString(Constants.current_humidity, String.format(Locale.US, "%d %%", forecastModel.current.humidity));
                    editor.putString(Constants.current_wind, String.format(Locale.US, "%d km/h", Math.round(forecastModel.current.wind_kph)));
                    editor.apply();
                }

        });
        viewModel.getAllProductsLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<Product>>() {
            @Override
            public void onChanged(ArrayList<Product> productArrayList) {
                ViewRecycleProductsAdapter ecommerceAdapter = new ViewRecycleProductsAdapter(requireContext(),productArrayList,"Home");
                fragmentHomeBinding.homeRecyclerViewEcommerce.setAdapter(ecommerceAdapter);
            }
        });


        NewsAdapter newsAdapter = new NewsAdapter();
        fragmentHomeBinding.homeRecyclerViewNews.setAdapter(newsAdapter);


        if (getConnectivityStatus(getContext())==false){
            tipsDatabase.tipsDao().getTips()
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(tips -> {
                        TipsAdapter tipsAdapter = new TipsAdapter((ArrayList<Tips>)tips);
                        fragmentHomeBinding.homeRecyclerViewTips.setAdapter(tipsAdapter);} , throwable -> {});

        }
        viewModel.getTipsLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<Tips>>() {
            @Override
            public void onChanged(ArrayList<Tips> tips) {
                TipsAdapter tipsAdapter = new TipsAdapter(tips);
                fragmentHomeBinding.homeRecyclerViewTips.setAdapter(tipsAdapter);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.home_main_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.community) {
            PackageManager packageManager = requireActivity().getPackageManager();
            Intent intent = packageManager.getLaunchIntentForPackage("com.example.farmer_club");
            if (intent != null) {
                startActivity(intent);
            } else {
                Intent google_play = new Intent(android.content.Intent.ACTION_VIEW);
                google_play.setData(Uri.parse("http://play.google.com/store/apps/details?id=com.example.farmer_club"));
                startActivity(google_play);
            }
        }
        return super.onOptionsItemSelected(item);
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