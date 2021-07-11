package com.henryford.sticker.widget

import android.content.Context
import android.util.AttributeSet
import com.takwolf.android.hfrecyclerview.HeaderAndFooterRecyclerView

class StickerRecycleView:HeaderAndFooterRecyclerView{
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

}