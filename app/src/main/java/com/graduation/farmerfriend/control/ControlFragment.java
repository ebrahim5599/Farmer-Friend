package com.graduation.farmerfriend.control;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.control.iot_fragments.IOTAdapter;
import com.graduation.farmerfriend.databinding.FragmentControlBinding;

public class ControlFragment extends Fragment {

    FragmentControlBinding binding;
    ViewPager2 viewPager2;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentControlBinding.inflate(getLayoutInflater(),container,false);

        //TODO:**************************************************************************************************
        RegistrationFragment registrationFragment = new RegistrationFragment();
        boolean first_run = true;
        if(first_run){
            binding.linearVisibility.setVisibility(View.GONE);
            if (savedInstanceState == null) {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.registration_fragment_container, registrationFragment);
                fragmentTransaction.commit();
            }
        } else {
            binding.registrationFragmentContainer.setVisibility(View.GONE);
        }
        //TODO:**************************************************************************************************
//        binding.fragmentControlButtonLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(requireContext(), IOT_Activity.class);
//                startActivity(intent);
//            }
//        });

        IOTAdapter iotAdapter = new IOTAdapter(this);
        viewPager2 = binding.viewPager2;
        viewPager2.setAdapter(iotAdapter);

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new TabLayoutMediator(binding.tabs, viewPager2,
                (tab, position) -> tab.setText(setTabTitle(position))
        ).attach();
        binding.tabs.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#00796B"));
        binding.tabs.setSelectedTabIndicatorColor(Color.parseColor("#00796B"));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                Navigation.findNavController(requireView()).navigate(R.id.homeFragment);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        // The callback can be enabled or disabled here or in handleOnBackPressed()
    }
    @Override
    public void onPause() {
        super.onPause();

        Toast.makeText(requireContext(),"onPause() IOTControl ",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(requireContext(),"onDestroy() IOTControl ",Toast.LENGTH_LONG).show();
    }

    public String setTabTitle(int pageNumber) {
        switch (pageNumber) {
            case 0:
                return "Weather";
            case 1:
                return "Soil";
            default:
                return "Control";
        }
    }
}