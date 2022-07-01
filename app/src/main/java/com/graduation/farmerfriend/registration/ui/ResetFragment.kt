package com.graduation.farmerfriend.registration.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.graduation.farmerfriend.R
import com.graduation.farmerfriend.databinding.FragmentResetBinding

class ResetFragment : Fragment() {

    private lateinit var viewBinding: FragmentResetBinding
    private lateinit var registrationViewModel: RegistrationViewModel
    private var emailPattern: String = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private var passwordPattern: String = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{6,}$"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentResetBinding.inflate(inflater, container, false)
        registrationViewModel = ViewModelProvider(this)[RegistrationViewModel::class.java]

        registrationViewModel.codeMessage.observe(viewLifecycleOwner) { s ->
            Toast.makeText(context, s.message, Toast.LENGTH_SHORT).show()
            viewBinding.loadingReset.visibility = View.GONE

            if (s.message.equals("The password has been changed successfully")) {
                findNavController().popBackStack(R.id.loginFragment, false)
            } else if (s.message.equals("OTP is expired, please generate the new OTP")) {
                findNavController().popBackStack()
            }

        }

        registrationViewModel.codeMessageString.observe(viewLifecycleOwner) { code ->
            Toast.makeText(context, code, Toast.LENGTH_SHORT).show()
            viewBinding.loadingReset.visibility = View.GONE
        }

        viewBinding.resetButton.setOnClickListener {
            resetPassword()
        }

        return viewBinding.root
    }

    private fun resetPassword() {
        var email: String = viewBinding.resetEmailEditText.text.toString()
        var otp: String = viewBinding.resetOtpEditText.text.toString()
        var password: String = viewBinding.resetPasswordEditText.text.toString()

        if (email.isEmpty() || otp.isEmpty() || password.isEmpty()) {
            viewBinding.resetEmailEditText.error = "Enter a valid email"
            viewBinding.resetOtpEditText.error = "Enter the code sent to you by email"
            viewBinding.resetPasswordEditText.error = "Enter a valid password"
        } else if (!email.matches(emailPattern.toRegex())) {
            viewBinding.resetEmailEditText.error = "Enter a valid email address"
        } else if (!password.matches(passwordPattern.toRegex())) {
            viewBinding.resetPasswordEditText.error =
                "The password must consist of at least 6 characters, containing at least one alphabet, one lowercase letter (“a” - “z”), and one uppercase letter (“A” - “Z”)."
        } else {
            registrationViewModel.resetPassword(email, otp, password)
            viewBinding.loadingReset.visibility = View.VISIBLE
        }
    }
}