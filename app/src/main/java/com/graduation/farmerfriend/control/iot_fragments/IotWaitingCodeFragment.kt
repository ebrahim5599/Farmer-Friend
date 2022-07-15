package com.graduation.farmerfriend.control.iot_fragments

import android.os.Bundle
import android.util.Log
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
import com.graduation.farmerfriend.repos.EcommerceRepo
import com.graduation.farmerfriend.sharedPreferences.SharedPref
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList

class IotWaitingCodeFragment : Fragment() {

    private lateinit var viewBinding: FragmentIotWaitingCodeBinding
    private lateinit var sharedPref: SharedPref
    var TAG = "IotWaitingCode";
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


          // if (viewBinding.iotWaitingCodeText.text.toString() == "5" ){
               //findNavController().navigate(R.id.next_action, null, options)
//               var array_data = ArrayList<Data_HasIoT>()
//               var data = Data_HasIoT()
//               data.value = true
//               array_data.add(data)
//               Check(array_data)
//           }
//           else{
//               Toast.makeText(context,"The code is wrong", Toast.LENGTH_LONG).show()
//           }


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
                                    var array_data = ArrayList<Data_HasIoT>()
                                    var data = Data_HasIoT()
                                    data.value = true
                                    array_data.add(data)
                                    Check(array_data)
                                    Navigation.findNavController(view).navigate(R.id.next_action, null)
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

    fun Check(data: ArrayList<Data_HasIoT>) {
        var ecommerceRepo = EcommerceRepo.getInstance()
        var objectSingle = ecommerceRepo.editIotStatus(sharedPref.getStringPref(Constants.USER_NAME, ""),data)


        var observer: SingleObserver<Any> = object : SingleObserver<Any> {
            override fun onSubscribe(d: Disposable) {}
            override fun onSuccess(o: Any) {
                sharedPref.putBoolPref(Constants.HAS_IOT_SYSTEM,true)
                Log.d(TAG, "onSuccess: patching")
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, "onError: $e ")
            }
        }

        objectSingle.subscribe(observer)

    }
}

