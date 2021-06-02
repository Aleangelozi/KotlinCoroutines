package com.aleangelozi.kotlincoroutines

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.aleangelozi.kotlincoroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.button.setOnClickListener {
            //Comment the next first line of code and uncomment the next second line of code
            //to observe the difference.

            lifecycleScope.launch {
            //GlobalScope.launch {
                while (true) {
                    delay(1000L)
                    Log.d(TAG, "is still running ...")
                }
            }

            GlobalScope.launch {
                delay(5000L)
                Intent(this@MainActivity, SecondActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }


    }
}