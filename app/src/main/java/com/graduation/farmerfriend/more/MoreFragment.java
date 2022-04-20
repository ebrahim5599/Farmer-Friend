package com.graduation.farmerfriend.more;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.databinding.FragmentMoreBinding;

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
            }
        });
        return binding.getRoot();
    }
}