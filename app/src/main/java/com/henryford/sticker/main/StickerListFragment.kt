package com.henryford.sticker.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.allen.commlib.arouter.ARouterPage
import com.henryford.sticker.BaseFragment
import com.henryford.sticker.R
import com.henryford.sticker.adapter.BaseAdapter
import com.henryford.sticker.main.adapter.StickerListAdapter
import com.henryford.sticker.main.bean.MainIndicatorBean
import com.henryford.sticker.main.bean.TagBean
import com.henryford.sticker.main.viewmodel.MainStickerViewModel
import com.henryford.sticker.main.widget.TagHeaderView
import com.henryford.sticker.util.LocalDataManager.prepareDefaultSticker
import com.henryford.sticker.util.LogUtil
import com.henryford.sticker.widget.StickerRecycleView
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import kotlinx.android.synthetic.main.fragment_sticker_list.*

class StickerListFragment : BaseFragment() {
    private lateinit var refreshLayout: SmartRefreshLayout
    private lateinit var stickerListAdapter: StickerListAdapter
    private lateinit var mainStickerViewModel: MainStickerViewModel
    val TAG = StickerListFragment::class.java.simpleName
    var mainIndicatorBean: MainIndicatorBean? = null
    var rvRecyclerView:StickerRecycleView? = null
    override fun getRootViewResource(): Int {
        return R.layout.fragment_sticker_list
    }
    companion object{
        val ARGUMENT_KEY = "argument_key"
        fun newInstance(mainIndicatorBean: MainIndicatorBean):StickerListFragment{
            var fragment = StickerListFragment()
            var bundle = Bundle()
            bundle.putSerializable(ARGUMENT_KEY, mainIndicatorBean)
            fragment.arguments = bundle
            return fragment
        }
    }


    override fun initView() {
        rvRecyclerView = rootView!!.findViewById(R.id.rv_sticker_list)
        refreshLayout = rootView!!.findViewById(R.id.srf_refresh)
        refreshLayout.setRefreshHeader(MaterialHeader(context))
        refreshLayout.setRefreshFooter(ClassicsFooter(context))


        stickerListAdapter = StickerListAdapter()
        rvRecyclerView?.layoutManager = GridLayoutManager(context, 2)
//        rvRecyclerView?.addItemDecoration(GridSpacingItemDecoration(2,
//            context?.let { ScreenUtil.dp2Px(it,15.0f) }!!,true))
        rvRecyclerView?.adapter = stickerListAdapter

    }

    override fun initData() {
        mainStickerViewModel = ViewModelProvider(this).get(MainStickerViewModel::class.java)
        mainIndicatorBean = arguments?.getSerializable(ARGUMENT_KEY) as MainIndicatorBean?

        mainStickerViewModel.tagList.observe(this,
            Observer<TagBean> {
                var headerView = TagHeaderView(requireContext())
                headerView.addTagList(it!!.tagList)
                rvRecyclerView?.run {
                    if (this.headerViewCount > 0) {
                        this.removeHeaderView(0)
                    }
                    this.addHeaderView(headerView)
                }
            })

        mainStickerViewModel.stickerList.observe(this, Observer {
            it.pageInfo?.run {

                if (this.score != 0.0) {
                    stickerListAdapter.addData(it.mainStickerList)
                    if (!this.hasMore) {
                        refreshLayout.finishLoadMoreWithNoMoreData()
                    } else {
                        refreshLayout.finishLoadMore()
                    }
                } else {
                    stickerListAdapter.setData(it.mainStickerList)
                    refreshLayout.finishRefresh()
                    if (!this.hasMore) {
                        refreshLayout.setEnableLoadMore(false)
                    } else {
                        refreshLayout.setEnableLoadMore(true)
                    }
                }
            }
        })
        LogUtil.d(TAG, "initData" + mainIndicatorBean?.name)
        refreshLayout.autoRefresh()
    }

    override fun setListener() {
        refreshLayout.setOnRefreshListener {
            mainStickerViewModel.loadTagBeanList()
            mainStickerViewModel.loadStickerList()
        }
        refreshLayout.setOnLoadMoreListener {
            mainStickerViewModel.loadStickerList()
        }

        stickerListAdapter.onItemClickListener = object :BaseAdapter.OnItemClickListener{
            override fun onItemClick(view: View, position: Int) {
                ARouter.getInstance().build(ARouterPage.STICKER_DOWNLOAD_ACTIVITY).navigation(
                    context
                )
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        LogUtil.d(TAG, "onCreateView" + savedInstanceState)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroy() {
        LogUtil.d(TAG, "onDestroy" + mainIndicatorBean?.name)
        super.onDestroy()
    }

    override fun onDestroyView() {
        LogUtil.d(TAG, "onDestroyView" + mainIndicatorBean?.name)
        super.onDestroyView()
    }

    override fun onResume() {
        LogUtil.d(TAG, "onResume" + mainIndicatorBean?.name)
        super.onResume()
    }



    override fun firstLoadData() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        LogUtil.d(TAG, "onCreate" + mainIndicatorBean?.name)
        super.onCreate(savedInstanceState)
    }
}