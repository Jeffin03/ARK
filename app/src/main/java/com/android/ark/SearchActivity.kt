package com.android.ark

import FileAdapter
import FileData
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
import androidx.recyclerview.widget.RecyclerView
import com.android.ark.databinding.ActivitySearchBinding // Import your generated binding class
import com.google.firebase.firestore.FirebaseFirestore

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

        // Initialize RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        // Fetch data from Firestore and set the adapter
        val db = FirebaseFirestore.getInstance()
        db.collection("files").get().addOnSuccessListener { result ->
            val files = result.map { document ->
                document.toObject(FileData::class.java)
            }
            val adapter = FileAdapter(files)
            recyclerView.adapter = adapter
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