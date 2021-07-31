package com.henryford.sticker.main.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.launcher.ARouter
import com.allen.commlib.arouter.ARouterPage
import com.google.android.flexbox.*
import com.henryford.sticker.R
import com.henryford.sticker.adapter.BaseAdapter
import com.henryford.sticker.main.TagDetailActivity
import com.henryford.sticker.main.adapter.FlexBoxAdapter
import com.henryford.sticker.main.bean.TagBean
import com.henryford.sticker.util.LogUtil
import com.henryford.ui.util.ScreenUtil

class TagHeaderView :FrameLayout{
    val TAG = TagHeaderView::class.java.simpleName
    private lateinit var adapter: FlexBoxAdapter
    lateinit var mRecyclerView: RecyclerView

    constructor(context: Context) : super(context){
        init(context)
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){
        init(context)
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        init(context)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes){
        init(context)
    }

    fun init(context: Context){
        var view = LayoutInflater.from(context).inflate(R.layout.view_tag_header,this)
        initView(view)
        initListener()
    }

    fun initView(view:View){
        mRecyclerView = view.findViewById<RecyclerView>(R.id.rv_tag)
        val flexboxLayoutManager = FlexboxLayoutManager(context)
        flexboxLayoutManager.flexWrap = FlexWrap.WRAP
        flexboxLayoutManager.flexDirection = FlexDirection.ROW
        flexboxLayoutManager.justifyContent = JustifyContent.FLEX_START
        flexboxLayoutManager.alignItems = AlignItems.FLEX_START

        adapter = FlexBoxAdapter()

        mRecyclerView.layoutManager = flexboxLayoutManager
        mRecyclerView.adapter = adapter
    }
    fun addTagList(tabBeans:ArrayList<TagBean.InnerTagBean>?){
        tabBeans?.run {
            adapter.setData(tabBeans)
        }

    }

    fun initListener(){
        adapter.onItemClickListener = object :BaseAdapter.OnItemClickListener{
            override fun onItemClick(view: View, position: Int) {
                LogUtil.d(TAG,"onItemClick:"+position)
                ARouter.getInstance().build(ARouterPage.TAG_DETAIL_ACTIVITY)
                    .withObject(TagDetailActivity.KEY_DATA,adapter.mDatas.get(position))
                    .navigation(context)
            }

        }
    }


}