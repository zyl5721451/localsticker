package com.henryford.sticker.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout

abstract class BaseCustomView:FrameLayout {
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
    private fun init(context: Context){
        var view  = LayoutInflater.from(context).inflate(getLayoutId(),this)
        initView(view)
        initListener()
    }

    abstract fun initListener()

    abstract fun initView(view: View)

    abstract fun getLayoutId():Int


}