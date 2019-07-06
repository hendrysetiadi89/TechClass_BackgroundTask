package com.tokopedia.techclass.rxjava

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tokopedia.techclass.R
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_rxjava_example_2.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class RxJavaExample2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxjava_example_2)
        button.setOnClickListener {
            Observable.just(true)
                .map {
                    fetchFromSite()
                }.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : Observer<String> {
                    override fun onComplete() {}
                    override fun onSubscribe(d: Disposable) {

                    }
                    override fun onNext(t: String) {
                        textView.text = t
                    }
                    override fun onError(e: Throwable) {
                        // do when error
                    }
                })
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