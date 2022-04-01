package com.graduation.farmerfriend.registration

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.graduation.farmerfriend.R
import com.graduation.farmerfriend.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var viewBinding : FragmentLoginBinding
    private var hasIotSystem : Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentLoginBinding.inflate(inflater, container, false)

        val options = navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
        }

        viewBinding.loginButton.setOnClickListener (
            if (hasIotSystem)
                Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_controlFragment, null)
            else
                Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_iotIntroFragment, null)
        )

        viewBinding.createNewAccount.setOnClickListener {
            findNavController().navigate(R.id.registrationFragment, null, options)
        }

        return viewBinding.root
    }

}