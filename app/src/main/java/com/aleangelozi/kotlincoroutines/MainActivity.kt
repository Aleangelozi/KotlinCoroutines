package com.aleangelozi.kotlincoroutines

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.aleangelozi.kotlincoroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // All coroutines inside GlobalScope will live as long
        // as the application (Main Thread) does. GlobalScope starts a new thread.
        GlobalScope.launch {

            Log.d(TAG, "Coroutine says hi from thread ${Thread.currentThread().name}")

        }

        Log.d(TAG, "Hi from thread ${Thread.currentThread().name}")
    }
}