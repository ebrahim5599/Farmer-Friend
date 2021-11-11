package com.graduation.farmerfriend.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.graduation.farmerfriend.databinding.FragmentHomeBinding;
import com.graduation.farmerfriend.models.ForecastModel;
import com.graduation.farmerfriend.repos.ForecastRepo;
import com.squareup.picasso.Picasso;


public class HomeFragment extends Fragment {

    FragmentHomeBinding fragmentHomeBinding;
    ForecastViewModel viewModel;
    ForecastRepo forecastRepo;
    ForecastModel forecastModel0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = fragmentHomeBinding.getRoot();
        forecastRepo = new ForecastRepo(getContext());
        viewModel = new ViewModelProvider(this).get(ForecastViewModel.class);
        viewModel.init(getContext());
        viewModel.getForecastModelLiveData().observe(getViewLifecycleOwner(), new Observer<ForecastModel>() {
            @Override
            public void onChanged(ForecastModel forecastModel) {
                fragmentHomeBinding.textViewDegree.setText(String.valueOf((int)forecastModel.dailyForecasts.get(0).temperature.maximum.value));
                if (forecastModel.dailyForecasts.get(0).night != null) {
                    String imageUrl = "https://www.accuweather.com/images/weathericons/1.svg"
                            +forecastModel.dailyForecasts.get(0).night.icon+".svg";
                }
                fragmentHomeBinding.textView2.setText(String.valueOf(forecastModel.dailyForecasts.get(0).night.iconPhrase));

            }
        });

//        forecastRepo.setForecastData();
        fragmentHomeBinding.forecastCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                forecastRepo.setForecastData();
                viewModel.setForecastData();
//                fragmentHomeBinding.textViewDegree.setText(String.valueOf(forecastRepo.getForecastModel().dailyForecasts.get(0).temperature.maximum.value));
            }
        });
//        if(forecastModel0 != null)
//        fragmentHomeBinding.textViewDegree.setText(String.valueOf((int) forecastModel0.dailyForecasts.get(0).temperature.maximum.value));
        EcommerceAdapter ecommerceAdapter = new EcommerceAdapter();
        fragmentHomeBinding.recyclerViewEcommerce.setAdapter(ecommerceAdapter);
        NewsAdapter newsAdapter = new NewsAdapter();
        fragmentHomeBinding.recyclerViewNews.setAdapter(newsAdapter);
        return view;
    }


}