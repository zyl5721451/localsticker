package com.henryford.sticker.util

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

typealias GetIOExecutor = ()-> Scheduler

object RxJavaUtil {
    var iOScheduler: GetIOExecutor = { Schedulers.io() }

    fun <T> observableIoToMain(): ObservableTransformer<T, T> {
        return ObservableTransformer{
                upstream ->
            upstream.subscribeOn(iOScheduler.invoke())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }
}