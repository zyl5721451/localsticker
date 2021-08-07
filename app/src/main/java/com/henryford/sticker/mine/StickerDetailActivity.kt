package com.henryford.sticker.mine

import android.content.Intent
import android.widget.Button
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.allen.commlib.arouter.ARouterPage
import com.henryford.sticker.BaseActivity
import com.henryford.sticker.R
import com.henryford.sticker.make.MakeStickerActivity
import com.henryford.sticker.mine.bean.MineStickerBean


@Route(path = ARouterPage.STICKER_DETAIL_ACTIVITY)
class StickerDetailActivity : BaseActivity() {
    lateinit var btnMake:Button
    companion object{
        const val KEY_DATA = "key_data"
    }

    @Autowired(name = KEY_DATA)
    @JvmField
    var stickerList: List<MineStickerBean>? = null
    override fun initToolbar() {
    }

    override fun setListener() {
        btnMake.setOnClickListener {
        }
    }

    override fun initData() {
    }

    override fun initView() {
        btnMake = findViewById(R.id.btn_make)
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_sticker_detail
    }
}