package com.tokopedia.techclass.coroutines

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.tokopedia.techclass.R
import kotlinx.android.synthetic.main.activity_coroutines_example_1.*
import kotlinx.coroutines.*

class CoroutinesExample1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines_example_1)
        button.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) { // launch a new coroutine in background and continue
                delay(1000) // non-blocking delay for 1 second (default time unit is ms)
                println("World") // print after delay
            }
            println("Hello,") // main thread continues while coroutine is delayed
        }

    }

}