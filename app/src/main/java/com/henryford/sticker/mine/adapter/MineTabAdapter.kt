package com.henryford.sticker.mine.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.henryford.sticker.BaseFragment
import com.henryford.sticker.mine.MinePackFragment
import com.henryford.sticker.mine.MineStickerFragment
import com.henryford.sticker.util.LogUtil

class MineTabAdapter : FragmentStateAdapter {
    var mDatas = ArrayList<String>()
    var fragments = mutableMapOf<String,Fragment>()
    val TAG = MineTabAdapter::class.java.simpleName

    constructor(fragmentActivity: FragmentActivity, mDatas: ArrayList<String>) : super(
        fragmentActivity
    ) {
        this.mDatas = mDatas
    }

    constructor(fragment: Fragment, mDatas: ArrayList<String>) : super(fragment) {
        this.mDatas = mDatas
    }

    constructor(
        fragmentManager: FragmentManager,
        lifecycle: Lifecycle,
        mDatas: ArrayList<String>
    ) : super(fragmentManager, lifecycle) {
        this.mDatas = mDatas
    }


    override fun getItemCount(): Int {
        return mDatas.size
    }

    override fun createFragment(position: Int): Fragment {
        var name = mDatas.get(position)
        var fragment = fragments[name]
        if(fragment!=null){
            LogUtil.d(TAG,"createFragment 缓存"+position)
            return fragment
        }
        LogUtil.d(TAG,"createFragment 新建"+position)
        var fragmentSticker:BaseFragment
        if(position == 0){
            fragmentSticker = MinePackFragment.newInstance()
        }else {
            fragmentSticker = MineStickerFragment.newInstance()
        }
        fragments.put(name,fragmentSticker)
        return fragmentSticker
    }


}