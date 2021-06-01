package com.aleangelozi.kotlincoroutines

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.aleangelozi.kotlincoroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Coroutines are executed sequentially by default, but we can change
        // in order to execute asynchronously.

        // Running sequentially
        GlobalScope.launch(Dispatchers.IO) {
            val time = measureTimeMillis {
                val answer1 = networkCall1()
                val answer2 = networkCall2()

                Log.d(TAG, "Answer1 is $answer1")
                Log.d(TAG, "Answer2 is $answer2")
            }
            Log.d(TAG, "First request took $time ms")


        }

        // Running asynchronously using Jobs.
        // This is not recommended, it's better practice to use Async.
        GlobalScope.launch(Dispatchers.IO) {
            val time = measureTimeMillis {
                lateinit var answer3: String
                lateinit var answer4: String

                val job1 = launch { answer3 = networkCall3() }
                val job2 = launch { answer4 = networkCall4() }
                job1.join()
                job2.join()

                Log.d(TAG, "Answer3 is $answer3")
                Log.d(TAG, "Answer4 is $answer4")
            }
            Log.d(TAG, "Second request took $time ms")
        }


        // Running asynchronously using Async.
        GlobalScope.launch(Dispatchers.IO) {
            val time = measureTimeMillis {


                val answer5 = async { networkCall5() }
                val answer6 = async {networkCall6() }

                Log.d(TAG, "Answer3 is ${answer5.await()}")
                Log.d(TAG, "Answer4 is ${answer6.await()}")
            }
            Log.d(TAG, "Second request took $time ms")
        }
    }

    private suspend fun networkCall1(): String {
        delay(3000L)
        return "Answer 1"
    }

    private suspend fun networkCall2(): String {
        delay(3000L)
        return "Answer 2"
    }

    private suspend fun networkCall3(): String {
        delay(3000L)
        return "Answer 3"
    }

    private suspend fun networkCall4(): String {
        delay(3000L)
        return "Answer 4"
    }

    private suspend fun networkCall5(): String {
        delay(3000L)
        return "Answer 5"
    }

    private suspend fun networkCall6(): String {
        delay(3000L)
        return "Answer 6"
    }


}