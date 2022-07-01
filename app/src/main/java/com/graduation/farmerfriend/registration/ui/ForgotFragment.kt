package com.graduation.farmerfriend.registration.ui

import android.opengl.Visibility
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
import com.graduation.farmerfriend.databinding.FragmentForgotBinding
import com.graduation.farmerfriend.registration.pojo.ForgotPassword

class ForgotFragment : Fragment() {

    private lateinit var viewBinding: FragmentForgotBinding
    private lateinit var registrationViewModel: RegistrationViewModel
    private var emailPattern: String = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentForgotBinding.inflate(inflater, container, false)
        registrationViewModel = ViewModelProvider(this)[RegistrationViewModel::class.java]

        val options = navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
        }

        registrationViewModel.codeMessage.observe(viewLifecycleOwner) { s ->
            Toast.makeText(context, s.message, Toast.LENGTH_SHORT).show()
            viewBinding.loadingForgot.visibility = View.GONE
            if (s.message.equals("Token sent successfully in email")) {
                findNavController().navigate(R.id.resetFragment, null, options)
                s.setMessage("back")
            }
        }

        registrationViewModel.codeMessageString.observe(viewLifecycleOwner){ code ->
            viewBinding.loadingForgot.visibility = View.GONE
            Toast.makeText(context, code, Toast.LENGTH_SHORT).show()
        }

        viewBinding.forgotButton.setOnClickListener {
            sendOtpCode()
        }

        return viewBinding.root
    }

    private fun sendOtpCode() {
        var email: String = viewBinding.forgotEmailEditText.text.toString()

        if (!email.matches(emailPattern.toRegex()))
            viewBinding.forgotEmailEditText.error = "Enter Correct Email"
        else {
            registrationViewModel.sendCode(email)
            viewBinding.loadingForgot.visibility = View.VISIBLE
        }

    }
}