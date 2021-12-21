package com.example.applicationkotlin

import android.app.Application
import android.util.Log

class TwitterCloneApplication : Application() {
    private val TAG: String = TwitterCloneApplication::class.java.simpleName
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "TwitterCloneApplication onCreate")
    }
}