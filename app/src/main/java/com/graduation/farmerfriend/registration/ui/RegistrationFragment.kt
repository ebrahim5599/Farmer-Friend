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
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.graduation.farmerfriend.R
import com.graduation.farmerfriend.constants.Constants
import com.graduation.farmerfriend.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {

    private lateinit var viewBinding: FragmentRegistrationBinding
    private lateinit var registrationViewModel: RegistrationViewModel
    private var emailPattern: String = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private var passwordPattern: String = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{6,}$"
    private lateinit var sharedPreferences : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentRegistrationBinding.inflate(inflater, container, false)
        sharedPreferences = requireActivity().getSharedPreferences(Constants.MAIN_SHARED_PREFERENCES, Context.MODE_PRIVATE)

        val options = navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
        }

        registrationViewModel = ViewModelProvider(this)[RegistrationViewModel::class.java]

        registrationViewModel.userDataMutableLiveData.observe(
            viewLifecycleOwner
        ) { userData ->
            Toast.makeText(context, userData.firstName, Toast.LENGTH_SHORT).show()
            sharedPreferences.edit().putBoolean(Constants.LOGGED_IN, true).apply()
            sharedPreferences.edit().putString(
                Constants.FIRST_AND_LAST_NAME,
                userData.firstName+" " +userData.lastName).apply()
            sharedPreferences.edit().putString(Constants.USER_ID,userData.id)
            sharedPreferences.edit().putString(Constants.USER_NAME,userData.username)

            Log.i("info", "Success")
            viewBinding.loadingRegister.visibility = View.GONE
            findNavController().navigate(R.id.action_registrationFragment_to_iotIntroFragment)
        }

        registrationViewModel.errorMessage.observe(
            viewLifecycleOwner
        ) { s ->
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
            Log.i("info", "Error")
            viewBinding.loadingRegister.visibility = View.GONE
        }
//
//        viewBinding.registerButton.setOnClickListener(
//            Navigation.createNavigateOnClickListener(R.id.next_action, null)
//        )

        viewBinding.registerButton.setOnClickListener {
            performRegistration()
        }

        viewBinding.backToLogin.setOnClickListener {
            findNavController().popBackStack()
        }

        return viewBinding.root
    }

    private fun performRegistration() {
        var firstName: String = viewBinding.registerFirstNameEditText.text.toString()
        var lastName: String = viewBinding.registerLastNameEditText.text.toString()
        var username: String = viewBinding.registerUsernameEditText.text.toString()
        var email: String = viewBinding.registerEmailEditText.text.toString()
        var password: String = viewBinding.registerPasswordEditText.text.toString()
        var confirmPassword: String = viewBinding.registerConfirmPasswordEditText.text.toString()

        // Valid email address format.
        if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty()
            || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()
        ) {
            viewBinding.registerFirstNameEditText.error = "Enter your first name"
            viewBinding.registerLastNameEditText.error = "Enter your last name"
            viewBinding.registerUsernameEditText.error = "Enter a valid username"
            viewBinding.registerEmailEditText.error = "Enter a valid email"
            viewBinding.registerPasswordEditText.error = "Enter a valid password"
            viewBinding.registerConfirmPasswordEditText.error = "confirm your password"

        } else if (!email.matches(emailPattern.toRegex())) {
            viewBinding.registerEmailEditText.error = "Enter a valid email address"
        } else if (!password.matches(passwordPattern.toRegex())) {
            viewBinding.registerPasswordEditText.error = "The password must consist of at least 6 characters, containing at least one alphabet, one lowercase letter (“a” - “z”), and one uppercase letter (“A” - “Z”)."
        } else if (password != confirmPassword) {
            viewBinding.registerConfirmPasswordEditText.error = "Password does not match both fields"
        } else {
            registrationViewModel.getUserDataFromRegistration(firstName,lastName,username,email,password)
            viewBinding.loadingRegister.visibility = View.VISIBLE
        }
    }
}
