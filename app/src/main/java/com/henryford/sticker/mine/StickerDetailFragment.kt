package com.henryford.sticker.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.launcher.ARouter
import com.allen.commlib.arouter.ARouterPage
import com.henryford.sticker.BaseDialogFragment
import com.henryford.sticker.R
import com.henryford.sticker.adapter.BaseAdapter
import com.henryford.sticker.mine.adapter.MineStickerDetailBigAdapter
import com.henryford.sticker.mine.adapter.MineStickerDetailSmallAdapter
import com.henryford.sticker.mine.bean.MineStickerBean
import com.henryford.ui.util.ScreenUtil


class StickerDetailFragment : BaseDialogFragment() {
    lateinit var tvClose:TextView
    lateinit var viewaPagerBig:ViewPager2
    lateinit var flAd:FrameLayout
    lateinit var btnMake:Button
    lateinit var recyclerView:RecyclerView
    lateinit var bigViewPagerAdapter:MineStickerDetailBigAdapter
    lateinit var recyclerViewAdapter:MineStickerDetailSmallAdapter
    lateinit var onPageChangeCallback:ViewPager2.OnPageChangeCallback

    companion object{
        val FRAGMENT_TAG = "sticker_detail"
        val KEY_DATA = "key_data"
        fun newInstance(stickerList: ArrayList<MineStickerBean>): StickerDetailFragment? {
            val f = StickerDetailFragment()
            // Supply num input as an argument.
            val args = Bundle()
            args.putSerializable(KEY_DATA, stickerList)
            f.setArguments(args)
            return f
        }
    }

    var stickerList:ArrayList<MineStickerBean>? = null






    override fun firstLoadData() {
    }

    override fun getRootViewResource(): Int {
        return R.layout.activity_sticker_detail
    }

    override fun initView() {
        tvClose = rootView!!.findViewById(R.id.tv_close)
        viewaPagerBig = rootView!!.findViewById(R.id.view_pager_h)
        recyclerView = rootView!!.findViewById(R.id.recyclerview)
        btnMake = rootView!!.findViewById(R.id.btn_make)
        flAd = rootView!!.findViewById(R.id.fl_ad)

        viewaPagerBig.layoutParams.height = ScreenUtil.getScreenWidth(requireContext())

        (recyclerView.itemAnimator as SimpleItemAnimator?)?.run {
            supportsChangeAnimations = false
            addDuration = 0
            changeDuration = 0
            moveDuration = 0
            removeDuration = 0
        }
    }


    override fun setListener() {
        tvClose.setOnClickListener {
            dismiss()
        }

        btnMake.setOnClickListener {
            ARouter.getInstance().build(ARouterPage.MAKE_ACTIVITY).navigation(context)
        }
        onPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                recyclerView.scrollToPosition(position)
                recyclerViewAdapter.selectCurrentItem(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        }
        viewaPagerBig.registerOnPageChangeCallback(onPageChangeCallback)
        recyclerViewAdapter.onItemClickListener = object :BaseAdapter.OnItemClickListener{
            override fun onItemClick(view: View, position: Int) {
                viewaPagerBig.setCurrentItem(position, true)
            }

        }
    }

    override fun getDialogTheme(): Int {
        return R.style.AppTheme_StickerDetail
    }

    override fun initData() {
        stickerList = arguments?.getSerializable(KEY_DATA) as ArrayList<MineStickerBean>?

        bigViewPagerAdapter = MineStickerDetailBigAdapter()
        bigViewPagerAdapter.addData(stickerList)
        viewaPagerBig.setAdapter(bigViewPagerAdapter)


        recyclerViewAdapter = MineStickerDetailSmallAdapter()
        recyclerViewAdapter.addData(stickerList)
        recyclerView.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        recyclerView.setAdapter(recyclerViewAdapter)

    }



    override fun onDestroy() {
        super.onDestroy()
        viewaPagerBig.unregisterOnPageChangeCallback(onPageChangeCallback)
        recyclerView.clearOnScrollListeners()
    }
}