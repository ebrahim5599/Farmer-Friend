package com.graduation.farmerfriend.e_commerce.ui.product_activity;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.databinding.FragmentSeedProductsBinding;

public class SeedProductsFragment extends Fragment {

    FragmentSeedProductsBinding binding ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentSeedProductsBinding.inflate(inflater, container, false);

        ProductItemAdapter adapter = new ProductItemAdapter();
        binding.fragmentSeedsProductsRecyclerView.setAdapter(adapter);
        binding.fragmentSeedsProductsRecyclerView.setLayoutManager(new GridLayoutManager(requireContext(),3));
        Log.i("Fragment","seeds fragment oncreateview");

        return binding.getRoot();
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