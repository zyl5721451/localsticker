package com.henryford.ui.bottomnavigation

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.henryford.ui.R

class BottomNavigationBar :FrameLayout,View.OnClickListener{
    lateinit var itemSticker:BottomNavigationItemView
    lateinit var itemMake:BottomNavigationItemView
    lateinit var itemMine:BottomNavigationItemView
    var onNavigationItemSelectedListener: OnNavigationItemSelectedListener? = null
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
        var view = LayoutInflater.from(context).inflate(R.layout.layout_bottom_navigation,this)
        itemSticker = view.findViewById(R.id.biv_sticker)
        itemMake = view.findViewById(R.id.biv_make)
        itemMine = view.findViewById(R.id.biv_mine)
        itemSticker.setOnClickListener(this)
        itemMake.setOnClickListener(this)
        itemMine.setOnClickListener(this)
    }

    interface OnNavigationItemSelectedListener{
        fun onSelect(type:BottomNavigationItemView.BottomItemType)
    }

    private fun updateState(type: BottomNavigationItemView.BottomItemType){
        when(type){
            BottomNavigationItemView.BottomItemType.Sticker ->{
                itemSticker.updateState(true)
                itemMake.updateState(false)
                itemMine.updateState(false)
            }
            BottomNavigationItemView.BottomItemType.Make ->{
//                itemSticker.updateState(false)
//                itemMake.updateState(true)
//                itemMine.updateState(false)
            }
            BottomNavigationItemView.BottomItemType.Mine ->{
                itemSticker.updateState(false)
                itemMake.updateState(false)
                itemMine.updateState(true)
            }
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.biv_sticker ->{
                updateState(BottomNavigationItemView.BottomItemType.Sticker)
                onNavigationItemSelectedListener?.onSelect(BottomNavigationItemView.BottomItemType.Sticker)
            }
            R.id.biv_make ->{
                updateState(BottomNavigationItemView.BottomItemType.Make)
                onNavigationItemSelectedListener?.onSelect(BottomNavigationItemView.BottomItemType.Make)
            }
            R.id.biv_mine ->{
                updateState(BottomNavigationItemView.BottomItemType.Mine)
                onNavigationItemSelectedListener?.onSelect(BottomNavigationItemView.BottomItemType.Mine)
            }
        }
    }

}