package com.tokopedia.techclass.asynctask

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import com.tokopedia.techclass.R
import kotlinx.android.synthetic.main.activity_asynctask_example_1.*
import java.lang.ref.WeakReference

class AsyncTaskExample1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asynctask_example_1)

        MyFirstAsyncTask().execute()
        button.setOnClickListener {
            RandomGeneratorAsyncTask(textView).execute()
        }
    }
}

class RandomGeneratorAsyncTask(textView: TextView) : AsyncTask<Void, Void, Int>() {
    var weakReference: WeakReference<TextView> = WeakReference(textView)

    override fun doInBackground(vararg voids: Void): Int? {
        Thread.sleep(1000)
        println(Thread.currentThread().toString())
        // will return random value (0 to 100) after 1 second
        return (Math.random() * 100).toInt()
    }

    override fun onPostExecute(result: Int?) {
        super.onPostExecute(result)
        println(Thread.currentThread().toString())
        val textView = weakReference.get()
        if (textView!=null) {
            textView.text = result.toString()
        }
    }
}

class MyFirstAsyncTask : AsyncTask<Void, Void, Void>() {

    override fun doInBackground(vararg voids: Void): Void? {
        Thread.sleep(1000)
        println("This message will show after 1 second in log")
        return null
    }
}

