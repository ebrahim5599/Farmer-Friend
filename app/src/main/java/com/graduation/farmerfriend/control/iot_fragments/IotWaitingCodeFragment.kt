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
import com.graduation.farmerfriend.control.iot_fragments.hasIoTSystem.Data_HasIoT
import com.graduation.farmerfriend.control.iot_fragments.hasIoTSystem.HasIoTSystem
import com.graduation.farmerfriend.databinding.FragmentIotWaitingCodeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class IotWaitingCodeFragment : Fragment() {

    private lateinit var viewBinding : FragmentIotWaitingCodeBinding
//    private lateinit var iotWaitingCodeFragment : IotWaitingCodeFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentIotWaitingCodeBinding.inflate(inflater, container, false)


        val options = navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
        }


        viewBinding.iotWaitingCodeButton.setOnClickListener {
            findNavController().navigate(R.id.next_action, null, options)

           if (viewBinding.iotWaitingCodeText.text.toString() == "5" ){
               var data = Data_HasIoT("/hasIotSystem","replace",true)
               Check(data)
           }
           else{
               Toast.makeText(context,"The code is wrong", Toast.LENGTH_LONG).show()
           }

        }



//        viewBinding.iotWaitingCodeButton.setOnClickListener {
////            findNavController().navigate(R.id.controlFragment, null, options)
//            Navigation.createNavigateOnClickListener(R.id.next_action, null)
//        }

//        viewBinding.iotWaitingCodeButton.setOnClickListener {
//            val fragmentManager = requireActivity().supportFragmentManager
//                val fragmentTransaction = fragmentManager.beginTransaction()
//                fragmentTransaction.remove(this)
//                fragmentTransaction.commit()
//
////            binding.registrationFragmentContainer.setVisibility(View.GONE);
//
//        }
//        iotWaitingCodeFragment = IotWaitingCodeFragment()
//        viewBinding.iotWaitingCodeButton.setOnClickListener {
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

        return viewBinding.root
    }


//    override fun onBackPressed() {
//        val navigationController = viewBinding.navigation.findNavController()
//        if (navigationController.currentDestination?.id == R.id.homeFragment) {
//            finish()
//        } else {
//            super.onBackPressed()
//        }
//    }

    fun Check(data: Data_HasIoT) {

            var retrofit = Retrofit.Builder()
                .baseUrl("http://teamweb992022-001-site1.htempurl.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            var mail = retrofit.create(HasIoTSystem::class.java)

            var call = mail.Has_IoT("hfatma791@gmail.com",data)

            call.enqueue(object : Callback<Data_HasIoT> {
                override fun onResponse(
                    call: Call<Data_HasIoT>,
                    response: Response<Data_HasIoT>
                ) {
                    Toast.makeText(
                        context,
                        "Operation completed successfully",
                        Toast.LENGTH_LONG
                    ).show()

                }

                override fun onFailure(call: Call<Data_HasIoT>, t: Throwable) {

                    Toast.makeText(context, "The operation failed, try again", Toast.LENGTH_LONG)
                        .show()

                }

            })

    }
}