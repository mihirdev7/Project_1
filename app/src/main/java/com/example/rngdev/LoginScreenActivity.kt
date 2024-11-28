package com.example.rngdev

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputEditText

class LoginScreenActivity : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        val emailInput = findViewById<TextInputEditText>(R.id.emailIdd)
        val passwordInput = findViewById<TextInputEditText>(R.id.passIdd)
        val loginButton = findViewById<AppCompatButton>(R.id.buttonId)

        dbHelper = DatabaseHelper(this)

        loginButton.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show()
            } else {
                val result = dbHelper.insertUser(email, password)
                if (result != -1L) {
                    Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,MainActivity::class.java)
                    intent.putExtra("Email",email)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Error during login!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}