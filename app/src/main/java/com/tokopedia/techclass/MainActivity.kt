package com.tokopedia.techclass

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tokopedia.techclass.asynctask.AsyncTaskExample1Activity
import com.tokopedia.techclass.asynctask.AsyncTaskExample2Activity
import com.tokopedia.techclass.rxjava.RxJavaExample1Activity
import com.tokopedia.techclass.rxjava.RxJavaExample2Activity
import com.tokopedia.techclass.rxjava.RxJavaExample3Activity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_async_1.setOnClickListener {
            startActivity(Intent(this, AsyncTaskExample1Activity::class.java))
        }
        button_async_2.setOnClickListener {
            startActivity(Intent(this, AsyncTaskExample2Activity::class.java))
        }
        button_rxjava_1.setOnClickListener {
            startActivity(Intent(this, RxJavaExample1Activity::class.java))
        }
        button_rxjava_2.setOnClickListener {
            startActivity(Intent(this, RxJavaExample2Activity::class.java))
        }
        button_rxjava_3.setOnClickListener {
            startActivity(Intent(this, RxJavaExample3Activity::class.java))
        }
    }
}