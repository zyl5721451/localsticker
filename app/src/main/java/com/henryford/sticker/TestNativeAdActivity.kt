package com.henryford.sticker

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.google.android.ads.nativetemplates.NativeTemplateStyle
import com.google.android.ads.nativetemplates.TemplateView
import com.google.android.gms.ads.nativead.NativeAd
import com.henryford.sticker.util.NativeAdWrapper
import kotlinx.android.synthetic.main.activity_test_native_ad.*


class TestNativeAdActivity : BaseActivity() {
    var nativeAdWrapper: NativeAdWrapper? = null


    override fun onDestroy() {
        super.onDestroy()
        nativeAdWrapper?.destroy()
    }

    override fun initToolbar() {
    }

    override fun setListener() {
    }

    override fun initData() {
        nativeAdWrapper = NativeAdWrapper()
        //        nativeAdWrapper!!.loadNativedAd(this,object :NativeAdWrapper.OnNativeAdLoadListener{
//            override fun onNativeAdLoaded(nativeAds: ArrayList<NativeAd>) {
//                if(nativeAds.size>=2){
//                    val styles1 = NativeTemplateStyle.Builder().withMainBackgroundColor(ColorDrawable(Color.WHITE)).build()
//                    val template1 = findViewById<TemplateView>(R.id.my_template_small)
//                    template1.setStyles(styles1)
//                    template1.setNativeAd(nativeAds.get(0))
//
//                    val styles2 = NativeTemplateStyle.Builder().withMainBackgroundColor(ColorDrawable(Color.WHITE)).build()
//                    val template2 = findViewById<TemplateView>(R.id.my_template_big)
//                    template2.setStyles(styles2)
//                    template2.setNativeAd(nativeAds.get(1))
//                }
//
//            }
//
//        })
        nativeAdWrapper!!.loadUnifiedNativeAd(
            this,
            fl_unified_ad,
            NativeAdWrapper.NATIVEAD_VIEW_HOME_SPLASH,
            object : NativeAdWrapper.OnNativeAdLoadListener {
                override fun onNativeAdLoaded(nativeAds: ArrayList<NativeAd>) {

                }

                override fun onLoadComplete() {

                }

            })
    }

    override fun initView() {

    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_test_native_ad
    }
}