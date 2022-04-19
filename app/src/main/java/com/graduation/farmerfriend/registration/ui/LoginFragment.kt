package com.graduation.farmerfriend.registration.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.graduation.farmerfriend.R
import com.graduation.farmerfriend.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var viewBinding : FragmentLoginBinding
    private var hasIotSystem : Boolean = false
    private lateinit var registrationViewModel: RegistrationViewModel
    private var emailPattern : String = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

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

        registrationViewModel = ViewModelProvider(this)[RegistrationViewModel::class.java]
/* 1 */
//        viewBinding.loginButton.setOnClickListener (
//            if (hasIotSystem){
//                Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_controlFragment, null)
//            }else{
//                Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_iotIntroFragment, null)
//            }
//        )

/* 2 */
//        viewBinding.loginButton.setOnClickListener (
//            if (hasIotSystem){
//                registrationViewModel.getUserDataFromLogin()
//                Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_controlFragment, null)
//            }else{
//                registrationViewModel.getUserDataFromLogin()
//                Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_iotIntroFragment, null)
//            }
//        )

        viewBinding.loginButton.setOnClickListener {
                performLogin()
        }

        registrationViewModel.userDataMutableLiveData.observe(
            viewLifecycleOwner
        ) { userData ->
            Toast.makeText(context, userData.firstName, Toast.LENGTH_SHORT).show()
            viewBinding.loadingLogin.visibility = View.GONE
            if (userData.hasIotSystem)
                findNavController().navigate(R.id.action_loginFragment_to_controlFragment)
            else
                findNavController().navigate(R.id.action_loginFragment_to_iotIntroFragment)
        }

        registrationViewModel.errorMessage.observe(
            viewLifecycleOwner
        ) { s ->
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
            viewBinding.loadingLogin.visibility = View.GONE
        }

        viewBinding.createNewAccount.setOnClickListener {
            findNavController().navigate(R.id.registrationFragment, null, options)
        }

        return viewBinding.root
    }

    private fun performLogin(){
        var email : String = viewBinding.loginEmailEditText.text.toString()
        var password : String = viewBinding.loginPasswordEditText.text.toString()

        if(!email.matches(emailPattern.toRegex()))
            viewBinding.loginEmailEditText.error = "Enter Correct Email"
        else if(password.isEmpty())
            viewBinding.loginPasswordEditText.error = "Enter the correct password"
        else{
            registrationViewModel.getUserDataFromLogin(email, password)
            viewBinding.loadingLogin.visibility = View.VISIBLE
        }
    }
}