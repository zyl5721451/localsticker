package com.henryford.sticker.mine.widget

import android.content.Context
import android.media.Image
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.allen.commlib.glide.GlideUtils
import com.henryford.sticker.R
import com.henryford.sticker.mine.bean.MinePackBean
import com.henryford.sticker.widget.BaseCustomView

class PackDetailHeader : BaseCustomView,View.OnClickListener{
    private lateinit var ivIcon: ImageView
    private lateinit var tvName: TextView
    private lateinit var tvAuthor: TextView
    private lateinit var tvSize: TextView
    private lateinit var btnShare: Button
    private lateinit var btnSendTo: Button
    private lateinit var flAd: FrameLayout
    constructor(context: Context) : super(context){

    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){

    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){

    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes){

    }

    override fun initListener() {
        btnShare.setOnClickListener(this)
        btnSendTo.setOnClickListener(this)
    }

    fun updateUI(data:MinePackBean.InnerMinePackBean?){
        data?.run {
            GlideUtils.loadRoundImage(ivIcon,data.previewIcon,5)
            tvName.text = data.name
            tvAuthor.text = data.author
            tvSize.text = data.size.toString()+"kb"
        }
    }
    override fun initView(view: View) {
        ivIcon = view.findViewById<ImageView>(R.id.iv_icon)
        tvName = view.findViewById(R.id.tv_name)
        tvAuthor = view.findViewById(R.id.tv_author)
        tvSize = view.findViewById(R.id.tv_size)
        btnShare = view.findViewById(R.id.btn_share)
        btnSendTo = view.findViewById(R.id.btn_send)
        flAd = view.findViewById(R.id.fl_ad)
    }

    override fun getLayoutId(): Int {
        return R.layout.view_mine_pack_header
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_share ->{

            }
            R.id.btn_send ->{

            }
        }
    }
}