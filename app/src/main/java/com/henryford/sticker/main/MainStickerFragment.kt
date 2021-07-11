package com.henryford.sticker.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.henryford.sticker.BaseFragment
import com.henryford.sticker.R
import com.henryford.sticker.main.adapter.MagicIndicatorAdapter
import com.henryford.sticker.main.adapter.MainStickerAdapter
import com.henryford.sticker.main.bean.MainIndicatorBean
import com.henryford.sticker.main.viewmodel.MainStickerViewModel
import com.henryford.sticker.util.LogUtil
import com.henryford.sticker.util.ViewPager2Helper
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator


class MainStickerFragment : BaseFragment() {
    lateinit  var viewPager: ViewPager2
    lateinit var magicIndicator: MagicIndicator
    private lateinit var mainStickerViewModel: MainStickerViewModel
    companion object{
        val TAG =  MainStickerFragment::class.java.simpleName
    }

    override fun getRootViewResource(): Int {
        return R.layout.fragment_main_sticker
    }

    override fun initView() {
        magicIndicator = rootView.findViewById<MagicIndicator>(R.id.magic_indicator)
        viewPager = rootView.findViewById<ViewPager2>(R.id.view_pager)

    }

    override fun initData() {
        mainStickerViewModel = ViewModelProvider(this).get(MainStickerViewModel::class.java)
        mainStickerViewModel.loadIndicatorList()
        initIndicator(viewPager, magicIndicator)
    }

    override fun setListener() {

    }

    private fun initIndicator(
        view_pager: ViewPager2,
        magicIndicator: MagicIndicator
    ) {
        mainStickerViewModel.indicatorDataList.observe(this, Observer {
            LogUtil.d(TAG, "indicatorList:" + it)
            setViewPagerData(it, view_pager)
            setIndicatorData(view_pager, it, magicIndicator)
        })

        ViewPager2Helper.bind(magicIndicator, view_pager)
    }

    private fun setViewPagerData(
        it: ArrayList<MainIndicatorBean>,
        view_pager: ViewPager2
    ) {
        var mainStickerAdapter = MainStickerAdapter(activity as FragmentActivity, it)
        view_pager.adapter = mainStickerAdapter
    }

    private fun setIndicatorData(
        view_pager: ViewPager2,
        it: ArrayList<MainIndicatorBean>,
        magicIndicator: MagicIndicator
    ) {
        val commonNavigator = CommonNavigator(context)
        commonNavigator.adapter = MagicIndicatorAdapter(view_pager, it)
        magicIndicator.navigator = commonNavigator
    }


}