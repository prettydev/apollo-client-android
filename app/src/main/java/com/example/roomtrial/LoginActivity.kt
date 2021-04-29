package com.example.roomtrial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var etUserName : EditText
    private lateinit var etUserPass : EditText
    private lateinit var btnLogin : Button
    private lateinit var buatAkun : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin = findViewById(R.id.btnLogin)
        buatAkun = findViewById(R.id.tvBuatAkun)
        etUserName = findViewById(R.id.etUsername)
        etUserPass = findViewById(R.id.etUserPassword)

        var userDb = UserDb.getInstance(this)

        btnLogin.setOnClickListener{
            GlobalScope.launch{
                val user = userDb?.registerDao()?.getUser(etUserName.text.toString(),etUserPass.text.toString())

                runOnUiThread{
                    if (user != null) {
                        startActivity(Intent(this@LoginActivity,MainActivity::class.java))
                    } else {
                        Toast.makeText(this@LoginActivity,"Gagal Login", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }

        buatAkun.setOnClickListener{
            startActivity(Intent(this,RegisterActivity::class.java))
        }


    }
}