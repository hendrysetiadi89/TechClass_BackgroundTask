package com.tokopedia.techclass.service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.support.v4.app.JobIntentService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import android.widget.Toast

class ExampleService : Service(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        launch {
            for (i in 0 until 5) {
                Thread.sleep(1000)
                println(i)
            }
            stopSelf()
        }
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        showToast("Service Started");
    }

    override fun onDestroy() {
        showToast("Service Finished");
        super.onDestroy()
    }

    fun showToast(text: CharSequence) {
        Handler().post { Toast.makeText(this@ExampleService, text, Toast.LENGTH_SHORT).show() }
    }
}


