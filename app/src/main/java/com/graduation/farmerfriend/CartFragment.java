package com.graduation.farmerfriend;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.graduation.farmerfriend.databinding.FragmentCartBinding;
import com.graduation.farmerfriend.e_commerce.ui.cart.CartItemsAdapter;
import com.graduation.farmerfriend.e_commerce.ui.cart.UserDataActivity;


public class CartFragment extends Fragment {

    FragmentCartBinding binding;
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        CartItemsAdapter adapter = new CartItemsAdapter(requireContext());
        binding.cartRecyclerView.setAdapter(adapter);
        binding.cartRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

//        Drawable mDivider = ContextCompat.getDrawable(requireContext(), R.drawable.ic_divider);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);
//        itemDecoration.setDrawable();
        binding.cartRecyclerView.addItemDecoration(itemDecoration);

        binding.cartButtonCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), UserDataActivity.class);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

}