package com.tokopedia.techclass.coroutines

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tokopedia.techclass.R
import kotlinx.android.synthetic.main.activity_coroutines_example_1.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CoroutinesExample1Activity : AppCompatActivity(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines_example_1)

        button.setOnClickListener {
            launch {
                delay(1000)
                val randomValue = (Math.random() * 100).toInt().toString()
                withContext(Dispatchers.Main) {
                    textView.text = randomValue
                }
            }
        }

        button_2.setOnClickListener {
            launch {
                delay(1000)
                val deferred = async(Dispatchers.IO) {
                    (Math.random() * 100).toInt().toString()
                }
                val result = deferred.await()
                withContext(Dispatchers.Main) {
                    textView_2.text = result
                }
            }
        }

        button_3.setOnClickListener {
            launch {
                val deferred1 = async(Dispatchers.IO) {
                    delay(2000)
                    "Jakarta"
                }
                val deferred2 = async(Dispatchers.IO) {
                    delay(2000)
                    "Bandung"
                }
                val result = deferred1.await() + " " + deferred2.await()
                withContext(Dispatchers.Main) {
                    textView_3.text = result
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

    }


}