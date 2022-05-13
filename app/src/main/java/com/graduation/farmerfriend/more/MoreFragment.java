package com.graduation.farmerfriend.more;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.constants.Constants;
import com.graduation.farmerfriend.databinding.FragmentMoreBinding;

public class MoreFragment extends Fragment {
    FragmentMoreBinding binding ;
    private SharedPreferences sharedPreferences;
    private MutableLiveData<String> mutableLiveDataForName;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentMoreBinding.inflate(inflater,container,false);

        sharedPreferences = requireActivity().getSharedPreferences(Constants.MAIN_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        mutableLiveDataForName = new MutableLiveData<>();


        binding.fragmentMoreImageviewUserimage.setImageResource(R.drawable.will_smith);
        binding.fragmentMoreTextviewName.setText(sharedPreferences.getString(
                Constants.FIRST_AND_LAST_NAME,"Log in"));

        mutableLiveDataForName.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.fragmentMoreTextviewName.setText(s);
            }
        });



        binding.fragmentMoreTextviewStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.storeFragment);
            }
        });

        binding.fragmentMoreTextviewCommunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Go To Farmer Club app", Toast.LENGTH_SHORT).show();
            }
        });

        binding.fragmentMoreTextviewAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "About us", Toast.LENGTH_SHORT).show();
            }
        });

        binding.fragmentMoreTextviewLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Logout", Toast.LENGTH_SHORT).show();
                sharedPreferences.edit().putBoolean(Constants.LOGGED_IN, false).apply();
                sharedPreferences.edit().putString(Constants.FIRST_AND_LAST_NAME, "").apply();
                sharedPreferences.edit().putString(Constants.USER_ID,"").apply();
                mutableLiveDataForName.setValue("");
            }
        });
        return binding.getRoot();
    }
}