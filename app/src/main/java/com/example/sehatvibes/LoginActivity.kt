package com.example.sehatvibes

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val usernameInput: EditText = findViewById(R.id.etUsrnmLogin)
        val passwordInput: EditText = findViewById(R.id.etPwLogin)
        val btnLogin: Button = findViewById(R.id.btnLogin)
        val tvRegister: TextView = findViewById(R.id.tvRegister)

        btnLogin.setOnClickListener {
            val username = usernameInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Harap isi semua kolom", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show()
//
//                val intent = Intent(this, RegisterActivity::class.java)
//                intent.putExtra("USERNAME", username)
//                startActivity(intent)
//                finish()
//            }
            }

            tvRegister.setOnClickListener {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }

        }
    }
}