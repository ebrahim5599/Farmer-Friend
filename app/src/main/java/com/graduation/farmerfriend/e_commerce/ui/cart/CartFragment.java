package com.graduation.farmerfriend.e_commerce.ui.cart;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.constants.Constants;
import com.graduation.farmerfriend.databinding.FragmentCartBinding;
import com.graduation.farmerfriend.ecommerce_models.Cart;
import com.graduation.farmerfriend.home.HomeViewModel;
import com.graduation.farmerfriend.sharedPreferences.SharedPref;

import java.util.ArrayList;


public class CartFragment extends Fragment {

    private FragmentCartBinding binding;
    private CartViewModel cartViewModel;
    private SharedPref sharedPref;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPref = new SharedPref(requireContext(),Constants.MAIN_SHARED_PREFERENCES);
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        cartViewModel.init();
        cartViewModel.getCartData(sharedPref.getStringPref(Constants.USER_ID,null));

        // This callback will only be called when MyFragment is at least Started.
//        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
//            @Override
//            public void handleOnBackPressed() {
//                switch(CartFragmentArgs.fromBundle(getArguments()).getFromWhichFragment()){
//                    case Constants.FROM_E_COMMERCE_FRAGMENT:
//                        Navigation.findNavController(requireView()).navigate(R.id.ECommerceFragment);
//                        break;
//                    case  Constants.FROM_TOOLS_FRAGMENT:
//                        Navigation.findNavController(requireView()).navigate(R.id.toolProductsFragment);
//                        break;
//                }
//            }
//        };
//        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        // The callback can be enabled or disabled here or in handleOnBackPressed()
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        cartViewModel.getCartLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<Cart>>() {
                    @Override
                    public void onChanged(ArrayList<Cart> carts) {
                        CartItemsAdapter adapter = new CartItemsAdapter(CartFragment.this,requireContext(),carts,sharedPref.getStringPref(Constants.USER_ID,""));
                        binding.cartRecyclerView.setAdapter(adapter);
                        binding.cartRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
                    }
                });

//        Drawable mDivider = ContextCompat.getDrawable(requireContext(), R.drawable.ic_divider);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);
//        itemDecoration.setDrawable();
        binding.cartRecyclerView.addItemDecoration(itemDecoration);

        binding.cartButtonCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sharedPref.getBoolPref(Constants.LOGGED_IN))
                    Navigation.findNavController(requireView()).navigate(R.id.action_cartFragment_to_userDataFragment);
                else
                    Navigation.findNavController(requireView()).navigate(R.id.action_cartFragment_to_loginFragment);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

}