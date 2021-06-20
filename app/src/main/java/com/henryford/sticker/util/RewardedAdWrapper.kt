package com.henryford.sticker.util

import android.app.Activity
import android.content.Context
import com.google.android.gms.ads.*
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback

class RewardedAdWrapper {
    val TAG = RewardedAdWrapper::class.java.simpleName
    val DEBUG_REWARDEDAD_ID = "ca-app-pub-3940256099942544/5224354917"

    fun loadRewardedAd(context: Context){
        // Use an activity context to get the rewarded video instance.
        var adRequest = AdRequest.Builder().build()

        RewardedAd.load(context,DEBUG_REWARDEDAD_ID, adRequest, object : RewardedAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                LogUtil.d(TAG, adError?.message)
            }

            override fun onAdLoaded(rewardedAd: RewardedAd) {
                LogUtil.d(TAG, "Ad was loaded.")
                rewardedAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
                    override fun onAdDismissedFullScreenContent() {
                        LogUtil.d(TAG, "Ad was dismissed.")
                    }

                    override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                        LogUtil.d(TAG, "Ad failed to show.")
                    }

                    override fun onAdShowedFullScreenContent() {
                        LogUtil.d(TAG, "Ad showed fullscreen content.")
                        // Called when ad is dismissed.
                        // Don't set the ad reference to null to avoid showing the ad a second time.
                    }
                }

                rewardedAd?.show(context as Activity, OnUserEarnedRewardListener() {
                    fun onUserEarnedReward(rewardItem: RewardItem) {
                        var rewardAmount = rewardItem.amount
                        var rewardType = rewardItem.type
                        LogUtil.d(TAG, "User earned the reward.")
                    }
                })

            }
        })
    }
}