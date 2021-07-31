package com.henryford.sticker.main

import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.allen.commlib.arouter.ARouterPage
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.henryford.sticker.BaseActivity
import com.henryford.sticker.R
import com.henryford.sticker.main.adapter.MainStickerAdapter
import com.henryford.sticker.main.adapter.StickerListAdapter
import com.henryford.sticker.main.bean.MainIndicatorBean
import com.henryford.sticker.main.bean.TagBean
import com.henryford.sticker.main.viewmodel.MainStickerViewModel
import com.henryford.sticker.mine.adapter.MineTabAdapter
import com.henryford.sticker.mine.viewmodel.MineViewModel
import kotlinx.android.synthetic.main.ad_splash.view.*

@Route(path = ARouterPage.TAG_DETAIL_ACTIVITY)
class TagDetailActivity : BaseActivity() {
    companion object{
        const val  KEY_DATA = "key_data"
    }

    private lateinit var mainStickerViewModel: MainStickerViewModel
    private lateinit var stickerListAdapter: StickerListAdapter

    @Autowired(name = KEY_DATA)
    @JvmField
    var tagBean:TagBean.InnerTagBean? = null
    lateinit var toolbar: Toolbar
    lateinit var tabLayout:TabLayout
    lateinit var viewPager:ViewPager2
    override fun initToolbar() {
        toolbar.title = tagBean?.name
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun setListener() {
    }

    override fun initData() {
        mainStickerViewModel = ViewModelProvider(this).get(MainStickerViewModel::class.java)

        var tabData = ArrayList<MainIndicatorBean>()
        tabData.add(MainIndicatorBean(101,"New",MainStickerAdapter.NORMAL))
        tabData.add(MainIndicatorBean(102,"Trending",MainStickerAdapter.NORMAL))
        setViewPagerData(tabData)
        setIndicatorData(tabData)
    }


    private fun setViewPagerData(data:ArrayList<MainIndicatorBean>) {
        var mainStickerAdapter = MainStickerAdapter(this,data)
        viewPager.adapter = mainStickerAdapter
        viewPager.offscreenPageLimit = 2
    }

    private fun setIndicatorData(data:ArrayList<MainIndicatorBean>) {
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = data[position].name
        }.attach()
    }

    override fun initView() {
        toolbar = findViewById(R.id.toolbar)
        tabLayout = findViewById(R.id.tab)
        viewPager = findViewById(R.id.view_pager)
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_tag_detail
    }
}