package com.tokopedia.techclass.service

import android.content.Intent
import android.os.Handler
import android.support.v4.app.JobIntentService
import android.widget.Toast


class ExampleIntentService : JobIntentService() {
    override fun onCreate() {
        super.onCreate()
        showToast("JobIntentService Started");
    }
    override fun onHandleWork(p0: Intent) {
        for (i in 0 until 5) {
            Thread.sleep(1000)
            println(i)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        showToast("JobIntentService finished");
    }

    fun showToast(text: CharSequence) {
        Handler().post { Toast.makeText(this, text, Toast.LENGTH_SHORT).show() }
    }
}
