package com.henryford.sticker.util

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.util.Log
import android.view.ViewGroup
import com.google.android.gms.ads.*
import com.henryford.sticker.BuildConfig

class BannerAd {
    val TAG = BannerAd::class.java.simpleName
    companion object{
        const val HOME_BANNER_AD_ID = "ca-app-pub-1115472477447824/7336217319"
        const val DETAIL_BANNER_AD_ID = "ca-app-pub-1115472477447824/7872222172"
        const val DEBUG_BANNER_AD_ID = "ca-app-pub-3940256099942544/6300978111"
    }

    lateinit var adView: AdView
    lateinit var adSize: AdSize
    lateinit var context: Context
    lateinit var container: ViewGroup
    var initialLayoutComplete = false

    constructor(container: ViewGroup) {
        val display = (container.context as Activity).windowManager.defaultDisplay
        val outMetrics = DisplayMetrics()
        display.getMetrics(outMetrics)

        val density = outMetrics.density

        var adWidthPixels = container.width.toFloat()
        if (adWidthPixels == 0f) {
            adWidthPixels = outMetrics.widthPixels.toFloat()
        }
        val adWidth = (adWidthPixels / density).toInt()
        adSize = AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(container.context, adWidth)
        context = container.context
        this.container = container
    }



    fun loadBannerAd(adId:String){
        adView = AdView(context)
        this.container.addView(adView)
        this.container.viewTreeObserver.addOnGlobalLayoutListener {
            if (!initialLayoutComplete) {
                initialLayoutComplete = true

                if(BuildConfig.DEBUG){
                    LogUtil.d(TAG,"loadBannerAd:debug")
                    adView.adUnitId = DEBUG_BANNER_AD_ID
                }else {
                    LogUtil.d(TAG,"loadBannerAd:release")
                    adView.adUnitId = adId
                }

                adView.adSize = adSize
                // Create an ad request. Check your logcat output for the hashed device ID to
                // get test ads on a physical device, e.g.,
                // "Use AdRequest.Builder.addTestDevice("ABCDE0123") to get test ads on this device."
                val adRequest = AdRequest
                    .Builder()
                    .build()
                // Start loading the ad in the background.
                adView.loadAd(adRequest)
            }
        }

    }

    fun pause(){
        adView.pause()
    }

    fun resume(){
        adView.resume()
    }

    fun destroy(){
        adView.destroy()
    }



}