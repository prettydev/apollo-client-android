package com.example.roomtrial

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.ImageView
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var tvNama: TextView
    private lateinit var tvUmur: TextView
    private lateinit var tvEmail: TextView
    private lateinit var ivPhoto: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvNama = findViewById(R.id.tvNama)
        tvUmur = findViewById(R.id.tvUmur)
        tvEmail = findViewById(R.id.tvEmail)
        ivPhoto = findViewById(R.id.ivPhoto)
        var userDb = UserDb.getInstance(this)

        GlobalScope.launch {
            var getUser = userDb?.registerDao()?.getUser("admin", "admin")

            GlobalScope.launch(Dispatchers.Main) {
                tvNama.text = getUser?.nama
                tvUmur.text = getUser?.umur.toString()
                tvEmail.text = getUser?.email.toString()
                ivPhoto.setImageURI(Uri.parse(getUser?.image))

            }
        }

    }
}