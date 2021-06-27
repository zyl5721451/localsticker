package com.henryford.sticker.util

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.View
import android.widget.*
import com.google.android.gms.ads.*
import com.google.android.gms.ads.nativead.MediaView
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import com.google.android.gms.ads.nativead.NativeAdView
import com.henryford.sticker.R
import java.util.*
import kotlin.collections.ArrayList


class NativeAdWrapper {
    val TAG = NativeAdWrapper::class.java.simpleName
    val DEBUG_NATIVE_ID = "ca-app-pub-3940256099942544/2247696110"
    var nativeAds = ArrayList<NativeAd>()
    var currentNativeAd:NativeAd? = null
    lateinit var adLoader:AdLoader
    fun loadNativedAd(context: Context,loadListener: OnNativeAdLoadListener){
        adLoader = AdLoader.Builder(context, DEBUG_NATIVE_ID)
            .forNativeAd {  nativeAd ->
                nativeAds.add(nativeAd)
                if(!adLoader.isLoading){
                    loadListener.onNativeAdLoaded(nativeAds)
                }
            }
            .withAdListener(object : AdListener() {
                override fun onAdImpression() {
                    super.onAdImpression()
                }

                override fun onAdClicked() {
                    super.onAdClicked()
                }

                override fun onAdFailedToLoad(p0: LoadAdError) {
                    super.onAdFailedToLoad(p0)
                    // Handle the failure by logging, altering the UI, and so on.
                }

                override fun onAdClosed() {
                    super.onAdClosed()
                }

                override fun onAdOpened() {
                    super.onAdOpened()
                }

                override fun onAdLoaded() {
                    super.onAdLoaded()
                }
            })
            .withNativeAdOptions(
                NativeAdOptions.Builder()
                // Methods in the NativeAdOptions.Builder class can be
                // used here to specify individual options settings.
                .build())
            .build()

        adLoader.loadAds(AdRequest.Builder().build(), 2)


    }

    fun loadUnifiedNativeAd(activity: Activity, container: FrameLayout) {

        val builder = AdLoader.Builder(activity, DEBUG_NATIVE_ID)
            .forNativeAd { nativeAd ->
                // OnUnifiedNativeAdLoadedListener implementation.
                // If this callback occurs after the activity is destroyed, you must call
                // destroy and return or you may get a memory leak.
                var activityDestroyed = false
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    activityDestroyed = activity.isDestroyed
                }
                if (activityDestroyed || activity.isFinishing || activity.isChangingConfigurations) {
                    nativeAd.destroy()
                    return@forNativeAd
                }
                // You must call destroy on old ads when you are done with them,
                // otherwise you will have a memory leak.
                currentNativeAd?.destroy()
                currentNativeAd = nativeAd
                val adView = activity.layoutInflater
                    .inflate(R.layout.ad_splash, null) as NativeAdView
                populateUnifiedNativeAdView(nativeAd, adView)
                container.removeAllViews()
                container.addView(adView)
            }.withAdListener(object : AdListener() {
                override fun onAdImpression() {
                    super.onAdImpression()
                }

                override fun onAdClicked() {
                    super.onAdClicked()
                }

                override fun onAdFailedToLoad(p0: LoadAdError) {
                    super.onAdFailedToLoad(p0)
                }

                override fun onAdClosed() {
                    super.onAdClosed()
                }

                override fun onAdOpened() {
                    super.onAdOpened()
                }

                override fun onAdLoaded() {
                    super.onAdLoaded()
                }

            })

        val videoOptions = VideoOptions.Builder()
            .setStartMuted(true)
            .build()
        val adOptions = com.google.android.gms.ads.formats.NativeAdOptions.Builder()
            .setVideoOptions(videoOptions)
            .build()
        builder.withNativeAdOptions(adOptions).build().loadAd(AdRequest.Builder().build())
    }


    /**
     * @param nativeAd the object containing the ad's assets
     * @param adView the view to be populated
     */
    private fun populateUnifiedNativeAdView(nativeAd: NativeAd, adView: NativeAdView) {
        // Set the media view.
        adView.mediaView = adView.findViewById<MediaView>(R.id.ad_media)//图片或者视频

        // Set other ad assets.
        adView.headlineView = adView.findViewById(R.id.ad_headline)//标题名称，广告名称
        adView.bodyView = adView.findViewById(R.id.ad_body)//广告介绍，简介
        adView.callToActionView = adView.findViewById(R.id.ad_call_to_action)//安装按钮
        adView.iconView = adView.findViewById(R.id.ad_app_icon)//图标
        adView.priceView = adView.findViewById(R.id.ad_price)//价格
        adView.starRatingView = adView.findViewById(R.id.ad_stars)//星级
        adView.storeView = adView.findViewById(R.id.ad_store)//应用市场名称
        adView.advertiserView = adView.findViewById(R.id.ad_advertiser)//广告提供者

        // The headline and media content are guaranteed to be in every UnifiedNativeAd.
        (adView.headlineView as TextView).text = nativeAd.headline//标题名称，广告名称
        adView.mediaView.setMediaContent(nativeAd.mediaContent)

        // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
        // check before trying to display them.
        if (nativeAd.body == null) {
            adView.bodyView.visibility = View.INVISIBLE
        } else {
            adView.bodyView.visibility = View.VISIBLE
            (adView.bodyView as TextView).text = nativeAd.body
        }

        if (nativeAd.callToAction == null) {
            adView.callToActionView.visibility = View.INVISIBLE
        } else {
            adView.callToActionView.visibility = View.VISIBLE
            (adView.callToActionView as Button).text = nativeAd.callToAction
        }

        if (nativeAd.icon == null) {
            adView.iconView.visibility = View.GONE
        } else {
            (adView.iconView as ImageView).setImageDrawable(
                nativeAd.icon.drawable
            )
            adView.iconView.visibility = View.VISIBLE
        }

        if (nativeAd.price == null) {
            adView.priceView.visibility = View.INVISIBLE
        } else {
            adView.priceView.visibility = View.VISIBLE
            (adView.priceView as TextView).text = nativeAd.price
        }

        if (nativeAd.store == null) {
            adView.storeView.visibility = View.INVISIBLE
        } else {
            adView.storeView.visibility = View.VISIBLE
            (adView.storeView as TextView).text = nativeAd.store
        }

        if (nativeAd.starRating == null) {
            adView.starRatingView.visibility = View.INVISIBLE
        } else {
            (adView.starRatingView as RatingBar).rating = nativeAd.starRating!!.toFloat()
            adView.starRatingView.visibility = View.VISIBLE
        }

        if (nativeAd.advertiser == null) {
            adView.advertiserView.visibility = View.INVISIBLE
        } else {
            (adView.advertiserView as TextView).text = nativeAd.advertiser
            adView.advertiserView.visibility = View.VISIBLE
        }

        // This method tells the Google Mobile Ads SDK that you have finished populating your
        // native ad view with this native ad.
        adView.setNativeAd(nativeAd)

        // Get the video controller for the ad. One will always be provided, even if the ad doesn't
        // have a video asset.
        val vc = nativeAd.mediaContent

        // Updates the UI to say whether or not this ad has a video asset.
        if (vc.hasVideoContent()) {
            // Create a new VideoLifecycleCallbacks object and pass it to the VideoController. The
            // VideoController will call methods on this object when events occur in the video
            // lifecycle.
            vc.videoController.videoLifecycleCallbacks = object : VideoController.VideoLifecycleCallbacks() {
                override fun onVideoEnd() {
                    // Publishers should allow native ads to complete video playback before
                    // refreshing or replacing them with another ad in the same UI location.
                    super.onVideoEnd()
                }
            }
        }
    }


    fun destroy(){
        for(ad in nativeAds){
            ad.destroy()
        }
        currentNativeAd?.destroy()
    }

    interface OnNativeAdLoadListener{
        fun onNativeAdLoaded(nativeAds:ArrayList<NativeAd>)
    }
}