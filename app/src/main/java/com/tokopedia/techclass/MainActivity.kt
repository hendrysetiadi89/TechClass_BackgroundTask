package com.tokopedia.techclass

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.JobIntentService
import android.support.v7.app.AppCompatActivity
import com.tokopedia.techclass.asynctask.AsyncTaskExample1Activity
import com.tokopedia.techclass.asynctask.AsyncTaskExample2Activity
import com.tokopedia.techclass.coroutines.CoroutinesExample1Activity
import com.tokopedia.techclass.coroutines.CoroutinesExample2Activity
import com.tokopedia.techclass.rxjava.RxJavaExample1Activity
import com.tokopedia.techclass.rxjava.RxJavaExample2Activity
import com.tokopedia.techclass.rxjava.RxJavaExample3Activity
import com.tokopedia.techclass.service.ExampleIntentService
import com.tokopedia.techclass.service.ExampleJobService
import com.tokopedia.techclass.service.ExampleService
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
        button_coroutines_1.setOnClickListener {
            startActivity(Intent(this, CoroutinesExample1Activity::class.java))
        }
        button_coroutines_2.setOnClickListener {
            startActivity(Intent(this, CoroutinesExample2Activity::class.java))
        }
        button_service.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as? JobScheduler
                val bundle = PersistableBundle()

                jobScheduler?.schedule(
                    JobInfo.Builder(11,
                        ComponentName(this, ExampleJobService::class.java))
                        .setRequiredNetworkType(JobInfo.NETWORK_TYPE_NONE)
                        .setOverrideDeadline(0)
                        .setExtras(bundle)
                        .build())
            } else {
                startService(Intent(this, ExampleService::class.java))
            }
        }

        button_service_2.setOnClickListener {
            JobIntentService.enqueueWork(
                this,
                ExampleIntentService::class.java,
                10,
                Intent(this, ExampleIntentService::class.java)
            )
        }
    }
}