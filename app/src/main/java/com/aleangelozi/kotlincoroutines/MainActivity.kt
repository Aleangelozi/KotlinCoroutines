package com.aleangelozi.kotlincoroutines

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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

        // All coroutines inside GlobalScope will live as long
        // as the application (Main Thread) does. GlobalScope starts a new thread.
        GlobalScope.launch {

            // delay() will only suspend the current coroutine, not the whole thread.
            delay(3000)
            Log.d(TAG, "Coroutine says hi from thread ${Thread.currentThread().name}")

            val doSomethingAnswer = doSomething()
            val doSomethingAnswer2 = doSomething2()

            Log.d(TAG, doSomethingAnswer)
            Log.d(TAG, doSomethingAnswer2)


        }

        Log.d(TAG, "Hi from thread ${Thread.currentThread().name}")
    }

    // We also can create customized suspend functions.
    private suspend fun doSomething(): String {
        delay(3000)
        return "This is the answer from doSomething."
    }

    private suspend fun doSomething2(): String {
        delay(3000)
        return "This is the answer from doSomething2."
    }
}