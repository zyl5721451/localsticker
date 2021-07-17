package com.henryford.sticker.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.henryford.sticker.BaseFragment
import com.henryford.sticker.main.StickerListFragment
import com.henryford.sticker.main.bean.MainIndicatorBean
import com.henryford.sticker.util.LogUtil

class MainStickerAdapter :FragmentStateAdapter{
    var mDatas = ArrayList<MainIndicatorBean>()
    var fragments = mutableMapOf<Long,Fragment>()
    val TAG = MainStickerAdapter::class.java.simpleName

    constructor(fragmentActivity: FragmentActivity, mDatas: ArrayList<MainIndicatorBean>) : super(
        fragmentActivity
    ) {
        this.mDatas = mDatas
    }

    constructor(fragment: Fragment, mDatas: ArrayList<MainIndicatorBean>) : super(fragment) {
        this.mDatas = mDatas
    }

    constructor(
        fragmentManager: FragmentManager,
        lifecycle: Lifecycle,
        mDatas: ArrayList<MainIndicatorBean>
    ) : super(fragmentManager, lifecycle) {
        this.mDatas = mDatas
    }

    override fun getItemCount(): Int {
        return mDatas.size
    }


    override fun createFragment(position: Int): Fragment {
        var key = mDatas.get(position).id
        var fragment = fragments[key]
        if(fragment!=null){
            LogUtil.d(TAG,"createFragment 缓存"+position)
            return fragment
        }
        LogUtil.d(TAG,"createFragment 新建"+position)
        var fragmentSticker:BaseFragment
        if(position == 1){
            fragmentSticker = StickerListFragment.newInstance(mDatas.get(position))
        }else{
            fragmentSticker = StickerListFragment.newInstance(mDatas.get(position))
        }

        fragments.put(key,fragmentSticker)
        return fragmentSticker
    }

}