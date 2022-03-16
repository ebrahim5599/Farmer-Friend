package com.graduation.farmerfriend.camera

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.graduation.farmerfriend.databinding.FragmentCameraBinding
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import android.R
import android.R.attr

import android.widget.ImageView

import android.R.attr.data
import android.annotation.SuppressLint
import android.provider.AlarmClock
import com.graduation.farmerfriend.ui.MainActivity
import android.provider.AlarmClock.EXTRA_MESSAGE

import android.widget.EditText
import java.io.File
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.graphics.drawable.Drawable
import android.media.ExifInterface
import android.media.Image
import android.provider.MediaStore.MediaColumns.ORIENTATION
import androidx.camera.core.*
import androidx.camera.view.video.OutputFileResults
import androidx.core.graphics.BitmapCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.squareup.picasso.Picasso
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.newSingleThreadContext


class CameraFragment : Fragment() {

    private lateinit var viewBinding: FragmentCameraBinding
    private var imageCapture: ImageCapture? = null
    private lateinit var cameraExecutor: ExecutorService
    private var message: String? = null
    private var finalBitmap : Bitmap? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        viewBinding = FragmentCameraBinding.inflate(inflater, container, false);

        // Request camera permissions
        if (allPermissionsGranted()) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(), REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS
            )
        }
        // Set up the listeners for take photo and video capture buttons
        viewBinding.imageCaptureButton.setOnClickListener {
            takePhoto()
        }

//        viewBinding.lastDetails.setOnClickListener{
////            goToActivity()
//            viewBinding.previewImageNow.visibility = View.VISIBLE
//            viewBinding.previewCameraNow.visibility = View.GONE
//        }

        viewBinding.retakeThePhoto.setOnClickListener {
            viewBinding.previewCameraNow.visibility = View.VISIBLE
            viewBinding.previewImageNow.visibility = View.GONE
            startCamera()
        }

        viewBinding.goToTheProcessing.setOnClickListener {
            savePhoto()
        }
        cameraExecutor = Executors.newSingleThreadExecutor()
        return viewBinding.root;
    }

    companion object {
        private const val TAG = "FarmerFriend"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS =
            mutableListOf(
                Manifest.permission.CAMERA,
            ).apply {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                    add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                }
            }.toTypedArray()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        cameraExecutor.shutdown()
    }

    override fun onResume() {
        super.onResume()
        viewBinding.previewImageNow.visibility = View.GONE
        viewBinding.previewCameraNow.visibility = View.VISIBLE
    }

    private fun savePhoto() {
        // Get a stable reference of the modifiable image capture use case
        val imageCapture = imageCapture ?: return

        // Create time stamped name and MediaStore entry.
        val name = SimpleDateFormat(FILENAME_FORMAT, Locale.US)
            .format(System.currentTimeMillis())
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/Farmer Friend")
            }
        }

        // Create output options object which contains file + metadata
        val outputOptions = ImageCapture.OutputFileOptions
            .Builder(
                context?.contentResolver!!,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            )
            .build()


        // Set up image capture listener, which is triggered after photo has
        // been taken
        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(requireContext()),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    Log.e(TAG, "Photo capture failed: ${exc.message}", exc)
                }

                override fun
                        onImageSaved(output: ImageCapture.OutputFileResults) {
                    val msg = "Photo capture succeeded: ${output.savedUri}"
                    message = output.savedUri.toString()
                    goToActivity()
//                    viewBinding.previewImageNow.visibility = View.VISIBLE
//                    viewBinding.previewCameraNow.visibility = View.GONE
//                    Glide.with(this@CameraFragment).load(message).into(viewBinding.showImageHere)
                    Log.d(TAG, msg)
                }
            }
        )
    }

    private fun takePhoto() {
        // Get a stable reference of the modifiable image capture use case
        val imageCapture = imageCapture ?: return

        imageCapture.takePicture(ContextCompat.getMainExecutor(requireContext()), object :
            ImageCapture.OnImageCapturedCallback() {
            @SuppressLint("UnsafeOptInUsageError")
            override fun onCaptureSuccess(image: ImageProxy) {
                super.onCaptureSuccess(image)
                viewBinding.previewImageNow.visibility = View.VISIBLE
                viewBinding.previewCameraNow.visibility = View.GONE
                finalBitmap = image.image?.toBitmap()?.let { rotateBitmap(it, 90f) }
//                viewBinding.showImageHere.setImageBitmap(finalBitmap)
                Glide.with(this@CameraFragment).load(finalBitmap).into(viewBinding.showImageHere)
            }

            override fun onError(exception: ImageCaptureException) {
                super.onError(exception)
                Toast.makeText(context, exception.toString(), Toast.LENGTH_SHORT).show()
                Log.e("TAG", "Photo capture failed: ${exception}", exception)
            }
        }
        )
    }

    private fun Image.toBitmap(): Bitmap {
        val buffer = planes[0].buffer
        buffer.rewind()
        val bytes = ByteArray(buffer.capacity())
        buffer.get(bytes)
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }

    private fun rotateBitmap(source: Bitmap, angle: Float): Bitmap? {
        val matrix = Matrix()
        matrix.postRotate(angle)
        return Bitmap.createBitmap(source, 0, 0, source.width, source.height, matrix, true)
    }

    private fun startCamera() {
        val cameraProviderFuture = context?.let { ProcessCameraProvider.getInstance(it) }

        cameraProviderFuture?.addListener({
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(viewBinding.viewFinder.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder().build()

            // Select back camera as a default
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture
                )

            } catch (exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(requireContext()))
    }


    private fun goToActivity() {
        activity?.let {
            val intent = Intent(it, CameraResultActivity::class.java)
            intent.putExtra("PATH", message)
            it.startActivity(intent)
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            activity?.baseContext!!, it
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults:
        IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera()
            } else {
                Toast.makeText(
                    context,
                    "Permissions not granted by the user.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}