package com.example.applicationkotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_activity)
        initView()
    }

    private fun initView() {
        val button = findViewById<Button>(R.id.bv2_id)
        button.setOnClickListener {
            openLastPage()
        }
    }

    private fun openLastPage() {
        val intent = Intent(this, MoreDetailsActivity::class.java)
        startActivity(intent)
    }
}