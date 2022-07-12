package com.graduation.farmerfriend.more

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import androidx.navigation.Navigation.findNavController
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.graduation.farmerfriend.R
import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.graduation.farmerfriend.constants.Constants
import com.graduation.farmerfriend.databinding.FragmentMoreBinding
import okhttp3.MediaType
import okhttp3.RequestBody

class MoreFragment : Fragment() {
    var binding: FragmentMoreBinding? = null
    lateinit var sharedPreferences: SharedPreferences
    var mutableLiveDataForName: MutableLiveData<String>? = null

    override
    fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoreBinding.inflate(inflater, container, false)

        sharedPreferences = requireActivity().getSharedPreferences(Constants.MAIN_SHARED_PREFERENCES, Context.MODE_PRIVATE)
        mutableLiveDataForName = MutableLiveData()

        if (sharedPreferences.getBoolean(
                Constants.LOGGED_IN,
                false
            )
        ) binding!!.fragmentMoreTextviewLogin!!.visibility = View.GONE
        binding!!.fragmentMoreTextviewLogin!!.paintFlags =
            binding!!.fragmentMoreTextviewLogin!!.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        binding!!.fragmentMoreImageviewUserimage.setImageResource(R.mipmap.ic_launcher)
        binding!!.fragmentMoreImageviewUserimage.setOnClickListener {
            val builder = AlertDialog.Builder(activity)
            builder.setItems(R.array.load_photo, object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {

                    if (which == 0) {

                    }
                    if (which == 1) {


                    }
                }})
            builder.create()
            builder.show()

        }
        binding!!.fragmentMoreTextviewName.text = sharedPreferences.getString(
            Constants.FIRST_AND_LAST_NAME, "Anonymous User"
        )
        mutableLiveDataForName!!.observe(viewLifecycleOwner) { s ->
            binding!!.fragmentMoreTextviewName.text = s
        }
        binding!!.fragmentMoreTextviewStore.setOnClickListener { view ->
            findNavController(view).navigate(
                R.id.storeFragment
            )
        }
        binding!!.fragmentMoreTextviewCommunity.setOnClickListener {
            val packageManager = requireActivity().packageManager
            val intent = packageManager.getLaunchIntentForPackage("com.example.farmer_club")
            if (intent != null) {
                startActivity(intent)
            } else {
                val google_play = Intent(Intent.ACTION_VIEW)
                google_play.data =
                    Uri.parse("http://play.google.com/store/apps/details?id=com.example.farmer_club")
                startActivity(google_play)
            }
        }
        binding!!.fragmentMoreTextviewAbout.setOnClickListener { view ->
            findNavController(view).navigate(
                R.id.about_us
            )
        }
        binding!!.fragmentMoreTextviewLogout.setOnClickListener {
            Toast.makeText(context, "Logout", Toast.LENGTH_SHORT).show()
            sharedPreferences.edit().putBoolean(Constants.LOGGED_IN, false).apply()
            sharedPreferences.edit().putString(Constants.FIRST_AND_LAST_NAME, "Anonymous User")
                .apply()
            sharedPreferences.edit().putString(Constants.USER_ID, "").apply()
            mutableLiveDataForName!!.setValue("Anonymous User")
            TransitionManager.beginDelayedTransition(binding!!.root, AutoTransition())
            binding!!.fragmentMoreTextviewLogin!!.visibility = View.VISIBLE
            sharedPreferences.edit().putBoolean(Constants.HAS_IOT_SYSTEM, false).apply()
        }
        binding!!.fragmentMoreTextviewLogin!!.setOnClickListener { view ->
            findNavController(view).navigate(
                R.id.action_moreFragment_to_loginFragment
            )
        }
        return binding!!.root
    }

    fun upload_photo(){
      //  RequestBody requestFile = RequestBody.create(MediaType.parse("mutlipart/form-data"),file);
    }
}