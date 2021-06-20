package com.henryford.sticker.util

import android.app.Activity
import android.content.Context
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import java.util.*

class InterstitialAdWrapper {
    companion object{
        val DEBUG_INTERSTITIALAD_ID = "ca-app-pub-3940256099942544/1033173712"
    }
    val TAG = InterstitialAdWrapper::class.java.simpleName

    fun loadInterstitialAd(context: Context){
        var adRequest = AdRequest.Builder().build()
        InterstitialAd.load(context,DEBUG_INTERSTITIALAD_ID, adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                LogUtil.d(TAG, adError?.message)
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                LogUtil.d(TAG, "Ad was loaded.")
                interstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                    override fun onAdImpression() {
                        super.onAdImpression()
                        LogUtil.d(TAG,"onAdImpression")
                    }

                    override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                        super.onAdFailedToShowFullScreenContent(p0)
                        LogUtil.d(TAG,"onAdFailedToShowFullScreenContent")
                    }

                    override fun onAdShowedFullScreenContent() {
                        super.onAdShowedFullScreenContent()
                        LogUtil.d(TAG,"onAdShowedFullScreenContent")
                    }

                    override fun onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent()
                        LogUtil.d(TAG,"onAdDismissedFullScreenContent")
                    }
                }
                interstitialAd?.show(context as Activity)
            }
        })
    }

}