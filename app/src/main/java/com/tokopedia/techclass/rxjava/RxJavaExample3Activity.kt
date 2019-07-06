package com.tokopedia.techclass.rxjava

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tokopedia.techclass.R
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_rxjava_example_3.*

class RxJavaExample3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxjava_example_3)

        button.setOnClickListener { _ ->
            fetchListOfCities()
                .flatMap { Observable.fromIterable(it) }
                .flatMap { fetchCityTitle(it).subscribeOn(Schedulers.io()) }
                .map { "$it city" }
                .toList()
                .toObservable()
                .map { it.joinToString(", ") }
                .doOnNext { storeToDatabase().subscribeOn(Schedulers.io()) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : Observer<String> {
                    override fun onComplete() {}
                    override fun onSubscribe(d: Disposable) {}
                    override fun onNext(t: String) {
                        textView.text = t
                    }

                    override fun onError(e: Throwable) {
                        // do on error here
                    }
                })
        }

        button_2.setOnClickListener {
            Observable.zip(
                fetchCityTitle(1).subscribeOn(Schedulers.io()),
                fetchPersonName("1").subscribeOn(Schedulers.io()),
                BiFunction<String, String, String> { t1, t2 -> "$t1 $t2" }
            ).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : Observer<String> {
                    override fun onComplete() {}
                    override fun onSubscribe(d: Disposable) {}
                    override fun onNext(t: String) {
                        textView_2.text = t
                    }

                    override fun onError(e: Throwable) {
                        // do on error here
                    }
                })
        }
    }

    fun fetchListOfCities(): Observable<List<Int>> {
        return Observable.fromCallable {
            Thread.sleep(1000)
            listOf(1, 2, 3, 4)
        }
    }

    fun fetchCityTitle(cityId: Int): Observable<String> {
        return Observable.fromCallable {
            Thread.sleep(1000)
            when (cityId) {
                1 -> "Jakarta"
                2 -> "Bandung"
                3 -> "Bali"
                else -> "Other"
            }
        }
    }

    fun fetchPersonName(personId: String): Observable<String> {
        return Observable.fromCallable {
            Thread.sleep(1000)
            when (personId) {
                "1" -> "Budi"
                else -> "Unknown"
            }
        }
    }

    fun storeToDatabase(): Observable<Boolean> {
        return Observable.fromCallable {
            Thread.sleep(1000)
            true
        }
    }

}