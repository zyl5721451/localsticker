package com.henryford.sticker.main.adapter

import android.content.Context
import android.graphics.Color
import androidx.viewpager2.widget.ViewPager2
import com.henryford.sticker.R
import com.henryford.sticker.main.bean.MainIndicatorBean
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView

class MagicIndicatorAdapter : CommonNavigatorAdapter {
    var viewPager: ViewPager2? = null
    var mDatas = ArrayList<MainIndicatorBean>()

    constructor(viewPager: ViewPager2?, mDatas: ArrayList<MainIndicatorBean>) : super() {
        this.viewPager = viewPager
        this.mDatas = mDatas
    }


    override fun getCount(): Int {
        return  mDatas.size
    }

    override fun getTitleView(context: Context?, index: Int): IPagerTitleView? {
        val colorTransitionPagerTitleView = ColorTransitionPagerTitleView(context)
        colorTransitionPagerTitleView.normalColor = Color.GRAY
        colorTransitionPagerTitleView.selectedColor = context?.resources?.getColor(R.color.green)!!
        colorTransitionPagerTitleView.setText(mDatas.get(index).name)
        colorTransitionPagerTitleView.setOnClickListener { viewPager?.setCurrentItem(index) }
        return colorTransitionPagerTitleView
    }

    override fun getIndicator(context: Context?): IPagerIndicator? {
        val indicator = LinePagerIndicator(context)
        indicator.mode = LinePagerIndicator.MODE_WRAP_CONTENT
        indicator.setColors(context?.resources?.getColor(R.color.green)!!)
        return indicator
    }

}