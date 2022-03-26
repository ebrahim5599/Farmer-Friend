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

import android.media.Image
import android.net.Uri
import androidx.camera.core.*
import androidx.core.app.SharedElementCallback

import com.bumptech.glide.Glide
import com.graduation.farmerfriend.e_commerce.Category
import com.graduation.farmerfriend.ml.Model
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.IOException
import java.nio.ByteBuffer
import org.tensorflow.lite.support.image.TensorImage
import java.net.URI


class CameraFragment : Fragment() {

    private lateinit var viewBinding: FragmentCameraBinding
    private var imageCapture: ImageCapture? = null
    private lateinit var cameraExecutor: ExecutorService
    private var message: String? = null
    private var finalBitmap : Bitmap? = null
    private var img_gallery: Bitmap? = null
    private var gallery: String? = null
    private var result: String? = null
    private var camera: Boolean = false
    private var image:Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        viewBinding = FragmentCameraBinding.inflate(inflater, container, false)

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
            camera = true
            image = false
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

        viewBinding.imageGallryButton.setOnClickListener {
            viewBinding.previewImageNow.visibility = View.VISIBLE
            viewBinding.previewCameraNow.visibility = View.GONE
            image = true
            camera = false
            message = null
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, 100)

        }

        viewBinding.goToTheProcessing.setOnClickListener {

            if(camera){
                savePhoto()
            }else if (image){
                img_gallery?.let { it1 -> image_processing(it1) }
            }

            viewBinding.previewImageNow.visibility = View.GONE
            viewBinding.previewCameraNow.visibility = View.VISIBLE
        }

        cameraExecutor = Executors.newSingleThreadExecutor()
        return viewBinding.root
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

//    override fun onResume() {
//        super.onResume()
//        viewBinding.previewImageNow.visibility = View.GONE
//        viewBinding.previewCameraNow.visibility = View.VISIBLE
//    }

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

                    finalBitmap?.let { image_processing(it) }



//                    viewBinding.previewImageNow.visibility = View.VISIBLE
//                    viewBinding.previewCameraNow.visibility = View.GONE
//                    Glide.with(this@CameraFragment).load(message).into(viewBinding.showImageHere)
                    Log.d(TAG, msg)
                }
            }
        )
    }

    private fun image_processing(bitmap : Bitmap){

        var  bitmap = bitmap?.let { it1 -> Bitmap.createScaledBitmap(it1, 224, 224, true) }

        val model = context?.let { it1 -> Model.newInstance(it1) }

        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.FLOAT32)

        val tensorImage = TensorImage(DataType.FLOAT32)
        tensorImage.load(bitmap)
        val byteBuffer: ByteBuffer = tensorImage.buffer

        inputFeature0.loadBuffer(byteBuffer)

        val outputs = model?.process(inputFeature0)
        val outputFeature0 = outputs?.outputFeature0AsTensorBuffer

        result =
            """${outputFeature0!!.floatArray[0]}  ${outputFeature0.floatArray[1]}  ${outputFeature0.floatArray[2]}  ${outputFeature0.floatArray[3]}
                       ${outputFeature0.floatArray[4]}  ${outputFeature0.floatArray[5]}  ${outputFeature0.floatArray[6]}  ${outputFeature0.floatArray[7]}
                       ${outputFeature0.floatArray[8]}  ${outputFeature0.floatArray[9]}  ${outputFeature0.floatArray[10]}  ${outputFeature0.floatArray[11]}
                       ${outputFeature0.floatArray[12]}  ${outputFeature0.floatArray[13]}  ${outputFeature0.floatArray[14]}  ${outputFeature0.floatArray[15]}
                       ${outputFeature0.floatArray[16]}  ${outputFeature0.floatArray[17]}  ${outputFeature0.floatArray[18]}  ${outputFeature0.floatArray[19]}
                       ${outputFeature0.floatArray[20]}  ${outputFeature0.floatArray[21]}  ${outputFeature0.floatArray[22]}  ${outputFeature0.floatArray[23]}
                       ${outputFeature0.floatArray[24]}  ${outputFeature0.floatArray[25]}  ${outputFeature0.floatArray[26]}  ${outputFeature0.floatArray[27]}
                       ${outputFeature0.floatArray[28]}  ${outputFeature0.floatArray[29]}  ${outputFeature0.floatArray[30]}  ${outputFeature0.floatArray[31]}"""

        model.close()
        goToActivity()
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
                finalBitmap = image.image?.toBitmap()?.let { rotateBitmap(it, 0f) }
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

            if (message?.isNotEmpty() == true) {
                intent.putExtra("PATH", message)
            }else if (gallery?.isNotEmpty() == true){
                intent.putExtra("PATH", gallery)
            }
            intent.putExtra("result",result)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100) {


            val contentResolver = requireContext().contentResolver

            viewBinding.showImageHere.setImageURI(data!!.data)
            val uri = data.data
            gallery = uri.toString()
            try {
                img_gallery = MediaStore.Images.Media.getBitmap(contentResolver, uri)
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }

}
