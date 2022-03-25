package com.graduation.farmerfriend.control

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.graduation.farmerfriend.R
import com.graduation.farmerfriend.databinding.FragmentControlContainerBinding


class ControlContainerFragment : Fragment() {

    private lateinit var viewBinding : FragmentControlContainerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentControlContainerBinding.inflate(inflater, container, false)


        return viewBinding.root
    }

}