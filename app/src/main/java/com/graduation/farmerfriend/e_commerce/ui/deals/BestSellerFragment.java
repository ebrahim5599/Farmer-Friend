package com.graduation.farmerfriend.e_commerce.ui.deals;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.databinding.FragmentBestSelersBinding;

public class BestSellerFragment extends Fragment {
    FragmentBestSelersBinding fragmentBestSelersBinding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentBestSelersBinding = FragmentBestSelersBinding.inflate(inflater,container,false);
        View view = fragmentBestSelersBinding.getRoot();
        BestSellerAdapter adapter = new BestSellerAdapter();
        fragmentBestSelersBinding.fragmentBestSellersRecyclerView.setAdapter(adapter);
        fragmentBestSelersBinding.fragmentBestSellersRecyclerView.setLayoutManager(new GridLayoutManager(requireContext(),3));

        Log.i("Fragment","Best sellers oncreateView");
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                Navigation.findNavController(requireView()).navigate(R.id.ECommerceFragment);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        // The callback can be enabled or disabled here or in handleOnBackPressed()
    }
}