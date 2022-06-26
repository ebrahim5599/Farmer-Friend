package com.graduation.farmerfriend.registration.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.graduation.farmerfriend.R
import com.graduation.farmerfriend.constants.Constants
import com.graduation.farmerfriend.databinding.FragmentLoginBinding
import com.graduation.farmerfriend.sharedPreferences.SharedPref

class LoginFragment : Fragment() {

    private lateinit var viewBinding: FragmentLoginBinding
    private var hasIotSystem: Boolean = false
    private lateinit var registrationViewModel: RegistrationViewModel
    private var emailPattern: String = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sharedPref: SharedPref

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
        sharedPreferences = requireActivity().getSharedPreferences(
            Constants.MAIN_SHARED_PREFERENCES,
            Context.MODE_PRIVATE
        )
        sharedPref = SharedPref(requireContext(), Constants.MAIN_SHARED_PREFERENCES)

        registrationViewModel = ViewModelProvider(this)[RegistrationViewModel::class.java]

        viewBinding.loginButton.setOnClickListener {
            performLogin()
        }

        registrationViewModel.userDataMutableLiveData.observe(
            viewLifecycleOwner
        ) { userData ->

            sharedPreferences.edit().putBoolean(Constants.LOGGED_IN, true).apply()
            sharedPreferences.edit().putString(
                Constants.FIRST_AND_LAST_NAME,
                userData.firstName + " " + userData.lastName
            ).apply()
            sharedPref.putStringPref(Constants.USER_ID, userData.id)
            sharedPref.putStringPref(Constants.USER_NAME, userData.username)
            sharedPref.putBoolPref(Constants.ENABLE, true)
            sharedPref.putIntPref(Constants.COUNTER, 2)
            sharedPref.putStringPref(Constants.NAME_BUTTON_MAIL, "send request")
            Log.d("TAG", "onCreateView: " + userData.id)
            sharedPref.putBoolPref(Constants.HAS_IOT_SYSTEM, userData.hasIotSystem)
            Toast.makeText(context, userData.firstName, Toast.LENGTH_SHORT).show()
            viewBinding.loadingLogin.visibility = View.GONE

            //TODO:00000000000000000000000000000000000000000000000000000000000000000
            val previousFragment = findNavController().previousBackStackEntry?.destination?.id

            previousFragment?.let {
                when (previousFragment) {
                    R.id.moreFragment ->
                        // The previous fragment is moreFragment
                        findNavController().popBackStack()

                    R.id.controlContainerFragment -> {
                        // The previous fragment is controlContainerFragment
                        if (userData.hasIotSystem)
                            findNavController().navigate(R.id.action_loginFragment_to_controlFragment)
                        else
                            findNavController().navigate(R.id.action_loginFragment_to_iotIntroFragment)
                    }

                    R.id.cartFragment -> {
                        // The previous fragment is cartFragment
                        findNavController().navigate(R.id.action_loginFragment_to_userDataFragment)
                    }
                    else ->
                        // The previous fragment is neither Fragment 2 nor 4
                        Log.i("TAG", "onCreateView: ElseFragment")
                }
            }
            /* */
//            if (userData.hasIotSystem)
//                findNavController().navigate(R.id.action_loginFragment_to_controlFragment)
//            else
//                findNavController().navigate(R.id.action_loginFragment_to_iotIntroFragment)
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

    private fun performLogin() {
        var email: String = viewBinding.loginEmailEditText.text.toString()
        var password: String = viewBinding.loginPasswordEditText.text.toString()

        if (!email.matches(emailPattern.toRegex()))
            viewBinding.loginEmailEditText.error = "Enter Correct Email"
        else if (password.isEmpty())
            viewBinding.loginPasswordEditText.error = "Enter the correct password"
        else {
            registrationViewModel.getUserDataFromLogin(email, password)
            viewBinding.loadingLogin.visibility = View.VISIBLE
        }
    }
}