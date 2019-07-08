package com.tokopedia.techclass.rxjava

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.tokopedia.techclass.R
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_rxjava_example_1.*

class RxJavaExample1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxjava_example_1)
        val stream = Observable.just(5).map {
            println(Thread.currentThread().toString())
            it * it
        }

        button.setOnClickListener {
            stream.map {
                println(Thread.currentThread().toString())
                Thread.sleep(1000)
                it }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : Observer<Int> {
                    override fun onComplete() {}
                    override fun onSubscribe(d: Disposable) {}
                    override fun onNext(t: Int) {
                        println(Thread.currentThread().toString())
                        Toast.makeText(this@RxJavaExample1Activity, t.toString(), Toast.LENGTH_LONG).show()
                    }
                    override fun onError(e: Throwable) {
                        Toast.makeText(this@RxJavaExample1Activity, e.toString(), Toast.LENGTH_LONG).show()
                    }
                })
        }


    }

}