package com.graduation.farmerfriend.camera;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.bumptech.glide.Glide;
import com.graduation.farmerfriend.databinding.ActivityCameraResultBinding;

public class CameraResultActivity extends AppCompatActivity {

    private ActivityCameraResultBinding binding;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCameraResultBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.cameraFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent n = getIntent();
        path = n.getStringExtra("PATH");
//        Toast.makeText(getBaseContext(), "path is: " + path, Toast.LENGTH_SHORT).show();

//        Picasso.get().load(path).into(binding.previewImage);
//        Picasso.get().load(path).rotate(90).(binding.toolbarImage);
        Glide.with(CameraResultActivity.this).load(path).into(binding.toolbarImage);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == Activity.RESULT_OK && requestCode == 444 && data != null){
//            binding.previewImage.setImageBitmap((Bitmap) data.getExtras().get("Pictures/Farmer Friend"));
//        }
//    }

//    private void getImage(){
//        File imgFile = new  File(path);
////        File imgFile = new  File("/external_files/Pictures/Farmer Friend/");
//        if(imgFile.exists()){
//            Bitmap myBitmap = BitmapFactory.decodeFile(path);
//            binding.previewImage.setImageBitmap(myBitmap);
//            Toast.makeText(getBaseContext(), "exist", Toast.LENGTH_SHORT).show();
//        }else
//            Toast.makeText(getBaseContext(), "not exist", Toast.LENGTH_SHORT).show();
//    }

//    private fun getImage() {
//        val imgFile = File(message)
//        if (imgFile.exists()) {
//            val myBitmap =
//                    BitmapFactory.decodeFile(imgFile.absolutePath + "/2022-03-03-01-12-01-745.jpg")
////            Toast.makeText(context, imgFile.absolutePath + "2022-03-03-01-12-01-745.jpg", Toast.LENGTH_LONG).show()
//            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
//            viewBinding.perviewLastImage.setImageBitmap(myBitmap)
//        } else
//            Toast.makeText(context, "not exist", Toast.LENGTH_LONG).show()
//
//    }
}