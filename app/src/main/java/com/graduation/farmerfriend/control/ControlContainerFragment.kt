package com.graduation.farmerfriend.control

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.graduation.farmerfriend.R
import com.graduation.farmerfriend.databinding.FragmentControlContainerBinding


class ControlContainerFragment : Fragment() {

    private lateinit var viewBinding: FragmentControlContainerBinding
    private var loginStatus: Boolean = false
    private var hasIotSystem: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentControlContainerBinding.inflate(inflater, container, false)

//        val options = navOptions {
//            anim {
//                enter = R.anim.slide_in_right
//                exit = R.anim.slide_out_left
//                popEnter = R.anim.slide_in_left
//                popExit = R.anim.slide_out_right
//            }
//        }
        viewBinding.goToFarm.setOnClickListener(
            if (loginStatus && hasIotSystem) {
                Navigation.createNavigateOnClickListener(R.id.action_controlContainerFragment_to_controlFragment, null)
            }else if (loginStatus && !hasIotSystem) {
                Navigation.createNavigateOnClickListener(R.id.action_controlContainerFragment_to_iotIntroFragment, null)
            }else {
                Navigation.createNavigateOnClickListener(R.id.next_action, null)
            }
        )

        return viewBinding.root
    }
}