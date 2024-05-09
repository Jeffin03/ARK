package com.android.ark

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.android.ark.databinding.ActivityRegisterBinding

import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Screen navigation to Login Activity
        binding.loginBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val registerButton = binding.submitBtn

        // On click of Register button
        registerButton.setOnClickListener {
            val name = binding.textUsername.text.toString()
            val password = binding.textPassword.text.toString()

            auth.createUserWithEmailAndPassword(name, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign up success
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                    } else {
                        // If sign up fails, display a message to the user.
                        Log.w("Register: ", "createUserWithEmail:failure", task.exception)
                        binding.textPassword.error = "Authentication failed."
                    }
                }
        }
    }
}