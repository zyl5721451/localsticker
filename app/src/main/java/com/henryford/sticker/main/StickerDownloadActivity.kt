package com.henryford.sticker.main

import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.allen.commlib.arouter.ARouterPage
import com.henryford.sticker.BaseActivity
import com.henryford.sticker.R
import com.henryford.sticker.main.adapter.StickerListAdapter
import com.henryford.sticker.main.viewmodel.MainStickerViewModel
import com.henryford.sticker.main.widget.StickerDownloadHeaderView
import com.henryford.sticker.main.widget.TagHeaderView
import com.henryford.sticker.util.LogUtil
import com.henryford.sticker.widget.StickerRecycleView
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import kotlinx.android.synthetic.main.ad_splash.view.*

@Route(path = ARouterPage.STICKER_DOWNLOAD_ACTIVITY)
class StickerDownloadActivity : BaseActivity() {
    val TAG = StickerDownloadActivity::class.java.simpleName
    lateinit var toolbar: Toolbar
    lateinit var refreshLayout:SmartRefreshLayout
    lateinit var recyclerView:StickerRecycleView
    private lateinit var stickerListAdapter: StickerListAdapter
    private lateinit var mainStickerViewModel: MainStickerViewModel
    override fun initToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun setListener() {
        mainStickerViewModel.stickerList.observe(this, Observer {
            it.pageInfo?.run {

                if(this.score!=0.0){
                    stickerListAdapter.addData(it.mainStickerList)
                    if(!this.hasMore){
                        refreshLayout.finishLoadMoreWithNoMoreData()
                    }else {
                        refreshLayout.finishLoadMore()
                    }
                }else {
                    stickerListAdapter.setData(it.mainStickerList)
                    refreshLayout.finishRefresh()
//                    refreshLayout.setEnableRefresh(false)
                    if(!this.hasMore){
                        refreshLayout.setEnableLoadMore(false)
                    }else {
                        refreshLayout.setEnableLoadMore(true)
                    }
                }
            }
        })

        mainStickerViewModel.stickerDetail.observe(this, Observer {
            it.sticker?.run {
                var headerView = StickerDownloadHeaderView(this@StickerDownloadActivity)
                headerView.updateUI(this)
                recyclerView?.run {
                    if(this.headerViewCount>0){
                        this.removeHeaderView(0)
                    }
                    this.addHeaderView(headerView)
                }
            }
        })

        refreshLayout.setOnLoadMoreListener {
            mainStickerViewModel.loadStickerList()
        }
        refreshLayout.setOnRefreshListener {
            mainStickerViewModel.loadStickerList()
            mainStickerViewModel.loadStickerDetail()
        }

    }

    override fun initData() {
        mainStickerViewModel = ViewModelProvider(this).get(MainStickerViewModel::class.java)
        refreshLayout.autoRefresh()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_sticker_download,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_report->{
                ARouter.getInstance().build(ARouterPage.REPORT_ACTIVITY).navigation(this)
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun initView() {
        toolbar = findViewById(R.id.toolbar)
        refreshLayout = findViewById(R.id.srf_refresh)
        recyclerView = findViewById(R.id.rv_sticker_list)
        refreshLayout.setRefreshHeader(MaterialHeader(this))
        refreshLayout.setRefreshFooter(ClassicsFooter(this))

        stickerListAdapter = StickerListAdapter()
        recyclerView?.layoutManager = GridLayoutManager(this,2)
        recyclerView?.adapter = stickerListAdapter

    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_sticker_download
    }
}