package com.android.ark

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class UploadActivity : AppCompatActivity() {
    private val REQUEST_CODE = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_upload)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val buttonUpload = findViewById<Button>(R.id.button3)
        val textViewFileCount = findViewById<TextView>(R.id.textview_file_count)
        buttonUpload.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "*/*" // Change this to specific MIME type if needed
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true) // Allow multiple files to be selected
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            val textViewFileCount = findViewById<TextView>(R.id.textview_file_count)
            val clipData = data?.clipData
            if (clipData != null) {
                // Multiple files selected
                textViewFileCount.text = "Files selected: ${clipData.itemCount}"
            } else {
                // Single file selected
                textViewFileCount.text = "Files selected: 1"
            }
        }
    }
}