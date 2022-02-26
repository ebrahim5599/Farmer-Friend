package com.graduation.farmerfriend.more;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.databinding.FragmentMoreBinding;
import com.graduation.farmerfriend.registration.RegisterActivity;

public class MoreFragment extends Fragment {
    FragmentMoreBinding binding ;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentMoreBinding.inflate(inflater,container,false);
        binding.fragmentMoreImageviewUserimage.setImageResource(R.drawable.will_smith);

        binding.fragmentMoreTextviewStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.storeFragment);
            }
        });

        binding.fragmentMoreTextviewCommunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });
        return binding.getRoot();
    }
}