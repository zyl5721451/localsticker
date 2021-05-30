package com.henryford.sticker.util

import android.content.Context
import android.util.DisplayMetrics
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

object AdManager {
    lateinit var context:Context

    fun init(context: Context){
        MobileAds.initialize(context)
        this.context = context
    }







}