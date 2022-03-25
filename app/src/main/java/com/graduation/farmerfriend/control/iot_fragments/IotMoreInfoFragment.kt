package com.graduation.farmerfriend.control.iot_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.graduation.farmerfriend.R
import com.graduation.farmerfriend.databinding.FragmentIotMoreInfoBinding

class IotMoreInfoFragment : Fragment() {

    private lateinit var viewBinding : FragmentIotMoreInfoBinding
    private lateinit var iotWaitingCodeFragment : IotWaitingCodeFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentIotMoreInfoBinding.inflate(inflater, container, false)

        iotWaitingCodeFragment = IotWaitingCodeFragment()
        viewBinding.iotMoreInfoButton.setOnClickListener {
            if (savedInstanceState == null) {
                val fragmentManager = requireActivity().supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(
                    R.id.registration_fragment_container,
                    iotWaitingCodeFragment
                )
                fragmentTransaction.commit()
            }
        }

        return viewBinding.root
    }

}