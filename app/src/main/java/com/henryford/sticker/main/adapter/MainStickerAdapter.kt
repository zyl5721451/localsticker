package com.henryford.sticker.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.henryford.sticker.BaseFragment
import com.henryford.sticker.main.ForYouFragment
import com.henryford.sticker.main.StickerListFragment
import com.henryford.sticker.main.bean.MainIndicatorBean
import com.henryford.sticker.util.LogUtil

class MainStickerAdapter :FragmentStateAdapter{
    companion object{
        val FROM = 1
        val FORYOU = 2
        val NORMAL = 3
    }
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
        var mainIndicatorBean = mDatas.get(position)
        var key = mDatas.get(position).id
        var fragment = fragments[key]
        if(fragment!=null){
            LogUtil.d(TAG,"createFragment 缓存"+position)
            return fragment
        }
        LogUtil.d(TAG,"createFragment 新建"+position)
        var fragmentSticker:BaseFragment
        if(mainIndicatorBean.type == FROM){
            fragmentSticker = StickerListFragment.newInstance(mainIndicatorBean)
        }else if(mainIndicatorBean.type == FORYOU){
            fragmentSticker = ForYouFragment.newInstance(mainIndicatorBean)
//            fragmentSticker = StickerListFragment.newInstance(mainIndicatorBean)
        }else {
            fragmentSticker = StickerListFragment.newInstance(mainIndicatorBean)
        }

        fragments.put(key,fragmentSticker)
        return fragmentSticker
    }

}