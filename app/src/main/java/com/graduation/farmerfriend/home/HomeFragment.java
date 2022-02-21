package com.graduation.farmerfriend.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.bumptech.glide.Glide;
import com.graduation.farmerfriend.R;

import com.graduation.farmerfriend.constants.Constants;
import com.graduation.farmerfriend.databinding.FragmentHomeBinding;
import com.graduation.farmerfriend.models.Root;
import com.graduation.farmerfriend.repos.ForecastRepo;

import java.util.Locale;

public class HomeFragment extends Fragment {

    FragmentHomeBinding fragmentHomeBinding;
    ForecastViewModel viewModel;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = fragmentHomeBinding.getRoot();
        setHasOptionsMenu(true);
        requireActivity();
        sharedPreferences = requireActivity().getSharedPreferences(Constants.MAIN_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(getActivity()).get(ForecastViewModel.class);
        viewModel.getForecastModelLiveData().observe(getViewLifecycleOwner(), new Observer<Root>() {
            @Override
            public void onChanged(Root forecastModel) {
                fragmentHomeBinding.textViewDegree.setText(String.format(Locale.US,"%d°", Math.round(forecastModel.current.temp_c)));
                String imageUrl = "https://" + forecastModel.current.condition.icon;
                Log.i("Glide error", forecastModel.forecast.forecastday.toString());
                Glide.with(requireContext()).load(imageUrl).into(fragmentHomeBinding.imageView);
                fragmentHomeBinding.textViewLocation.setText(sharedPreferences.getString(Constants.LOCATION_ADDRESS,"Cairo, Egypt"));
                fragmentHomeBinding.textViewConditionText.setText(forecastModel.current.condition.text);
                fragmentHomeBinding.textViewHumidity.setText(String.format(Locale.US,"%d %%", forecastModel.current.humidity));
                fragmentHomeBinding.textViewWind.setText(String.format(Locale.US,"%d km/h", Math.round(forecastModel.current.wind_kph)));
            }
        });

        EcommerceAdapter ecommerceAdapter = new EcommerceAdapter();
        fragmentHomeBinding.homeRecyclerViewEcommerce.setAdapter(ecommerceAdapter);
        NewsAdapter newsAdapter = new NewsAdapter();
        fragmentHomeBinding.homeRecyclerViewNews.setAdapter(newsAdapter);
        fragmentHomeBinding.viewAllECommerce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_ECommerceFragment);
            }
        });
        TipsAdapter tipsAdapter = new TipsAdapter();
        fragmentHomeBinding.homeRecyclerViewTips.setAdapter(tipsAdapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.home_main_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}