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
import android.graphics.Bitmap
import android.graphics.Paint
import android.net.Uri
import android.provider.MediaStore
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.graduation.farmerfriend.constants.Constants
import com.graduation.farmerfriend.databinding.FragmentMoreBinding
import okhttp3.MediaType
import okhttp3.RequestBody
import java.io.IOException

class MoreFragment : Fragment() {
    var binding: FragmentMoreBinding? = null
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor:SharedPreferences.Editor
    var mutableLiveDataForName: MutableLiveData<String>? = null
    private var gallery: String? = null
    var image: Uri? = null

    override
    fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoreBinding.inflate(inflater, container, false)

        sharedPreferences = requireActivity().getSharedPreferences(Constants.MAIN_SHARED_PREFERENCES, Context.MODE_PRIVATE)
        mutableLiveDataForName = MutableLiveData()

        editor = sharedPreferences.edit()

        image = sharedPreferences.getString(Constants.photo,"")?.toUri()
       // Toast.makeText(context,image.toString(),Toast.LENGTH_SHORT).show()

        if (sharedPreferences.getString(Constants.photo,"") != "") {
            context?.let {
                Glide.with(it).load(image).into(binding!!.fragmentMoreImageviewUserimage)
            }
        }else{
            binding!!.fragmentMoreImageviewUserimage.setImageResource(R.mipmap.ic_launcher)
        }


        if (sharedPreferences.getBoolean(
                Constants.LOGGED_IN,
                false
            )
        ) binding!!.fragmentMoreTextviewLogin!!.visibility = View.GONE
        binding!!.fragmentMoreTextviewLogin!!.paintFlags =
            binding!!.fragmentMoreTextviewLogin!!.paintFlags or Paint.UNDERLINE_TEXT_FLAG
     //   binding!!.fragmentMoreImageviewUserimage.setImageResource(R.mipmap.ic_launcher)
        binding!!.fragmentMoreImageviewUserimage.setOnClickListener {
            val builder = AlertDialog.Builder(activity)
            builder.setItems(R.array.load_photo, object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {

                    if (which == 0) {
                        val intent = Intent(Intent.ACTION_GET_CONTENT)
                        intent.type = "image/*"
                        startActivityForResult(intent, 100)
                    }
                    if (which == 1) {


                        editor.putString(Constants.photo,"")
                        editor.apply()
                        binding!!.fragmentMoreImageviewUserimage.setImageResource(R.mipmap.ic_launcher)

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100) {


            val contentResolver = requireContext().contentResolver

            val uri = data?.data
            binding?.fragmentMoreImageviewUserimage?.setImageURI(uri)
            gallery = uri.toString()

            editor.putString(Constants.photo,gallery)
            editor.apply()

        }

    }
}