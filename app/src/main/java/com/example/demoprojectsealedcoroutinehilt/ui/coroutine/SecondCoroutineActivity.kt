package com.example.demoprojectsealedcoroutinehilt.ui.coroutine

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.demoprojectsealedcoroutinehilt.R
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SecondCoroutineActivity : AppCompatActivity() {

    val scope = CoroutineScope(CoroutineName("Mu Coroutine"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activit_second_coroutine)
        scope.launch {
            Log.d("TTT", this.coroutineContext.toString())
        }
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}