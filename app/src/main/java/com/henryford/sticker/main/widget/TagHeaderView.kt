package com.henryford.sticker.main.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.google.android.flexbox.FlexboxLayout
import com.henryford.sticker.R
import com.henryford.sticker.main.bean.TagBean
import com.henryford.ui.util.ScreenUtil

class TagHeaderView :FrameLayout{
    lateinit var mFlexBoxLayout: FlexboxLayout

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
        mFlexBoxLayout = view.findViewById<FlexboxLayout>(R.id.flexboxlayout)

    }
    fun addTagList(tabBean:TagBean?){
        tabBean?.run {
            tabBean.tagList.forEach {
                var textView = TextView(context)
                textView.text = it.name
                textView.setTextColor(context.resources.getColor(R.color.black))
                textView.textSize = ScreenUtil.dp2Px(context,14.0f).toFloat()
                mFlexBoxLayout.addView(textView)
            }
        }
    }

    fun initListener(){

    }


}