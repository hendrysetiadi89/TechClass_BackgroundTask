package com.tokopedia.techclass

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MyAsyncTask().execute()
        RandomGeneratorAsyncTask(textView).execute()
    }
}

class RandomGeneratorAsyncTask(textView: TextView) : AsyncTask<Void, Void, Int>() {
    var weakReference: WeakReference<TextView> = WeakReference(textView)

    override fun doInBackground(vararg voids: Void): Int? {
        Thread.sleep(1000)
        // will return random value (0 to 100) after 1 second
        return (Math.random() * 100).toInt()
    }

    override fun onPostExecute(result: Int?) {
        super.onPostExecute(result)
        val textView = weakReference.get()
        if (textView!=null) {
            textView.text = result.toString()
        }
    }
}

class MyAsyncTask : AsyncTask<Void, Void, Void>() {

    override fun doInBackground(vararg voids: Void): Void? {
        Thread.sleep(1000)
        Log.i("MY TAG", "This message will show after 1 second in log")
        return null
    }
}