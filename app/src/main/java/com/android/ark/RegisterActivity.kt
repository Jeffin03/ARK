package com.android.ark

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.android.ark.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)

//  screen navigation to Login Activity
        binding.loginBtn.setOnClickListener {
            val intent= Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val sharedPref = getSharedPreferences("pref", MODE_PRIVATE)
        val editor = sharedPref.edit()

        val registerButton = binding.submitBtn

        //onclick of Register button
        registerButton.setOnClickListener{
//            getting values from view using findViewByID
            val name = binding.textUsername
            val password = binding.textPassword
            val confirmPassword = binding.confirmPs

            //comparing password and confirmPassword
            if(password.text.toString() != confirmPassword.text.toString()){
                confirmPassword.error = "Password mismatch"
            }

            //saving multiple data in sharedPref
            editor.apply{
                putString("name", name.text.toString())
                putString("password", password.text.toString())
                apply()
            }

            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}