package com.henryford.sticker.util

import android.app.Activity
import android.content.Context
import com.google.android.gms.ads.*
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback


class RewardedInterstitialAdWrapper : OnUserEarnedRewardListener {
    val TAG = RewardedInterstitialAdWrapper::class.java.simpleName
    val DEBUG_REWARDEDAD_ID = "ca-app-pub-3940256099942544/5354046379"

    fun loadRewardeInterstitialdAd(context: Context){
        // Use the test ad unit ID to load an ad.
        // Use the test ad unit ID to load an ad.
        RewardedInterstitialAd.load(context, "ca-app-pub-3940256099942544/5354046379",
            AdRequest.Builder().build(), object : RewardedInterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: RewardedInterstitialAd) {
                    LogUtil.e(TAG, "onAdLoaded")
                    ad.setFullScreenContentCallback(object :
                        FullScreenContentCallback() {
                        /** Called when the ad failed to show full screen content.  */
                        override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                            LogUtil.i(TAG, "onAdFailedToShowFullScreenContent")
                        }

                        /** Called when ad showed the full screen content.  */
                        override fun onAdShowedFullScreenContent() {
                            LogUtil.i(TAG, "onAdShowedFullScreenContent")
                        }

                        /** Called when full screen content is dismissed.  */
                        override fun onAdDismissedFullScreenContent() {
                            LogUtil.i(TAG, "onAdDismissedFullScreenContent")
                        }
                    })

                    ad.show(context as Activity,this@RewardedInterstitialAdWrapper);
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    LogUtil.e(TAG, "onAdFailedToLoad")
                }
            })
    }

    override fun onUserEarnedReward(p0: RewardItem) {
        LogUtil.i(TAG, "onUserEarnedReward");
        // TODO: Reward the user!
    }
}