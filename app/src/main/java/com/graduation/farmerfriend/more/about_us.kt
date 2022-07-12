package com.graduation.farmerfriend.more

import androidx.navigation.Navigation.findNavController
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.graduation.farmerfriend.R
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.graduation.farmerfriend.databinding.FragmentAboutUsBinding

class about_us : Fragment() {
    var viewBinding: FragmentAboutUsBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentAboutUsBinding.inflate(inflater, container, false)
        val view: View = viewBinding!!.root
        viewBinding!!.fragmentAboutUsTextviewcontent.text =
            """
            Our project is smart agriclture system where we take care of agriculture where we solve the water problem and reduce labor, follow up plants, market crops and agricultural products and communicate with experts. 
            
            Our app is the Farm Friend app used to help the farmer. Our app is connected to our IoT system to help the farmer who applied our IoT system to control his farm remotely. in our app, we also have e-commerce to help the farmer to buy his needs from tools, fertilizers, and seeds from the same app. It also provides the farmer with an AI feature to detect any disease in the plant by taking a photo of the plant's leaf.
            
            Mobile Team :
            Eng / Ebrahim Abd-Elaziz
            Eng / Ahmed Gamal Ward
            Eng / Fatma Hassan 
            
            Web Team :
            Eng / Hanaa Taha
            Eng / Manar Abdallah
            
            """.trimIndent()
        return view
    }
}