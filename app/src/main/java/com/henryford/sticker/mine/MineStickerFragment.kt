package com.henryford.sticker.mine

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.henryford.sticker.BaseFragment
import com.henryford.sticker.R
import com.henryford.sticker.mine.adapter.MinePackAdapter
import com.henryford.sticker.mine.adapter.MineStickerAdapter
import com.henryford.sticker.mine.viewmodel.MineViewModel
import com.henryford.sticker.widget.StickerRecycleView
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import kotlinx.android.synthetic.main.activity_sticker_pack_list.view.*

class MineStickerFragment : BaseFragment() {
    private lateinit var refreshLayout: SmartRefreshLayout
    private lateinit var mineStickerAdapter: MineStickerAdapter
    private lateinit var mineViewModel: MineViewModel
    val TAG = MineStickerFragment::class.java.simpleName
    var rvRecyclerView: StickerRecycleView? = null
    companion object{
        fun newInstance(): MineStickerFragment {
            var fragment = MineStickerFragment()
            return fragment
        }
    }
    override fun firstLoadData() {
    }

    override fun getRootViewResource(): Int {
        return R.layout.fragment_mine_sticker
    }

    override fun initView() {
        rvRecyclerView = rootView!!.findViewById(R.id.rv_sticker_list)
        refreshLayout = rootView!!.findViewById(R.id.srf_refresh)
        refreshLayout.setRefreshHeader(MaterialHeader(context))
        refreshLayout.setRefreshFooter(ClassicsFooter(context))


        mineStickerAdapter = MineStickerAdapter()
        rvRecyclerView?.layoutManager = GridLayoutManager(context,3)
        rvRecyclerView?.adapter = mineStickerAdapter
    }

    override fun initData() {
        mineViewModel = ViewModelProvider(this).get(MineViewModel::class.java)
        refreshLayout.autoRefresh()
    }

    override fun setListener() {
        mineViewModel.stickerList.observe(this, Observer {
            it.pageInfo?.run {

                if(this.score!=0.0){
                    mineStickerAdapter.addData(it.mineStickerkList)
                    if(!this.hasMore){
                        refreshLayout.finishLoadMoreWithNoMoreData()
                    }else {
                        refreshLayout.finishLoadMore()
                    }
                }else {
                    mineStickerAdapter.setData(it.mineStickerkList)
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
            mineViewModel.getStickerList()
        }
        refreshLayout.setOnLoadMoreListener {

        }
    }


}