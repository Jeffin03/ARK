package com.android.ark

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.ark.databinding.ActivitySearchBinding // Import your generated binding class

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding // Declare binding variable

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySearchBinding.inflate(layoutInflater) // Initialize binding
        setContentView(binding.root) // Set the content view to the root of the binding

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val linearLayout = findViewById<LinearLayout>(R.id.linearLayout)
        val emptyTextView = findViewById<TextView>(R.id.emptyTextView)

        updateEmptyTextViewVisibility(linearLayout, emptyTextView)

        binding.imageView9.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun updateEmptyTextViewVisibility(linearLayout: LinearLayout, emptyTextView: TextView) {
        if (linearLayout.childCount == 1) { // Only the TextView is present
            emptyTextView.visibility = View.VISIBLE
        } else {
            emptyTextView.visibility = View.GONE
        }
    }
}