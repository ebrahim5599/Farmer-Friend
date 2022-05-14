package com.graduation.farmerfriend.control.iot_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.google.firebase.database.*
import com.graduation.farmerfriend.R
import com.graduation.farmerfriend.constants.Constants
import com.graduation.farmerfriend.control.iot_fragments.hasIoTSystem.Data_HasIoT
import com.graduation.farmerfriend.control.iot_fragments.hasIoTSystem.HasIoTSystem
import com.graduation.farmerfriend.databinding.FragmentIotWaitingCodeBinding
import com.graduation.farmerfriend.sharedPreferences.SharedPref
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class IotWaitingCodeFragment : Fragment() {

    private lateinit var viewBinding: FragmentIotWaitingCodeBinding
    private lateinit var sharedPref: SharedPref
//    private lateinit var iotWaitingCodeFragment : IotWaitingCodeFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentIotWaitingCodeBinding.inflate(inflater, container, false)
        sharedPref = SharedPref(requireContext(), Constants.MAIN_SHARED_PREFERENCES)

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

           if (viewBinding.iotWaitingCodeText.text.toString() == "5"){
               var data = Data_HasIoT("/hasIotSystem","replace",true)
               Check(data)
           }
           else{
               Toast.makeText(context,"The code is wrong", Toast.LENGTH_LONG).show()
           }

        }

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.iotWaitingCodeButton.setOnClickListener {

            when {

                viewBinding.iotWaitingCodeText.text.toString() == sharedPref.getStringPref(
                    Constants.USER_ID, ""
                ) -> {
                    val rootRef = FirebaseDatabase.getInstance().reference

                    rootRef.child(sharedPref.getStringPref(Constants.USER_ID, ""))
                        .addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                if (snapshot.value == null) {
                                    Toast.makeText(
                                        requireContext(),
                                        "your IOT system will be available soon",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
//                                Toast.makeText(requireContext(),"has iot ",Toast.LENGTH_SHORT).show()
                                    //ToDo: call api to set "hasIOTSystem" to true
                                    Navigation.findNavController(view)
                                        .navigate(R.id.next_action, null)
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {}
                        })
                }
                else ->
                    Toast.makeText(
                        requireContext(),
                        "please enter the code correctly",
                        Toast.LENGTH_SHORT
                    ).show()


            }
        }
    }

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