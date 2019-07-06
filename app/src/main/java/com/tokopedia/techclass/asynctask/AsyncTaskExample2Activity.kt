package com.tokopedia.techclass.asynctask

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_asynctask_example_2.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.ref.WeakReference
import java.net.HttpURLConnection
import java.net.URL


class AsyncTaskExample2Activity : AppCompatActivity(), Listener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.tokopedia.techclass.R.layout.activity_asynctask_example_2)

        button.setOnClickListener {
            FetchWebsiteAsyncTask(this).execute()
        }
    }

    override fun onSuccess(result: String?) {
        textView.setText(result)
    }
}

interface Listener {
    fun onSuccess(result: String?)
}

class FetchWebsiteAsyncTask(listener: Listener) : AsyncTask<String, Void, String>() {
    var weakReference: WeakReference<Listener> = WeakReference(listener)

    override fun doInBackground(vararg params: String?): String {
        val urlConnection: HttpURLConnection
        val url: URL

        try {
            url = URL("https://google.com")
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.setRequestMethod("GET")

            val responseCode = urlConnection.getResponseCode()

            if (responseCode == HttpURLConnection.HTTP_OK) {
                return readStream(urlConnection.getInputStream())
            }

        } catch (e: Throwable) {
            e.printStackTrace()
        }

        return ""
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        val listener = weakReference.get()
        if (listener != null) {
            listener.onSuccess(result)
        }
    }

    private fun readStream(`in`: InputStream): String {
        var reader: BufferedReader? = null
        val response = StringBuilder()
        try {
            reader = BufferedReader(InputStreamReader(`in`))
            var line = reader.readLine()
            while (line != null) {
                response.append(line)
                line = reader.readLine()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (reader != null) {
                try {
                    reader.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
            return response.toString()
        }
    }
}
