package com.example.sehatvibes.activity

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.sehatvibes.R
import com.example.sehatvibes.activity.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private var isPwVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val usernameInput: EditText = findViewById(R.id.etUsrnmLogin)
        val passwordInput: EditText = findViewById(R.id.etPwLogin)
        val btnLogin: Button = findViewById(R.id.btnLogin)
        val tvRegister: TextView = findViewById(R.id.tvRegister)
        val ivTogglePw: ImageView = findViewById(R.id.ivTogglePwLogin)

        setupPwToggle(passwordInput, ivTogglePw)

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
        }

        tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
    private fun setupPwToggle(editText: EditText, imageView: ImageView) {
        imageView.setOnClickListener {
            isPwVisible = !isPwVisible
            togglePwVisible(editText, imageView, isPwVisible)
        }
    }
    private fun togglePwVisible(editText: EditText, imageView: ImageView, isVisible: Boolean) {
        if(isVisible) {
            editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            imageView.setImageResource(R.drawable.ic_eye)
        } else {
            editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            imageView.setImageResource(R.drawable.ic_eye_off)
        }
    }
}