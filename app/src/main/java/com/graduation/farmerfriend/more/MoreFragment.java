package com.graduation.farmerfriend.more;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.databinding.FragmentMoreBinding;

public class MoreFragment extends Fragment {
    FragmentMoreBinding binding ;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentMoreBinding.inflate(inflater,container,false);

        binding.fragmentMoreImageviewUserimage.setImageResource(R.drawable.will_smith);
        return binding.getRoot();
    }
}