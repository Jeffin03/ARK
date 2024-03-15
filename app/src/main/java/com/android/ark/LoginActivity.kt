package com.android.ark

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.android.ark.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)

        binding.signupBtn.setOnClickListener {
            val intent= Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        val sharedPref = getSharedPreferences("pref", MODE_PRIVATE)


        sharedPref.getString("name", null)

        val loginButton = binding.submitBtn
        loginButton.setOnClickListener {
            val emailSharedPref = sharedPref.getString("name", null)
            val passwordSharedPref = sharedPref.getString("password",null)

            val emailText = binding.textUsername
            val passwordText = binding.textPassword

            if(emailSharedPref == emailText.text.toString() && passwordSharedPref == passwordText.text.toString()){
                Log.i("Login: ","User successfully logged In")
                val intent = Intent(this,HomeActivity::class.java)
                startActivity(intent)
            }else{
                passwordText.error = "Incorrect Username or Password"
            }
        }

    }
}