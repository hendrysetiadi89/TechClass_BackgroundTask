package com.tokopedia.techclass.coroutines

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tokopedia.techclass.R
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_coroutines_example_1.*
import kotlinx.android.synthetic.main.activity_rxjava_example_2.*
import kotlinx.android.synthetic.main.activity_rxjava_example_2.button
import kotlinx.android.synthetic.main.activity_rxjava_example_2.textView
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.coroutines.CoroutineContext

class CoroutinesExample2Activity : AppCompatActivity(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines_example_2)
        button.setOnClickListener {
            launch {
                val result = fetchFromSite()
                withContext(Dispatchers.Main) {
                    textView.text = result
                }
            }
        }
    }

    fun fetchFromSite():String{
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