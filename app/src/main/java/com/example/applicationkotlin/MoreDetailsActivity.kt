package com.example.applicationkotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MoreDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.more_details_activity)
        initView()
    }

    private fun initView() {

        val button = findViewById<Button>(R.id.bv3_id)
        button.setOnClickListener {
            openSplashPage()
        }
    }

    private fun openSplashPage() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}