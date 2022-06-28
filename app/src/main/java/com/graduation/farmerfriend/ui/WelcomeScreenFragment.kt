package com.graduation.farmerfriend.ui

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.graduation.farmerfriend.R
import com.graduation.farmerfriend.databinding.FragmentWelcomeScreenBinding


class WelcomeScreenFragment : Fragment() {

    private lateinit var viewBinding: FragmentWelcomeScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentWelcomeScreenBinding.inflate(inflater, container, false)

        viewBinding.skipNowButton.setOnClickListener {
            MainActivity.skipped = true
            findNavController().popBackStack()
        }

        viewBinding.welcomeLogin.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeScreenFragment_to_loginFragment)
        }

        viewBinding.welcomeSignup.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeScreenFragment_to_registrationFragment)
        }

        return viewBinding.root
    }

}

