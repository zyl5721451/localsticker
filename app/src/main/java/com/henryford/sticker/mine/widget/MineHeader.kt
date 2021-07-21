package com.henryford.sticker.mine.widget

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.allen.commlib.glide.GlideUtils
import com.henryford.sticker.R
import com.henryford.sticker.mine.bean.MineHeaderBean
import com.henryford.sticker.widget.BaseCustomView
import kotlinx.android.synthetic.main.view_mine_header.view.*

class MineHeader: BaseCustomView {
    lateinit var ivIcon:ImageView
    lateinit var tvName:TextView
    lateinit var tvPack:TextView
    lateinit var tvSticker:TextView
    lateinit var btnLogin:Button
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
        btnLogin.setOnClickListener {

        }
    }

    fun updateUI(data:MineHeaderBean){
        if(TextUtils.isEmpty(data.userId)){
            GlideUtils.loadCircleImage(ivIcon,data.userIcon)
            tvName.text = data.userName
            btnLogin.visibility = View.GONE
        }
        tvPack.text = data.packNum.toString()
        tvSticker.text = data.stickerNum.toString()
    }

    override fun initView(view: View) {
        ivIcon = view.findViewById(R.id.iv_icon)
        tvName = view.findViewById(R.id.tv_name)
        tvPack = view.findViewById(R.id.tv_pack)
        tvSticker = view.findViewById(R.id.tv_sticker)
        btnLogin = view.findViewById(R.id.btn_login)
    }

    override fun getLayoutId(): Int {
        return R.layout.view_mine_header
    }

}