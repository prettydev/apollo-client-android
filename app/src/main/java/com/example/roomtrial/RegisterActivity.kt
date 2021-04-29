package com.example.roomtrial

import android.R.attr
import android.R.attr.*
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class RegisterActivity : AppCompatActivity() {
    private lateinit var ivPhotoProfile: ImageView
    private lateinit var etNama: EditText
    private lateinit var etEmail: EditText
    private lateinit var etUmur: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnUploadImage: Button
    private lateinit var btnRegister: Button
    private val pickImage = 100
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        ivPhotoProfile = findViewById(R.id.ivPhotoProfile)
        etNama = findViewById(R.id.etNama)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etUmur = findViewById(R.id.etUmur)
        btnRegister = findViewById(R.id.btnRegister)
        btnUploadImage = findViewById(R.id.btnUploadImage)


        var userDb = UserDb.getInstance(this)

        btnRegister.setOnClickListener {
            imageUri?.path
            GlobalScope.launch {
                val user = UserEntity(
                    null,
                    etNama.text.toString(),
                    etEmail.text.toString(),
                    etUmur.text.toString().toInt(),
                    etPassword.text.toString(),
                    imageUri?.path.toString()
                )
                var insertUser = userDb?.registerDao()?.registerUser(user)

                GlobalScope.launch(Dispatchers.Main){
                    if (insertUser == 1.toLong()) {
                        startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                    } else
                        Toast.makeText(
                            this@RegisterActivity,
                            "Register Gagal, isi semua form",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                }
            }

        }

        btnUploadImage.setOnClickListener {
            CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(this);
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode === RESULT_OK) {
                val resultUri = result.uri
                val path = result.uri.path
                Log.d("PATH",path.toString())
                imageUri = resultUri
                ivPhotoProfile.setImageURI(resultUri)
            } else if (resultCode === CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
            }
        }
    }

}

