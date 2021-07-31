package com.henryford.sticker.main.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.allen.commlib.glide.GlideUtils
import com.allen.commlib.glide.progress.OnProgressListener
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.badge.BadgeUtils
import com.henryford.sticker.R
import com.henryford.sticker.main.DownloadStateFragment
import com.henryford.sticker.main.bean.StickerDetailBean
import com.henryford.sticker.mine.StickerDetailFragment
import com.henryford.sticker.widget.BaseCustomView
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.ad_splash.view.*
import java.io.File

class StickerDownloadHeaderView : BaseCustomView,View.OnClickListener {
    lateinit var ivIcon:ImageView
    lateinit var ivUp:ImageView
    lateinit var ivDown:ImageView
    lateinit var flAd:FrameLayout
    lateinit var btnAddTo:TextView
    lateinit var btnShareTo:Button
    lateinit var tvReportFlag:TextView
    lateinit var tagView:TagHeaderView
    var sticker:StickerDetailBean.InnerStickerDetailBean? = null
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    override fun initListener() {
        ivUp.setOnClickListener(this)
        ivDown.setOnClickListener(this)
        btnShareTo.setOnClickListener(this)
        btnAddTo.setOnClickListener(this)
        tvReportFlag.setOnClickListener(this)
    }

    override fun initView(view: View) {
        ivIcon = view.findViewById(R.id.iv_icon)
        ivUp = view.findViewById(R.id.iv_up)
        ivDown = view.findViewById(R.id.iv_down)
        flAd = view.findViewById(R.id.fl_ad)
        btnAddTo = view.findViewById(R.id.btn_add)
        btnShareTo = view.findViewById(R.id.btn_share)
        tvReportFlag = view.findViewById(R.id.tv_report_text)
        tagView = view.findViewById(R.id.tag_view)
    }

    fun updateUI(sticker:StickerDetailBean.InnerStickerDetailBean){
        this.sticker = sticker
        GlideUtils.loadImage(ivIcon,sticker.original)
        tagView.addTagList(sticker.tags)

    }
    override fun getLayoutId(): Int {
        return R.layout.view_sticker_download_header
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.iv_up ->{

            }
            R.id.iv_down ->{

            }
            R.id.btn_add ->{
                val ft= (context as FragmentActivity).supportFragmentManager.beginTransaction()
                val prev: Fragment? = (context as FragmentActivity).supportFragmentManager.findFragmentByTag(DownloadStateFragment.FRAGMENT_TAG)
                if (prev != null) {
                    ft.remove(prev)
                }
                ft.addToBackStack(null)
                val newFragment: DownloadStateFragment? = DownloadStateFragment.newInstance(sticker)
                newFragment?.show(ft, DownloadStateFragment.FRAGMENT_TAG)
            }
            R.id.btn_share ->{

            }
            R.id.tv_report_text ->{

            }
        }
    }
}