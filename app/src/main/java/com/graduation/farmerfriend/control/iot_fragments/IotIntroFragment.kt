package com.graduation.farmerfriend.control.iot_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.graduation.farmerfriend.R
import com.graduation.farmerfriend.databinding.FragmentIotIntroBinding

class IotIntroFragment : Fragment() {

    private lateinit var viewBinding : FragmentIotIntroBinding
    private lateinit var iotMoreInfoFragment: IotMoreInfoFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentIotIntroBinding.inflate(inflater, container, false)

        iotMoreInfoFragment = IotMoreInfoFragment()

        viewBinding.iotIntroButton.setOnClickListener {
            if (savedInstanceState == null) {
                val fragmentManager = requireActivity().supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(
                    R.id.registration_fragment_container,
                    iotMoreInfoFragment
                )
                fragmentTransaction.commit()
            }

        }

        return viewBinding.root
    }
}