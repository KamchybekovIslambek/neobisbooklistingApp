package com.example.booklisting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val search = findViewById<Button>(R.id.search)
        val editText = findViewById<EditText>(R.id.search_field)

        search.setOnClickListener {
            if (editText.text.toString().isEmpty()) {
                Toast.makeText(this, "Search field is empty", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, BookActivity::class.java)
                intent.putExtra("SearchBook", editText.text.toString())
                startActivity(intent)
            }
        }
    }
}