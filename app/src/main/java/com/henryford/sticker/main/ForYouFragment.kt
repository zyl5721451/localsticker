package com.henryford.sticker.main

import android.os.Bundle
import android.view.View
import android.widget.TextView
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
import com.henryford.sticker.main.bean.TagBean
import com.henryford.sticker.main.viewmodel.MainStickerViewModel
import com.henryford.sticker.util.LogUtil

class ForYouFragment: BaseFragment() {
    val TAG =  ForYouFragment::class.java.simpleName
    private lateinit var mainStickerViewModel: MainStickerViewModel
    lateinit var tabLayout: TabLayout
    lateinit var viewPager:ViewPager2
    companion object{
        val ARGUMENT_KEY = "argument_key"
        fun newInstance(mainIndicatorBean: MainIndicatorBean):ForYouFragment{
            var fragment = ForYouFragment()
            var bundle = Bundle()
            bundle.putSerializable(ARGUMENT_KEY,mainIndicatorBean)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun firstLoadData() {
    }

    override fun getRootViewResource(): Int {
        return R.layout.fragment_for_you
    }

    override fun initView() {
        tabLayout = rootView!!.findViewById(R.id.tab)
        viewPager = rootView!!.findViewById(R.id.view_pager)
    }

    override fun initData() {
        mainStickerViewModel = ViewModelProvider(this).get(MainStickerViewModel::class.java)
        mainStickerViewModel.loadForYouIndicatorList()
    }

    override fun setListener() {
        mainStickerViewModel.indicatorDataList.observe(this, Observer {
            LogUtil.d(MainStickerFragment.TAG, "indicatorList:" + it)
            setViewPagerData(it, viewPager)
            setIndicatorData(it)
        })
        tabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.run {

                    var textView = tab.customView!!.findViewById<TextView>(android.R.id.text1)
                    textView.setTextColor(resources.getColor(R.color.green))
                    textView.setBackgroundResource(R.drawable.bg_tag_foryou_select)
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.run {

                    var textView = tab.customView!!.findViewById<TextView>(android.R.id.text1)
                    textView.setTextColor(resources.getColor(R.color.black))
                    textView.setBackgroundResource(R.drawable.bg_tag_foryou_unselect)
                }
            }
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
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setCustomView(R.layout.view_tab_textview)
            var textView = tab.customView!!.findViewById<TextView>(android.R.id.text1)
            textView.setTextColor(resources.getColor(R.color.black))
            textView.setBackgroundResource(R.drawable.bg_tag_foryou_unselect)
            tab.text = it[position].name
        }.attach()
    }
}