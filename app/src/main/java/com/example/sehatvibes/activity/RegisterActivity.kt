package com.example.sehatvibes.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.sehatvibes.R
import com.example.sehatvibes.lib.ApiConfig
import com.example.sehatvibes.model.RegisterRequest
import com.example.sehatvibes.model.ResponseError
import com.google.gson.Gson
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    private lateinit var usernameInput: EditText
    private lateinit var nameInput: EditText
    private lateinit var weightInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var confirmPwInput: EditText
    private lateinit var btnRegister: Button
    private lateinit var tvLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        usernameInput = findViewById(R.id.etUsrnmRegis)
        nameInput = findViewById(R.id.etName)
        weightInput = findViewById(R.id.etWeight)
        passwordInput = findViewById(R.id.etPwRegis)
        confirmPwInput = findViewById(R.id.etConfirmPw)
        btnRegister = findViewById(R.id.btnRegister)
        tvLogin = findViewById(R.id.tvLogin)

        btnRegister.setOnClickListener {
            register()
        }
    }

    private fun register() {
        val username = usernameInput.text.toString().trim()
        val name = nameInput.text.toString().trim()
        val password = passwordInput.text.toString().trim()
        val confirmPw = confirmPwInput.text.toString().trim()
        val weightText = weightInput.text.toString().trim()


        if(username.isEmpty() || name.isEmpty() || password.isEmpty() || weightText.isEmpty()) {
            Toast.makeText(this, "Semua kolom wajib diisi", Toast.LENGTH_SHORT).show()
            return
        }

        if(password != confirmPw) {
            Toast.makeText(this, "Password tidak sama", Toast.LENGTH_SHORT).show()
            return
        }

        val weight = weightText.toDoubleOrNull()
        if(weight == null) {
            Toast.makeText(this, "Weight harus berupa angka", Toast.LENGTH_SHORT).show()
            return
        }

        val request = RegisterRequest (
            username = username,
            password = password,
            name = name,
            weight = weight
        )

        lifecycleScope.launch {
            try {
                val response = ApiConfig.authApi.register(request)

                if (response.isSuccessful) {
                    val body = response.body()
                    Toast.makeText(this@RegisterActivity, body?.message ?: "Register berhasil", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    val errorBody = response.errorBody()?.string()
                    val error = Gson().fromJson(errorBody, ResponseError::class.java)
                    Toast.makeText(this@RegisterActivity, error.message, Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@RegisterActivity, e.message ?:  "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
            }
        }
    }
}