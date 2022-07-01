package com.graduation.farmerfriend.control

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.distinctUntilChanged
import androidx.navigation.Navigation
import com.graduation.farmerfriend.R
import com.graduation.farmerfriend.constants.Constants
import com.graduation.farmerfriend.databinding.FragmentControlContainerBinding
import com.graduation.farmerfriend.ecommerce_models.IOTStatus
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable


class ControlContainerFragment : Fragment() {

    private lateinit var viewBinding: FragmentControlContainerBinding
    private var loginStatus: Boolean = false
    private var hasIotSystem: Boolean = false
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var viewModel: ControlContainerViewModel
    private val TAG = "iotstatus"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentControlContainerBinding.inflate(inflater, container, false)
        sharedPreferences = requireActivity().getSharedPreferences(
            Constants.MAIN_SHARED_PREFERENCES,
            Context.MODE_PRIVATE
        )

        loginStatus = sharedPreferences.getBoolean(Constants.LOGGED_IN, false)
        viewModel = ViewModelProvider(requireActivity())[ControlContainerViewModel::class.java]


//        val options = navOptions {
//            anim {
//                enter = R.anim.slide_in_right
//                exit = R.anim.slide_out_left
//                popEnter = R.anim.slide_in_left
//                popExit = R.anim.slide_out_right
//            }
//        }

        viewBinding.goToFarm.setOnClickListener {


            val iotStatusSingleObserver: SingleObserver<IOTStatus> =
                object : SingleObserver<IOTStatus> {
                    override fun onSubscribe(d: Disposable) {}
                    override fun onSuccess(iotStatus: IOTStatus) {
                        Log.d(TAG, "onCreateView: ${iotStatus.hasIotSystem}")
                        hasIotSystem = iotStatus.hasIotSystem
                        sharedPreferences.edit().putBoolean(Constants.HAS_IOT_SYSTEM, hasIotSystem)
                            .apply()
                        requireActivity().runOnUiThread{
                            checkWhereToNavigate(requireView(), hasIotSystem)
                            //Your code to run in GUI thread here
                        } //public void run() {

                    }

                    override fun onError(e: Throwable) {
                        hasIotSystem = sharedPreferences.getBoolean(Constants.HAS_IOT_SYSTEM, false)
                        requireActivity().runOnUiThread{
                            checkWhereToNavigate(requireView(), hasIotSystem)
                            //Your code to run in GUI thread here
                        } //public void run() {

                        Log.d(TAG, "onCreateView: $hasIotSystem")
                    }
                }
            sharedPreferences.getString(Constants.USER_NAME, "")
                ?.let { it1 -> viewModel.checkIotStatus(it1) }
                ?.subscribe(iotStatusSingleObserver)
        }

        return viewBinding.root
    }

    internal fun checkWhereToNavigate(view: View, hasIotSystem: Boolean) {
        if (loginStatus && hasIotSystem) {
//            Navigation.createNavigateOnClickListener(
//                R.id.action_controlContainerFragment_to_controlFragment,
//                null
//            )
            Navigation.findNavController(view).navigate(
                R.id.action_controlContainerFragment_to_controlFragment
            )
        } else if (loginStatus && !hasIotSystem) {

            Navigation.findNavController(view).navigate(
                R.id.action_controlContainerFragment_to_iotIntroFragment
            )

        } else {

            Navigation.findNavController(view).navigate(
                R.id.next_action
            )
        }
    }

}