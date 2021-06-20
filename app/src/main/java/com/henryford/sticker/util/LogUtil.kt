package com.henryford.sticker.util

import android.util.Log
import com.henryford.sticker.BuildConfig

object LogUtil {
    fun d(tag:String,msg:String){
        if(BuildConfig.DEBUG){
            Log.d(tag,msg)
        }
    }

    fun e(tag:String,msg:String){
        if(BuildConfig.DEBUG){
            Log.e(tag,msg)
        }
    }
    fun i(tag:String,msg:String){
        if(BuildConfig.DEBUG){
            Log.i(tag,msg)
        }
    }

}