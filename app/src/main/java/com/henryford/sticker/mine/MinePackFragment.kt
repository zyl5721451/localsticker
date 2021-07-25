package com.henryford.sticker.mine

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.allen.commlib.arouter.ARouterPage
import com.henryford.sticker.BaseFragment
import com.henryford.sticker.R
import com.henryford.sticker.adapter.BaseAdapter
import com.henryford.sticker.main.StickerListFragment
import com.henryford.sticker.main.adapter.StickerListAdapter
import com.henryford.sticker.main.bean.MainIndicatorBean
import com.henryford.sticker.main.viewmodel.MainStickerViewModel
import com.henryford.sticker.mine.adapter.MinePackAdapter
import com.henryford.sticker.mine.viewmodel.MineViewModel
import com.henryford.sticker.widget.StickerRecycleView
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout

class MinePackFragment : BaseFragment() {
    private lateinit var refreshLayout: SmartRefreshLayout
    private lateinit var minePackAdapter: MinePackAdapter
    private lateinit var mineViewModel: MineViewModel
    val TAG = MinePackFragment::class.java.simpleName
    var rvRecyclerView: StickerRecycleView? = null
    companion object{
        fun newInstance(): MinePackFragment {
            var fragment = MinePackFragment()
            return fragment
        }
    }
    override fun firstLoadData() {
    }

    override fun getRootViewResource(): Int {
        return R.layout.fragment_mine_pack
    }

    override fun initView() {
        rvRecyclerView = rootView!!.findViewById(R.id.rv_sticker_list)
        refreshLayout = rootView!!.findViewById(R.id.srf_refresh)
        refreshLayout.setRefreshHeader(MaterialHeader(context))
        refreshLayout.setRefreshFooter(ClassicsFooter(context))


        minePackAdapter = MinePackAdapter()
        rvRecyclerView?.layoutManager = LinearLayoutManager(context)
        rvRecyclerView?.adapter = minePackAdapter
    }

    override fun initData() {
        mineViewModel = ViewModelProvider(this).get(MineViewModel::class.java)
        refreshLayout.autoRefresh()
    }

    override fun setListener() {
        minePackAdapter.onItemClickListener = object :BaseAdapter.OnItemClickListener{
            override fun onItemClick(view: View, position: Int) {
                ARouter.getInstance()
                    .build(ARouterPage.PACK_DETAIL_ACTIVITY)
                    .withObject(PackDetailActivity.KEY_PACK,minePackAdapter.mDatas[position])
                    .navigation(context)
            }

        }
        mineViewModel.packList.observe(this, Observer {
            it.pageInfo?.run {

                if(this.score!=0.0){
                    minePackAdapter.addData(it.minePackList)
                    if(!this.hasMore){
                        refreshLayout.finishLoadMoreWithNoMoreData()
                    }else {
                        refreshLayout.finishLoadMore()
                    }
                }else {
                    minePackAdapter.setData(it.minePackList)
                    refreshLayout.finishRefresh()
                    if(!this.hasMore){
                        refreshLayout.setEnableLoadMore(false)
                    }else {
                        refreshLayout.setEnableLoadMore(true)
                    }
                }
            }
        })

        refreshLayout.setOnRefreshListener {
            mineViewModel.getPackList()
        }
        refreshLayout.setOnLoadMoreListener {

        }
    }

}