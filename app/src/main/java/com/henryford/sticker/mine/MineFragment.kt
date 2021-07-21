package com.henryford.sticker.mine

import android.widget.TextView
import androidx.fragment.app.Fragment
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
import com.henryford.sticker.mine.adapter.MineTabAdapter
import com.henryford.sticker.mine.viewmodel.MineViewModel
import com.henryford.sticker.mine.widget.MineHeader
import kotlinx.android.synthetic.main.fragment_mine.*

class MineFragment : BaseFragment() {
    lateinit var mineHeader: MineHeader
    lateinit var tabLayout: TabLayout
    lateinit var viewPager:ViewPager2
    lateinit var mineViewModel:MineViewModel
    companion object{
        val TAG =  MineFragment::class.java.simpleName
    }

    override fun firstLoadData() {

    }

    override fun getRootViewResource(): Int {
        return R.layout.fragment_mine
    }

    override fun initView() {
        mineHeader = rootView!!.findViewById(R.id.mine_header)
        tabLayout = rootView!!.findViewById(R.id.tab)
        viewPager = rootView!!.findViewById(R.id.view_pager)



    }

    override fun initData() {
        mineViewModel = ViewModelProvider(this).get(MineViewModel::class.java)
        mineViewModel.getMineData()

        var tabData = ArrayList<String>()
        tabData.add("Packs")
        tabData.add("Stickers")
        setViewPagerData(tabData)
        setIndicatorData(tabData)
    }

    override fun setListener() {
        mineViewModel.mineHeader.observe(this, Observer {
            mineHeader.updateUI(it)
        })
    }

    private fun setViewPagerData(data:ArrayList<String>) {
        var mainStickerAdapter = MineTabAdapter(activity as FragmentActivity,data)
        viewPager.adapter = mainStickerAdapter
        viewPager.offscreenPageLimit = 2
    }

    private fun setIndicatorData(data:ArrayList<String>) {
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = data[position]
        }.attach()
    }

}