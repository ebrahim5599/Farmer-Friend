package com.graduation.farmerfriend.control.iot_fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
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

    private lateinit var viewBinding : FragmentIotMoreInfoBinding
    private lateinit var iotWaitingCodeFragment : IotWaitingCodeFragment
    private lateinit var sharedPref: SharedPref



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentIotMoreInfoBinding.inflate(inflater, container, false)

        sharedPref = SharedPref(requireContext(), Constants.MAIN_SHARED_PREFERENCES)
        viewBinding.iotMoreInfoButtonText.text = sharedPref.getStringPref(Constants.NAME_BUTTON_MAIL,"")
//        iotWaitingCodeFragment = IotWaitingCodeFragment()
//        viewBinding.iotMoreInfoButton.setOnClickListener {
//            if (savedInstanceState == null) {
//                val fragmentManager = requireActivity().supportFragmentManager
//                val fragmentTransaction = fragmentManager.beginTransaction()
//                fragmentTransaction.replace(
//                    R.id.registration_fragment_container,
//                    iotWaitingCodeFragment
//                )
//                fragmentTransaction.commit()
//            }
//        }

        val options = navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
        }

        viewBinding.iotMoreInfoButton.setOnClickListener {

            var phone = viewBinding.fragmentIotMoreInfoPhoneNumber.text.toString()
            var email = viewBinding.fragmentIotMoreInfoEmail.text.toString()
            var area = viewBinding.fragmentIotMoreInfoArea.text.toString()
            var location = viewBinding.fragmentIotMoreInfoLocation.text.toString()
            var Body = phone+"\n"+email +"\n"+area +"\n"+ location


            var username =  sharedPref.getStringPref(Constants.USER_NAME, "")

            var user_mail = User_Data_Mail(username, Body)

            findNavController().navigate(R.id.next_action, null, options)

            Mail(user_mail)

        }

//        viewBinding.iotMoreInfoButton.setOnClickListener {
////            findNavController().navigate(R.id.iotWaitingCodeFragment, null, options)
//            Navigation.createNavigateOnClickListener(R.id.next_action, null)
//        }

        return viewBinding.root
    }

    fun Mail(user_data: User_Data_Mail) {

        if (sharedPref.getBoolPref(Constants.ENABLE) && sharedPref.getIntPref(Constants.COUNTER) > 0) {
            Log.d("Mail", "done")
            var retrofit = Retrofit.Builder()
                .baseUrl("http://teamweb992022-001-site1.htempurl.com/")
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

//                    Toast.makeText(context, "The operation failed, try again", Toast.LENGTH_LONG)
//                        .show()

                }

            })

            val count = sharedPref.getIntPref(Constants.COUNTER)
            sharedPref.putIntPref(Constants.COUNTER, count-1)
            Log.d("count",count.toString())
            sharedPref.putStringPref(Constants.NAME_BUTTON_MAIL,"Edit Data")
            viewBinding.iotMoreInfoButtonText.text = sharedPref.getStringPref(Constants.NAME_BUTTON_MAIL,"")
            if(sharedPref.getIntPref(Constants.COUNTER)== 0){
                sharedPref.putStringPref(Constants.NAME_BUTTON_MAIL,"Wait")
                viewBinding.iotMoreInfoButtonText.text = sharedPref.getStringPref(Constants.NAME_BUTTON_MAIL,"")

            }
        }
        else {
            sharedPref.putStringPref(Constants.NAME_BUTTON_MAIL,"Wait")
            viewBinding.iotMoreInfoButtonText.text = sharedPref.getStringPref(Constants.NAME_BUTTON_MAIL,"")
        }
    }
}

