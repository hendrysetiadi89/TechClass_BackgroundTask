package com.tokopedia.techclass.service

import android.annotation.TargetApi
import android.app.Service
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.support.v4.app.JobIntentService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import android.widget.Toast

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
class ExampleJobService : JobService(), CoroutineScope {
    override fun onStopJob(params: JobParameters?): Boolean {
        return true
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        showToast("JobService Started");
        launch {
            for (i in 0 until 5) {
                Thread.sleep(1000)
                println(i)
            }
            jobFinished(params, false)
        }
        return true
    }

    override fun onDestroy() {
        showToast("JobService Finished")
        super.onDestroy()
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    fun showToast(text: CharSequence) {
        Handler().post { Toast.makeText(this@ExampleJobService, text, Toast.LENGTH_SHORT).show() }
    }
}
