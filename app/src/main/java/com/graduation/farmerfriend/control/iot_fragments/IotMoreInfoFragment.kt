package com.graduation.farmerfriend.control.iot_fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.graduation.farmerfriend.R
import com.graduation.farmerfriend.constants.Constants
import com.graduation.farmerfriend.control.iot_fragments.Mail.Mailing
import com.graduation.farmerfriend.control.iot_fragments.Mail.User_Data_Mail
import com.graduation.farmerfriend.databinding.FragmentIotMoreInfoBinding
import com.graduation.farmerfriend.sharedPreferences.SharedPref
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Callback
import retrofit2.Response

class IotMoreInfoFragment : Fragment() {

    private lateinit var viewBinding: FragmentIotMoreInfoBinding
    private lateinit var iotWaitingCodeFragment: IotWaitingCodeFragment
    private lateinit var sharedPref: SharedPref
    var phone: String = ""
    var email: String = ""
    var area: String = ""
    var location: String = ""
    var count = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentIotMoreInfoBinding.inflate(inflater, container, false)
        sharedPref = SharedPref(requireContext(), Constants.MAIN_SHARED_PREFERENCES)
        count = sharedPref.getIntPref(Constants.COUNTER)

        val options = navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
        }

        if (viewBinding.fragmentIotMoreInfoPhoneNumber.onCheckIsTextEditor() ||
                viewBinding.fragmentIotMoreInfoEmail.onCheckIsTextEditor() ||
                viewBinding.fragmentIotMoreInfoArea.onCheckIsTextEditor() ||
                viewBinding.fragmentIotMoreInfoLocation.onCheckIsTextEditor()){

            sharedPref.putStringPref(Constants.NAME_BUTTON_MAIL, "Edit data")
            viewBinding.iotMoreInfoButtonText.text =
                sharedPref.getStringPref(Constants.NAME_BUTTON_MAIL, "")
        }
        viewBinding.iotMoreInfoButton.setOnClickListener {
            if (viewBinding.iotMoreInfoButtonText.text == getString(R.string.next)) {
                findNavController().navigate(R.id.next_action, null, options)
            } else {
                phone = viewBinding.fragmentIotMoreInfoPhoneNumber.text.toString()
                email = viewBinding.fragmentIotMoreInfoEmail.text.toString()
                area = viewBinding.fragmentIotMoreInfoArea.text.toString()
                location = viewBinding.fragmentIotMoreInfoLocation.text.toString()

                sharedPref.putStringPref(Constants.PHONE, phone)
                sharedPref.putStringPref(Constants.EMAIL, email)
                sharedPref.putStringPref(Constants.AREA, area)
                sharedPref.putStringPref(Constants.LOCATION_USER, location)

                var body = phone + "\n" + email + "\n" + area + "\n" + location
                var username = sharedPref.getStringPref(Constants.USER_NAME, "")
                var user_mail = User_Data_Mail(username, body)
                findNavController().navigate(R.id.next_action, null, options)

                if(phone != "" && email != "" && area != "" && location != ""){
                    sendMail(user_mail)
                }else{
                    Toast.makeText(context,"Please fill in the data",Toast.LENGTH_SHORT).show()
                }

            }
        }


//        phone = viewBinding.fragmentIotMoreInfoPhoneNumber.text.toString()
//        email = viewBinding.fragmentIotMoreInfoEmail.text.toString()
//        area = viewBinding.fragmentIotMoreInfoArea.text.toString()
//        location = viewBinding.fragmentIotMoreInfoLocation.text.toString()
//        viewBinding.fragmentIotMoreInfoPhoneNumber.setText( sharedPref.getStringPref(Constants.PHONE,""))
//        viewBinding.fragmentIotMoreInfoEmail.setText(sharedPref.getStringPref(Constants.EMAIL,""))
//        viewBinding.fragmentIotMoreInfoArea.setText (sharedPref.getStringPref(Constants.AREA,""))
//        viewBinding.fragmentIotMoreInfoLocation.setText(sharedPref.getStringPref(Constants.LOCATION_USER,""))
//        viewBinding.iotMoreInfoButtonText.text = sharedPref.getStringPref(Constants.NAME_BUTTON_MAIL,"")
//
//
//        viewBinding.iotMoreInfoButton.setOnClickListener {
//            if (viewBinding.iotMoreInfoButtonText.text == "next"){
//                findNavController().navigate(R.id.next_action, null, options)
//            }else {
//                var body = phone + "\n" + email + "\n" + area + "\n" + location
//                var username = sharedPref.getStringPref(Constants.USER_NAME, "")
//                var user_mail = User_Data_Mail(username, body)
//                findNavController().navigate(R.id.next_action, null, options)
//                Mail(user_mail, phone, email, area, location)
//            }
//        }
        return viewBinding.root
    }


    override fun onAttachFragment(childFragment: Fragment) {
        if (viewBinding.iotMoreInfoButtonText.text == "next" && (phone != sharedPref.getStringPref(
                Constants.PHONE,
                ""
            )
                    || email != sharedPref.getStringPref(
                Constants.EMAIL,
                ""
            ) || area != sharedPref.getStringPref(Constants.AREA, "")
                    || location != sharedPref.getStringPref(Constants.LOCATION_USER, ""))
        ) {
            sharedPref.putStringPref(Constants.NAME_BUTTON_MAIL, "Edit data")
            viewBinding.iotMoreInfoButtonText.text =
                sharedPref.getStringPref(Constants.NAME_BUTTON_MAIL, "")
        }
    }


    override fun onDetach() {
        super.onDetach()
        if (viewBinding.iotMoreInfoButtonText.text == "next" && (phone != sharedPref.getStringPref(
                Constants.PHONE,
                ""
            )
                    || email != sharedPref.getStringPref(
                Constants.EMAIL,
                ""
            ) || area != sharedPref.getStringPref(Constants.AREA, "")
                    || location != sharedPref.getStringPref(Constants.LOCATION_USER, ""))
        ) {
            sharedPref.putStringPref(Constants.NAME_BUTTON_MAIL, "Edit data")
            viewBinding.iotMoreInfoButtonText.text =
                sharedPref.getStringPref(Constants.NAME_BUTTON_MAIL, "")
        }
    }

    private fun sendMail(user_data: User_Data_Mail) {
        if (sharedPref.getBoolPref(Constants.ENABLE) && sharedPref.getIntPref(Constants.COUNTER) >= 0) {

            var retrofit = Retrofit.Builder()
                .baseUrl("http://newweb19992022-001-site1.ftempurl.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            var mail = retrofit.create(Mailing::class.java)
            var call = mail.sendmail(user_data)
            call.enqueue(object : Callback<User_Data_Mail> {
                override fun onResponse(
                    call: Call<User_Data_Mail>,
                    response: Response<User_Data_Mail>
                ) {
                    Toast.makeText(
                        context,
                        "Operation completed successfully, wait for a reply",
                        Toast.LENGTH_LONG
                    ).show()

                }

                override fun onFailure(call: Call<User_Data_Mail>, t: Throwable) {
                    Toast.makeText(context, "The operation failed, try again", Toast.LENGTH_LONG)
                        .show()
                }

            })

            if (sharedPref.getStringPref(Constants.NAME_BUTTON_MAIL,"")=="send request") {
                sharedPref.putIntPref(Constants.COUNTER, count - 1)

                sharedPref.putStringPref(Constants.NAME_BUTTON_MAIL,"Next")
                viewBinding.iotMoreInfoButtonText.text = sharedPref.getStringPref(Constants.NAME_BUTTON_MAIL,"")
            }
            if (sharedPref.getStringPref(Constants.NAME_BUTTON_MAIL,"") == "Edit data"){
                sharedPref.putIntPref(Constants.COUNTER, count - 1)
                sharedPref.putBoolPref(Constants.ENABLE,false)

                sharedPref.putStringPref(Constants.NAME_BUTTON_MAIL,"Wait")
                viewBinding.iotMoreInfoButtonText.text = sharedPref.getStringPref(Constants.NAME_BUTTON_MAIL,"")
            }

        }

        if (sharedPref.getBoolPref(Constants.ENABLE) && sharedPref.getIntPref(Constants.COUNTER) == 0) {

            sharedPref.putStringPref(Constants.NAME_BUTTON_MAIL, "Wait")
            viewBinding.iotMoreInfoButtonText.text =
                sharedPref.getStringPref(Constants.NAME_BUTTON_MAIL, "")
        }

    }
    override fun onResume() {
        super.onResume()
        viewBinding.fragmentIotMoreInfoPhoneNumber.setText(sharedPref.getStringPref(Constants.PHONE, ""))
        viewBinding.fragmentIotMoreInfoEmail.setText(sharedPref.getStringPref(Constants.EMAIL, ""))
        viewBinding.fragmentIotMoreInfoArea.setText(sharedPref.getStringPref(Constants.AREA, ""))
        viewBinding.fragmentIotMoreInfoLocation.setText(sharedPref.getStringPref(Constants.LOCATION_USER, ""))
//        viewBinding.iotMoreInfoButtonText.text = sharedPref.getStringPref(Constants.NAME_BUTTON_MAIL, "")
    }
}

