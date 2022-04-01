package com.graduation.farmerfriend.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.graduation.farmerfriend.R
import com.graduation.farmerfriend.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {

    private lateinit var viewBinding : FragmentRegistrationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentRegistrationBinding.inflate(inflater, container, false)

        val options = navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
        }

        viewBinding.registerButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.next_action, null)
        )


//        viewBinding.alreadyHaveAnAccount.setOnClickListener {
//            findNavController().navigate(R.id.loginFragment, null, options)
//        }
        return viewBinding.root
    }

}