package com.henryford.sticker.util

import android.content.Context
import com.henryford.sticker.dao.AppDataBase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.schedulers.Schedulers

object LocalDataManager {
    val TAG: String = LocalDataManager::class.java.simpleName
    fun prepareDefaultSticker(context: Context){
        Observable.create<Boolean> {
            if(AppDataBase.getInstance(context).mineStickerDao().stickerCount() < 3){
                AssetsUtil.doCopy(context,"stickers",context.filesDir.absolutePath)
            }
            it.onComplete()
        }.compose(RxJavaUtil.observableIoToMain())
            .subscribe {
                LogUtil.d(TAG, "prepareDefaultSticker:$it")
            }

    }
}