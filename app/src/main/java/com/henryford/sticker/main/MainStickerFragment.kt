package com.henryford.sticker.main

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.henryford.sticker.BaseFragment
import com.henryford.sticker.R
import com.henryford.sticker.main.adapter.MainStickerAdapter
import com.henryford.sticker.main.bean.MainIndicatorBean
import com.henryford.sticker.main.viewmodel.MainStickerViewModel
import com.henryford.sticker.util.LogUtil
import kotlinx.android.synthetic.main.view_tab_textview.view.*


class MainStickerFragment : BaseFragment() {
    lateinit  var viewPager: ViewPager2
    lateinit var magicIndicator: TabLayout
    private lateinit var mainStickerViewModel: MainStickerViewModel
    companion object{
        val TAG =  MainStickerFragment::class.java.simpleName
    }

    override fun firstLoadData() {

    }


    override fun getRootViewResource(): Int {
        return R.layout.fragment_main_sticker
    }

    override fun initView() {
        magicIndicator = rootView!!.findViewById<TabLayout>(R.id.magic_indicator)
        viewPager = rootView!!.findViewById<ViewPager2>(R.id.view_pager)

    }

    override fun initData() {
        mainStickerViewModel = ViewModelProvider(this).get(MainStickerViewModel::class.java)
        mainStickerViewModel.loadIndicatorList()
    }

    override fun setListener() {
        mainStickerViewModel.indicatorDataList.observe(this, Observer {
            LogUtil.d(TAG, "indicatorList:" + it)
            setViewPagerData(it, viewPager)
            setIndicatorData(it)
        })
    }

    private fun setViewPagerData(
        it: ArrayList<MainIndicatorBean>,
        view_pager: ViewPager2
    ) {
        var mainStickerAdapter = MainStickerAdapter(activity as FragmentActivity, it)
        view_pager.adapter = mainStickerAdapter
        view_pager.offscreenPageLimit = it.size
    }

    private fun setIndicatorData(it: ArrayList<MainIndicatorBean>) {
        TabLayoutMediator(magicIndicator, viewPager) { tab, position ->
            tab.text = it[position].name
        }.attach()
    }


}