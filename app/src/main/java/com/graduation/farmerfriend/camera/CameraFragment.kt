package com.graduation.farmerfriend.camera


import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.Image
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.DisplayMetrics
import android.util.Log
import android.util.Rational
import android.util.Size
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.graduation.farmerfriend.databinding.FragmentCameraBinding
import com.graduation.farmerfriend.ml.Model
import com.graduation.farmerfriend.permissions.FragmentPermissionHelper
import com.graduation.farmerfriend.permissions.FragmentPermissionInterface
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.*
import java.nio.ByteBuffer
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class CameraFragment : Fragment() {

    private lateinit var viewBinding: FragmentCameraBinding
    private var imageCapture: ImageCapture? = null
    private lateinit var cameraExecutor: ExecutorService
    private var message: String? = null
    private var finalBitmap: Bitmap? = null
    private var img_gallery: Bitmap? = null
    private var gallery: String? = null
    private var result: String? = null
    private var camera: Boolean = false
    private var image: Boolean = false
    private var max: Float = 0.0f
    private var disease: String? = null
    private var MY_CAMERA_PERMISSION_CODE = 100


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        viewBinding = FragmentCameraBinding.inflate(inflater, container, false)

        // Request camera permissions
        if (allPermissionsGranted()) {
            startCamera()
        } else {
//            ActivityCompat.requestPermissions(
//                requireActivity(), REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS
//            )

            val fragmentCameraPermissionHelper = FragmentPermissionHelper()
            fragmentCameraPermissionHelper.startPermissionRequest(
                this, Manifest.permission.CAMERA
            ) {
                run {
                    if (it) {
                        startCamera()
                    } else {
                        // Explain to the user that the feature is unavailable because the
                        // features requires a permission that the user has denied. At the
                        // same time, respect the user's decision. Don't link to system
                        // settings in an effort to convince the user to change their
                        // decision.
                        view?.let { it1 -> Navigation.findNavController(it1).popBackStack() }

                    }
                }
            }

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

            if (camera) {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                    val fragmentPermissionHelper = FragmentPermissionHelper()
                    fragmentPermissionHelper.startPermissionRequest(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        FragmentPermissionInterface {
                            run {
                                if (it) {
                                    saveImage()
                                } else {
                                    // Explain to the user that the feature is unavailable because the
                                    // features requires a permission that the user has denied. At the
                                    // same time, respect the user's decision. Don't link to system
                                    // settings in an effort to convince the user to change their
                                    // decision.
                                    view?.let { it1 ->
                                    }

                                }
                            }
                        })
                } else
                    saveImage()
//                finalBitmap?.let { imageProcessing(it) }
            } else if (image) {
                img_gallery?.let { it1 -> imageProcessing(it1) }
            }

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

    override fun onResume() {
        super.onResume()
        viewBinding.previewImageNow.visibility = View.GONE
        viewBinding.previewCameraNow.visibility = View.VISIBLE
    }

//        private fun savePhoto() {
//        // Get a stable reference of the modifiable image capture use case
//        val imageCapture = imageCapture ?: return
//
//        // Create time stamped name and MediaStore entry.
//        val name = SimpleDateFormat(FILENAME_FORMAT, Locale.US)
//            .format(System.currentTimeMillis())
//        val contentValues = ContentValues().apply {
//            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
//            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
//            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
//                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/Farmer Friend")
//            }
//        }
//
//
//        // Create output options object which contains file + metadata
//        val outputOptions = ImageCapture.OutputFileOptions
//            .Builder(
//                context?.contentResolver!!,
//                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                contentValues
//            )
//            .build()
//
//
//        // Set up image capture listener, which is triggered after photo has
//        // been taken
//        imageCapture.takePicture(
//            outputOptions,
//            ContextCompat.getMainExecutor(requireContext()),
//            object : ImageCapture.OnImageSavedCallback {
//                override fun onError(exc: ImageCaptureException) {
//                    Log.e(TAG, "Photo capture failed: ${exc.message}", exc)
//                }
//
//                override fun
//                        onImageSaved(output: ImageCapture.OutputFileResults) {
//                    val msg = "Photo capture succeeded: ${output.savedUri}"
//                    message = output.savedUri.toString()
//
//
//                    finalBitmap?.let { imageProcessing(it) }
//
//
////                    viewBinding.previewImageNow.visibility = View.VISIBLE
////                    viewBinding.previewCameraNow.visibility = View.GONE
////                    Glide.with(this@CameraFragment).load(message).into(viewBinding.showImageHere)
//                    Log.d(TAG, msg)
//                }
//            }
//        )
//    }


    private fun imageProcessing(bitmap: Bitmap) {

        val bitmap = bitmap.let { it1 -> Bitmap.createScaledBitmap(it1, 224, 224, true) }

        val model = context?.let { it1 -> Model.newInstance(it1) }

        val inputFeature0 =
            TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.FLOAT32)

        val tensorImage = TensorImage(DataType.FLOAT32)
        tensorImage.load(bitmap)
        val byteBuffer: ByteBuffer = tensorImage.buffer

        inputFeature0.loadBuffer(byteBuffer)

        val outputs = model?.process(inputFeature0)
        val outputFeature0 = outputs?.outputFeature0AsTensorBuffer

        val name = arrayOf<String>(
            "Apple",
            "Apple",
            "Apple",
            "Apple",
            "Corn(maize)",
            "Corn(maize)",
            "Corn(maize)",
            "Corn(maize)",
            "Grape",
            "Grape",
            "Grape",
            "Grape",
            "Orange",
            "Peach",
            "Peach",
            "Pepper(bell)",
            "Pepper(bell)",
            "Potato",
            "Potato",
            "Potato",
            "Strawberry",
            "Strawberry",
            "Tomato",
            "Tomato",
            "Tomato",
            "Tomato",
            "Tomato",
            "Tomato",
            "Tomato",
            "Tomato",
            "Tomato",
            "Tomato"
        )

        val disease_array = arrayOf<String>(
            "Apple_scab",
            "Black_rot",
            "Cedar_apple_rust",
            "healthy",
            "Cercospora_leaf_spot Gray_leaf_spot",
            "Common_rust",
            "healthy",
            "Northern_Leaf_Blight",
            "Black_rot",
            "Esca_(Black_Measles)",
            "healthy",
            "Leaf_blight_(Isariopsis_Leaf_Spot)",
            "Haunglongbing_(Citrus_greening)",
            "Bacterial_spot",
            "healthy",
            "Bacterial_spot",
            "healthy",
            "Early_blight",
            "healthy",
            "Late_blight",
            "healthy",
            "Leaf_scorch",
            "Bacterial_spot",
            "Early_blight",
            "healthy",
            "Late_blight",
            "Leaf_Mold",
            "Septoria_leaf_spot",
            "Spider_mites Two-spotted_spider_mite",
            "Target_Spot",
            "Tomato_mosaic_virus",
            "Tomato_Yellow_Leaf_Curl_Virus"
        )

        max = outputFeature0!!.floatArray[0]
        var i: Int = 0
        var index: Int = 0
        for (i in 1..31) {
            if (outputFeature0.floatArray[i] > max) {
                max = outputFeature0.floatArray[i]
                index = i
            }
        }
        result = name[index]
        disease = disease_array[index]
        max = max * 100

        model.close()
        goToActivity()

//        viewBinding.previewImageNow.visibility = View.GONE
//        viewBinding.previewCameraNow.visibility = View.VISIBLE
//        startCamera()
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
//                Toast.makeText(requireContext(), "", Toast.LENGTH_SHORT).show()
//                viewBinding.showImageHere.setImageBitmap(finalBitmap)
                Glide.with(this@CameraFragment).load(finalBitmap).into(viewBinding.showImageHere)
                image.close()
            }

            override fun onError(exception: ImageCaptureException) {
                super.onError(exception)
                Toast.makeText(context, exception.toString(), Toast.LENGTH_SHORT).show()
                Log.e("TAG", "Photo capture failed: $exception", exception)
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
        val cameraProviderFuture = requireContext().let { ProcessCameraProvider.getInstance(it) }

        cameraProviderFuture.addListener({
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(viewBinding.viewFinder.surfaceProvider)
                }
            val metrics =
                DisplayMetrics().also { viewBinding.viewFinder.display.getRealMetrics(it) }
            val screenSize = Size(metrics.widthPixels, metrics.heightPixels)
            imageCapture = ImageCapture.Builder()
                .setTargetResolution(screenSize)
                .setTargetRotation(viewBinding.viewFinder.display.rotation)
                .build()

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
        requireActivity().let {
            val intent = Intent(it, CameraResultActivity::class.java)

            if (message?.isNotEmpty() == true) {
                intent.putExtra("PATH", message)
            } else if (gallery?.isNotEmpty() == true) {
                intent.putExtra("PATH", gallery)
            }
//            else if(finalBitmap != null){
//                var bStream  =  ByteArrayOutputStream()
//                finalBitmap?.compress(Bitmap.CompressFormat.PNG, 50, bStream)
//                val byteArray = bStream.toByteArray()
//                intent.putExtra("image", byteArray)
//            }

            intent.putExtra("result", result)
            intent.putExtra("disease", disease)
            intent.putExtra("max", max)
            it.startActivity(intent)

        }
    }


    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            requireActivity().baseContext, it
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun saveImage(): Boolean {
        val name = SimpleDateFormat(FILENAME_FORMAT, Locale.US)
            .format(System.currentTimeMillis())
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/Farmer Friend")
            }
        }
        var imageCollection: Uri? = null
        var resolver = requireActivity().contentResolver
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            imageCollection =
                MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
        } else {
            imageCollection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }
        var imageUri = resolver.insert(imageCollection, contentValues)
        try {
            var outputStream: OutputStream? = Objects.requireNonNull(imageUri)
                ?.let {
                    resolver.openOutputStream(it)
                }
            finalBitmap?.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            Objects.requireNonNull(outputStream)

//            Toast.makeText(requireContext(), "$imageUri", Toast.LENGTH_SHORT).show()
            message = imageUri.toString()
            finalBitmap?.let { imageProcessing(it) }
            return true
        } catch (e: java.lang.Exception) {
            Toast.makeText(requireContext(), "Image not saved \n${e}", Toast.LENGTH_SHORT).show()
        }
        return false
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