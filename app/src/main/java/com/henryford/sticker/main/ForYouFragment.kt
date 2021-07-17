package com.henryford.sticker.main

import android.os.Bundle
import com.henryford.sticker.BaseFragment
import com.henryford.sticker.R
import com.henryford.sticker.main.bean.MainIndicatorBean
import kotlinx.android.synthetic.main.activity_sticker_pack_list.view.*

class ForYouFragment: BaseFragment() {

    companion object{
        val ARGUMENT_KEY = "argument_key"
        fun newInstance(mainIndicatorBean: MainIndicatorBean):StickerListFragment{
            var fragment = StickerListFragment()
            var bundle = Bundle()
            bundle.putSerializable(ARGUMENT_KEY,mainIndicatorBean)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun firstLoadData() {
    }

    override fun getRootViewResource(): Int {
        return R.layout.fragment_sticker_list
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun setListener() {

    }
}