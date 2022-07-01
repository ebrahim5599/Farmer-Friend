package com.graduation.farmerfriend.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.graduation.farmerfriend.IOT.ui.main.IOTViewModel;
import com.graduation.farmerfriend.IOTModels.Control;
import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.Tips;
import com.graduation.farmerfriend.constants.Constants;
import com.graduation.farmerfriend.databinding.FragmentHomeBinding;
import com.graduation.farmerfriend.e_commerce.ViewRecycleProductsAdapter;
import com.graduation.farmerfriend.ecommerce_models.IOTStatus;
import com.graduation.farmerfriend.ecommerce_models.Product;
import com.graduation.farmerfriend.forecast_models.RootForeCast;
import com.graduation.farmerfriend.repos.TipsRepo;
import com.graduation.farmerfriend.ui.MainActivity;

import java.util.ArrayList;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.Locale;

public class HomeFragment extends Fragment {

    boolean isWaterPumpON = false;
    boolean isFertilizerPumpON = false;
    private IOTViewModel mViewModel;
    FragmentHomeBinding binding;
    HomeViewModel viewModel;
    SharedPreferences sharedPreferences;
    ArrayList<Product> productArrayList;
    ArrayList<Tips> tipsArrayList;
    TipsAdapter tipsAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        setHasOptionsMenu(true);
        productArrayList = new ArrayList<>();
        sharedPreferences = requireActivity().getSharedPreferences(Constants.MAIN_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        viewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);

        binding.homePullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.setForecastData(sharedPreferences.getString(Constants.LOCATION, "Cairo"));
                viewModel.getEcommerceAllProducts();
                viewModel.checkIotStatus(sharedPreferences.getString(Constants.USER_NAME, ""));
//                Collections.shuffle(tipsArrayList);
//                tipsAdapter = new TipsAdapter(tipsArrayList);
//                binding.homeRecyclerViewTips.setAdapter(tipsAdapter);
            }
        });
        viewModel.getIOTStatusLiveData().observe(getViewLifecycleOwner(), new Observer<IOTStatus>() {
            @Override
            public void onChanged(IOTStatus iotStatus) {
                if (iotStatus != null) {
                    controlIotINHomeFragment(iotStatus.hasIotSystem);
                    if (iotStatus.hasIotSystem) {
                        binding.homeIOTTextView.setVisibility(View.VISIBLE);
                        binding.IOTDisplay.setVisibility(View.VISIBLE);
                    }
                } else {
                    controlIotINHomeFragment(sharedPreferences.getBoolean(Constants.HAS_IOT_SYSTEM, false));
                }

            }
        });

        return view;
    }

    private void putIOT(Control control) {
        Log.d("TAG", "putIOT: " + control.waterSwitch);

        if (control.isAuto) {
            binding.fregmentIotControlLambManual.setCardBackgroundColor(Color.WHITE);
            binding.fregmentIotControlLambAutomatic.setCardBackgroundColor(Color.RED);
        } else {
            binding.fregmentIotControlLambManual.setCardBackgroundColor(Color.RED);
            binding.fregmentIotControlLambAutomatic.setCardBackgroundColor(Color.WHITE);
        }

        if (control.fertSwitch) {
            isFertilizerPumpON = true;
            binding.fregmentIotControlLambFertilizerpump.setCardBackgroundColor(Color.RED);
        } else {
            binding.fregmentIotControlLambFertilizerpump.setCardBackgroundColor(Color.WHITE);
        }

        if (control.waterSwitch) {
            isWaterPumpON = true;
            binding.fregmentIotControlLambWaterpump.setCardBackgroundColor(Color.RED);

        } else {
            binding.fregmentIotControlLambWaterpump.setCardBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getForecastModelLiveData().observe(getViewLifecycleOwner(), new Observer<RootForeCast>() {
            @Override
            public void onChanged(RootForeCast forecastModel) {
                binding.textViewDegree.setText(String.format(Locale.US, "%d°", Math.round(forecastModel.current.temp_c)));
                String imageUrl = "https://" + forecastModel.current.condition.icon;
                Log.i("Glide error", forecastModel.forecast.forecastday.toString());
                Glide.with(requireContext()).load(imageUrl).into(binding.imageView);
                binding.textViewLocation.setText(sharedPreferences.getString(Constants.LOCATION_ADDRESS, "Cairo, Egypt"));
                binding.textViewConditionText.setText(forecastModel.current.condition.text);
                binding.textViewHumidity.setText(String.format(Locale.US, "%d %%", forecastModel.current.humidity));
                binding.textViewWind.setText(String.format(Locale.US, "%d km/h", Math.round(forecastModel.current.wind_kph)));
                binding.textViewCurrentTime.setText(forecastModel.location.localtime);

                binding.homePullToRefresh.setRefreshing(false);
            }
        });
        viewModel.getAllProductsLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<Product>>() {
            @Override
            public void onChanged(ArrayList<Product> productArrayList) {
                ViewRecycleProductsAdapter ecommerceAdapter = new ViewRecycleProductsAdapter(requireContext(), productArrayList, "Home");
                binding.homeRecyclerViewEcommerce.setAdapter(ecommerceAdapter);
            }
        });


        NewsAdapter newsAdapter = new NewsAdapter();
        binding.homeRecyclerViewNews.setAdapter(newsAdapter);


        viewModel.getTipsLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<Tips>>() {
            @Override
            public void onChanged(ArrayList<Tips> tips) {
                tipsArrayList = tips;
                tipsAdapter = new TipsAdapter(tips);
                binding.homeRecyclerViewTips.setAdapter(tipsAdapter);
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

    void controlIotINHomeFragment(boolean hasIotStatus) {
        if (hasIotStatus) {
            mViewModel = new ViewModelProvider(requireActivity()).get(IOTViewModel.class);
            mViewModel.init(requireContext());

            mViewModel.getIOTSensorsLiveData().observe(getViewLifecycleOwner(), sensors -> {
                Log.d("TAG", "onChanged: " + sensors.soilTemp);
                binding.fragmentIOTSoilTextViewTemp.setText(MessageFormat.format("{0} C", sensors.soilTemp));
                binding.fragmentIOTSoilTextViewMoisture.setText(MessageFormat.format("{0} %", sensors.moisture));

                binding.fragmentWeatherProgressBar.setProgress(sensors.airTemp);
                binding.fragmentIotWeatherTextViewHumidity.setText(MessageFormat.format("{0} %", sensors.humidity));
                binding.fragmentIotWeatherTextViewTemp.setText(MessageFormat.format("{0} C", sensors.airTemp));
                binding.fragmentIotWeatherTextViewLuminousIntensity.setText(MessageFormat.format("{0} lx", sensors.luminous));
                binding.fragmentIotWeatherTextViewPressure.setText(MessageFormat.format("{0} mb", sensors.pressure));
            });

            mViewModel.getIOTControlLiveData().observe(getViewLifecycleOwner(), new Observer<Control>() {
                @Override
                public void onChanged(Control control) {
                    putIOT(control);
                }
            });
        } else {
            binding.homeIOTTextView.setVisibility(View.GONE);
            binding.IOTDisplay.setVisibility(View.GONE);
        }
    }


}