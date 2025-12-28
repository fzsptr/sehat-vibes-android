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
import androidx.lifecycle.lifecycleScope
import com.example.sehatvibes.R
import com.example.sehatvibes.activity.RegisterActivity
import com.example.sehatvibes.lib.ApiConfig
import com.example.sehatvibes.model.LoginRequest
import com.example.sehatvibes.model.LoginResponse
import com.example.sehatvibes.model.ResponseError
import com.example.sehatvibes.utils.TokenManager
import com.google.gson.Gson
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private var isPwVisible = false
    private lateinit var usernameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        usernameInput = findViewById(R.id.etUsrnmLogin)
        passwordInput = findViewById(R.id.etPwLogin)
        btnLogin = findViewById(R.id.btnLogin)
        val tvRegister: TextView = findViewById(R.id.tvRegister)
        val ivTogglePw: ImageView = findViewById(R.id.ivTogglePwLogin)

        setupPwToggle(passwordInput, ivTogglePw)

        btnLogin.setOnClickListener {
            login()
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
        val typeface = editText.typeface

        if(isVisible) {
            editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            imageView.setImageResource(R.drawable.ic_eye)
        } else {
            editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            imageView.setImageResource(R.drawable.ic_eye_off)
        }

        editText.typeface = typeface
        editText.setSelection(editText.text.length)
    }
    private fun login() {
        val username = usernameInput.text.toString().trim()
        val password = passwordInput.text.toString().trim()

        if(username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Username & Password harus diisi", Toast.LENGTH_SHORT).show()
            return
        }

        val request = LoginRequest(username, password)
        lifecycleScope.launch {
            try {
                val api = ApiConfig.getAuthApi(this@LoginActivity)
                val response = api.login(request)

                if(response.isSuccessful) {
                    val body = response.body()
                    val token = body?.data?.accessToken

                    if(token.isNullOrEmpty()) {
                        Toast.makeText(this@LoginActivity, "Unauthorized", Toast.LENGTH_SHORT).show()
                        return@launch
                    }

                    TokenManager(this@LoginActivity).saveToken(token)

                    Toast.makeText(this@LoginActivity, body.message, Toast.LENGTH_SHORT).show()
                } else {
                    val errorBody = response.errorBody()?.string()
                    val error = Gson().fromJson(errorBody, ResponseError::class.java)
                    Toast.makeText(this@LoginActivity, error.message, Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@LoginActivity, e.message ?: "Something wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }
}