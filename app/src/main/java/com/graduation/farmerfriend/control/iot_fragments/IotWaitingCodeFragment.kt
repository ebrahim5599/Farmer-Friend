package com.graduation.farmerfriend.control.iot_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import com.google.firebase.database.*
import com.graduation.farmerfriend.R
import com.graduation.farmerfriend.constants.Constants
import com.graduation.farmerfriend.databinding.FragmentIotWaitingCodeBinding
import com.graduation.farmerfriend.sharedPreferences.SharedPref

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.iotWaitingCodeButton.setOnClickListener {

            when {

                viewBinding.iotWaitingCodeET.text.toString() == sharedPref.getStringPref(
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
    //    override fun onBackPressed() {
//        val navigationController = viewBinding.navigation.findNavController()
//        if (navigationController.currentDestination?.id == R.id.homeFragment) {
//            finish()
//        } else {
//            super.onBackPressed()
//        }
//    }
}