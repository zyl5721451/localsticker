package com.henryford.sticker.main

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.*
import com.alibaba.android.arouter.launcher.ARouter
import com.allen.commlib.arouter.ARouterPage
import com.allen.commlib.glide.GlideUtils
import com.allen.commlib.glide.progress.OnProgressListener
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.henryford.sticker.BaseDialogFragment
import com.henryford.sticker.R
import com.henryford.sticker.main.bean.StickerDetailBean
import com.henryford.sticker.util.LogUtil
import java.io.File

class DownloadStateFragment : BaseDialogFragment() {
    val TAG = DownloadStateFragment::class.java.simpleName
    lateinit var rlDownloading:RelativeLayout
    lateinit var pbProgress:ProgressBar
    lateinit var llDownloaded:LinearLayout
    lateinit var ivClose:ImageView
    lateinit var btnMake:Button
    lateinit var flAd:FrameLayout
    var sticker:StickerDetailBean.InnerStickerDetailBean? = null
    override fun firstLoadData() {
    }

    companion object{
        val FRAGMENT_TAG = "download_state"
        val KEY_DATA = "key_data"
        fun newInstance(sticker: StickerDetailBean.InnerStickerDetailBean?):DownloadStateFragment{
            var fragment = DownloadStateFragment()
            var bundle = Bundle()
            bundle.putSerializable(KEY_DATA, sticker)
            fragment.arguments = bundle
            return fragment
        }
    }

   private fun updateDownlodedUI(){
        llDownloaded.visibility = View.VISIBLE
        rlDownloading.visibility = View.GONE
    }


    override fun getRootViewResource(): Int {
        return R.layout.fragment_download_state
    }

    override fun initView() {
        rlDownloading = rootView!!.findViewById(R.id.rl_downloading)
        pbProgress = rootView!!.findViewById(R.id.pb)
        llDownloaded = rootView!!.findViewById(R.id.ll_downloaded)
        ivClose = rootView!!.findViewById(R.id.iv_close)
        btnMake = rootView!!.findViewById(R.id.btn_make)
        flAd = rootView!!.findViewById(R.id.fl_ad)
    }

    override fun initData() {
        sticker = arguments?.getSerializable(KEY_DATA) as StickerDetailBean.InnerStickerDetailBean?
        downloadSticker()

    }

    private fun downloadSticker() {
        sticker?.run {
            GlideUtils.downloadOnlyWithProgress(context,
                sticker?.original,
                object : CustomTarget<File>() {
                    override fun onResourceReady(
                        resource: File,
                        transition: Transition<in File>?
                    ) {
                        LogUtil.d(TAG, "onResourceReady:" + resource.absolutePath)
                        updateDownlodedUI()
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {

                    }
                },
                OnProgressListener { isComplete, percentage, bytesRead, totalBytes ->
                    LogUtil.d(
                        TAG,
                        "OnProgressListener:" + percentage + ":" + isComplete + ":" + totalBytes
                    )
                })
        }
    }


    override fun setListener() {
        btnMake.setOnClickListener {
            ARouter.getInstance().build(ARouterPage.MAKE_ACTIVITY).navigation(context)
        }
        ivClose.setOnClickListener {
            dismiss()
        }

    }

    override fun getDialogTheme(): Int {
        return R.style.AppTheme_StickerDetail
    }


}