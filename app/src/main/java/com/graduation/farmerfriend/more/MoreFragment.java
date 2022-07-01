package com.graduation.farmerfriend.more;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.constants.Constants;
import com.graduation.farmerfriend.databinding.FragmentMoreBinding;

public class MoreFragment extends Fragment {
    FragmentMoreBinding binding;
    SharedPreferences sharedPreferences;
    MutableLiveData<String> mutableLiveDataForName;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container,
                             @NonNull Bundle savedInstanceState) {
        binding = FragmentMoreBinding.inflate(inflater, container, false);

        sharedPreferences = requireActivity().getSharedPreferences(Constants.MAIN_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        mutableLiveDataForName = new MutableLiveData<>();

        if (sharedPreferences.getBoolean(Constants.LOGGED_IN, false))
            binding.fragmentMoreTextviewLogin.setVisibility(View.GONE);
        binding.fragmentMoreTextviewLogin.setPaintFlags(binding.fragmentMoreTextviewLogin.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        binding.fragmentMoreImageviewUserimage.setImageResource(R.mipmap.ic_launcher);
        binding.fragmentMoreTextviewName.setText(sharedPreferences.getString(
                Constants.FIRST_AND_LAST_NAME, "Anonymous User"));

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
                PackageManager packageManager = getActivity().getPackageManager();
                Intent intent = packageManager.getLaunchIntentForPackage("com.example.farmer_club");
                if (intent != null) {
                    startActivity(intent);
                } else {
                    Intent google_play = new Intent(android.content.Intent.ACTION_VIEW);
                    google_play.setData(Uri.parse("http://play.google.com/store/apps/details?id=com.example.farmer_club"));
                    startActivity(google_play);
                }
            }
        });

        binding.fragmentMoreTextviewAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.about_us);
            }
        });

        binding.fragmentMoreTextviewLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Logout", Toast.LENGTH_SHORT).show();
                sharedPreferences.edit().putBoolean(Constants.LOGGED_IN, false).apply();

                sharedPreferences.edit().putString(Constants.FIRST_AND_LAST_NAME, "Anonymous User").apply();
                sharedPreferences.edit().putString(Constants.USER_ID, "").apply();

                mutableLiveDataForName.setValue("Anonymous User");
                TransitionManager.beginDelayedTransition(binding.getRoot(), new AutoTransition());
                binding.fragmentMoreTextviewLogin.setVisibility(View.VISIBLE);
                sharedPreferences.edit().putBoolean(Constants.HAS_IOT_SYSTEM, false).apply();

            }
        });

        binding.fragmentMoreTextviewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_moreFragment_to_loginFragment);
            }
        });

        return binding.getRoot();
    }
}