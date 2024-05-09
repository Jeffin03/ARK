package com.android.ark

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.android.ark.databinding.ActivityLoginBinding

import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Navigation to Register Activity
        binding.signupBtn.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        val loginButton = binding.submitBtn
        loginButton.setOnClickListener {
            val emailText = binding.textUsername.text.toString()
            val passwordText = binding.textPassword.text.toString()

            auth.signInWithEmailAndPassword(emailText, passwordText)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success
                        Log.i("Login: ", "User successfully logged In")
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("Login: ", "signInWithEmail:failure", task.exception)
                        binding.textPassword.error = "Authentication failed."
                    }
                }
        }
    }
}