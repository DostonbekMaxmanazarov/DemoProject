package com.example.demoprojectsealedcoroutinehilt.ui.coroutine

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.demoprojectsealedcoroutinehilt.R
import com.example.demoprojectsealedcoroutinehilt.databinding.ActivitFirstCoroutineBinding
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class FirstCoroutineActivity : AppCompatActivity() {

    val scope = CoroutineScope(CoroutineName("My Coroutine"))

    lateinit var binding: ActivitFirstCoroutineBinding

    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitFirstCoroutineBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Log.d("TTT", "onCreate")

        /*job = GlobalScope.launch {
           while (true) {
               delay(1000L)
               Log.d("TTT", "Running...")
           }
       }*/

/*
        runBlocking {
            Log.d("TTT", this.coroutineContext.toString())
            delay(5000L)
        }
*/

        /*GlobalScope.launch {
            Log.d("TTT", "coroutine 1")
            delay(5000L)
            Log.d("TTT", "coroutine 1")
        }
        GlobalScope.launch {
            Log.d("TTT", "coroutine 2")
            delay(5000L)
            Log.d("TTT", "coroutine 2")
        }

        Log.d("TTT", "onCreate")*/

        lifecycleScope.launch {
            while (true) {
                delay(1000)
                Log.d("TTT", "launch")

            }
        }

        lifecycleScope.launchWhenCreated {
            while (true) {
                delay(1000)
                Log.d("TTT", "launchWhenCreated")
            }

        }
        lifecycleScope.launchWhenResumed {
            while (true) {
                delay(1000)
                Log.d("TTT", "launchWhenResumed")
            }
        }
        lifecycleScope.launchWhenStarted {
            while (true) {
                delay(1000)
                Log.d("TTT", "launchWhenStarted")
            }
        }

        binding.btn.setOnClickListener {
            startActivity(Intent(this, SecondCoroutineActivity::class.java))
            finish()
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d("TTT", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("TTT", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TTT", "onDestroy")
        job?.cancel()
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("TTT", "onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("TTT", "onResume")
    }

    override fun onStart() {
        super.onStart()
        Log.d("TTT", "onStart")
    }
}