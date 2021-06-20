package com.henryford.sticker

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.rewarded.RewardedAd
import com.henryford.sticker.util.BannerAd
import com.henryford.sticker.util.InterstitialAdWrapper
import com.henryford.sticker.util.RewardedAdWrapper
import com.henryford.sticker.util.RewardedInterstitialAdWrapper
import kotlinx.android.synthetic.main.activity_test_ad.*

class TestAdActivity : BaseActivity(),View.OnClickListener {
    lateinit var btnBannerAd:Button
    lateinit var btnInterstitialAd:Button
    lateinit var btnRewardedAd: Button
    lateinit var btnRewardedInterstitialAd: Button
    lateinit var btnNativeAd: Button
    lateinit var flBannerAd:FrameLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_ad)
        initView()
        initListener()
    }


    private fun initView() {
        btnBannerAd = btn_banner_ad
        btnInterstitialAd = btn_interstitial_ad
        btnRewardedAd = btn_rewarded_ad
        btnRewardedInterstitialAd = btn_rewarded_interstitial_ad
        btnNativeAd = btn_native_ad
        flBannerAd = fl_banner_ad
    }

    private fun initListener() {
        btnBannerAd.setOnClickListener(this)
        btnInterstitialAd.setOnClickListener(this)
        btnRewardedAd.setOnClickListener(this)
        btnRewardedInterstitialAd.setOnClickListener(this)
        btnNativeAd.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_banner_ad ->{
                BannerAd(flBannerAd).loadBannerAd(BannerAd.DEBUG_BANNER_AD_ID)
            }
            R.id.btn_interstitial_ad ->{
                InterstitialAdWrapper().loadInterstitialAd(this)
            }
            R.id.btn_rewarded_ad ->{
                RewardedAdWrapper().loadRewardedAd(this)
            }
            R.id.btn_rewarded_interstitial_ad ->{
                RewardedInterstitialAdWrapper().loadRewardeInterstitialdAd(this)
            }
            R.id.btn_native_ad ->{
            }

        }

    }

}