package com.henryford.sticker.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.henryford.sticker.BaseFragment
import com.henryford.sticker.R
import com.henryford.sticker.main.adapter.StickerListAdapter
import com.henryford.sticker.main.bean.MainIndicatorBean
import com.henryford.sticker.main.bean.TagBean
import com.henryford.sticker.main.viewmodel.MainStickerViewModel
import com.henryford.sticker.main.widget.TagHeaderView
import com.henryford.sticker.util.LogUtil
import com.henryford.sticker.widget.StickerRecycleView
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshHeader
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
            bundle.putSerializable(ARGUMENT_KEY,mainIndicatorBean)
            fragment.arguments = bundle
            return fragment
        }
    }


    override fun initView() {
        rvRecyclerView = rootView.findViewById(R.id.rv_sticker_list)
        refreshLayout = rootView.findViewById<SmartRefreshLayout>(R.id.srf_refresh)
        refreshLayout.setRefreshHeader(MaterialHeader(context))
        refreshLayout.setRefreshFooter(ClassicsFooter(context))

        stickerListAdapter = StickerListAdapter()
        rvRecyclerView?.adapter = stickerListAdapter

    }

    override fun initData() {
        mainStickerViewModel = ViewModelProvider(this).get(MainStickerViewModel::class.java)
        mainIndicatorBean = arguments?.getSerializable(ARGUMENT_KEY) as MainIndicatorBean?

        mainStickerViewModel.tagList.observe(this,
            Observer<TagBean> {
                LogUtil.d(TAG, "taglist:" + it)
//                var headerView = TagHeaderView(requireContext())
//                headerView.addTagList(it)
//                rvRecyclerView?.addHeaderView(headerView)
                refreshLayout.finishRefresh()
            })

        mainStickerViewModel.stickerList.observe(this, Observer {
            stickerListAdapter.setData(it.mainStickerList)
            refreshLayout.finishRefresh()
        })
        LogUtil.d(TAG,"initData"+mainIndicatorBean?.name)
    }

    override fun setListener() {
        refreshLayout.setOnRefreshListener {
            mainStickerViewModel.loadTagBeanList()
            mainStickerViewModel.loadStickerList()
        }
        refreshLayout.setOnLoadMoreListener {

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        LogUtil.d(TAG,"onCreateView"+savedInstanceState)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroy() {
        LogUtil.d(TAG,"onDestroy"+mainIndicatorBean?.name)
        super.onDestroy()
    }

    override fun onDestroyView() {
        LogUtil.d(TAG,"onDestroyView"+mainIndicatorBean?.name)
        super.onDestroyView()
    }

    override fun onResume() {
        LogUtil.d(TAG,"onResume"+mainIndicatorBean?.name)
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        LogUtil.d(TAG,"onCreate"+mainIndicatorBean?.name)
        super.onCreate(savedInstanceState)
    }
}