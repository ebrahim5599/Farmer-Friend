package com.graduation.farmerfriend.control.iot_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.graduation.farmerfriend.R
import com.graduation.farmerfriend.control.iot_fragments.Mail.Mailing
import com.graduation.farmerfriend.control.iot_fragments.Mail.User_Data_Mail
import com.graduation.farmerfriend.databinding.FragmentIotMoreInfoBinding
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Callback
import retrofit2.Response

class IotMoreInfoFragment : Fragment() {

    private lateinit var viewBinding : FragmentIotMoreInfoBinding
    private lateinit var iotWaitingCodeFragment : IotWaitingCodeFragment
    var enable = true
    var count = 2
    private val TAG = "IotMoreInfoFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentIotMoreInfoBinding.inflate(inflater, container, false)

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

        var phone = viewBinding.fragmentIotMoreInfoPhoneNumber.text.toString()
        var email = viewBinding.fragmentIotMoreInfoEmail.text.toString()
        var area = viewBinding.fragmentIotMoreInfoArea.text.toString()
        var location = viewBinding.fragmentIotMoreInfoLocation.text.toString()
        var Subject = "Client"
        var Body = phone +"\n"+email +"\n"+area +"\n"+ location

        var user_mail = User_Data_Mail(Subject, Body)


        viewBinding.iotMoreInfoButton.setOnClickListener {

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

        if (enable && count > 0) {
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

                    Toast.makeText(context, "The operation failed, try again", Toast.LENGTH_LONG)
                        .show()

                }

            })
            viewBinding.iotMoreInfoButtonText.text = "Edit Data"
            count --

            if (count == 0 ){
                enable = false
                viewBinding.iotMoreInfoButtonText.text = "Wait"
            }
        }
        else{
            Toast.makeText(context, "Your request is to be viewed", Toast.LENGTH_LONG)
                .show()
        }
    }

}