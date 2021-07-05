package com.henryford.ui.bottomnavigation

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.henryford.ui.R
import com.henryford.ui.util.ScreenUtil

class BottomNavigationItemView : FrameLayout {
    lateinit var tvName :TextView
    lateinit var ivIcon:ImageView
    var checked:Boolean = false
    var type:BottomItemType = BottomItemType.Sticker
    constructor(context: Context) : super(context) {
        init(context,null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context,attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context,attrs)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context,attrs)
    }


    fun init(context: Context,attrs: AttributeSet?){
        var enumType = 0
        attrs?.run {
            val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.BottomNavigationItemView)
            enumType = typedArray.getInt(R.styleable.BottomNavigationItemView_bnviType,0)
            checked = typedArray.getBoolean(R.styleable.BottomNavigationItemView_bnviChecked,false)
        }


        var view = LayoutInflater.from(context).inflate(R.layout.item_bottom_navigation,this)
        tvName = view.findViewById(R.id.tv_name)
        ivIcon = view.findViewById(R.id.iv_icon)
        when(enumType){
            0 ->{
                type = BottomItemType.Sticker
                tvName.text = "Sticker"
                if(checked){
                    ivIcon.setImageResource(R.drawable.icon_discover_s)
                    tvName.setTextColor(resources.getColor(R.color.green))
                }else {
                    ivIcon.setImageResource(R.drawable.icon_discover_n)
                    tvName.setTextColor(resources.getColor(R.color.gray))
                }

            }
            1 ->{
                type = BottomItemType.Make
                ivIcon.setImageResource(R.drawable.icon_maker)
                ivIcon.layoutParams.width = ScreenUtil.dp2Px(getContext(),40.0f)
                ivIcon.layoutParams.height = ScreenUtil.dp2Px(getContext(),40.0f)
                tvName.visibility = View.GONE
            }
            2 ->{
                type = BottomItemType.Mine
                tvName.text = "Mine"
                if(checked){
                    ivIcon.setImageResource(R.drawable.icon_mine_s)
                    tvName.setTextColor(resources.getColor(R.color.green))
                }else {
                    ivIcon.setImageResource(R.drawable.icon_mine_n)
                    tvName.setTextColor(resources.getColor(R.color.gray))
                }
            }


        }
    }

    fun updateState(checked:Boolean) {
        when (type) {
            BottomItemType.Sticker -> {
                if (checked) {
                    ivIcon.setImageResource(R.drawable.icon_discover_s)
                    tvName.setTextColor(resources.getColor(R.color.green))
                } else {
                    ivIcon.setImageResource(R.drawable.icon_discover_n)
                    tvName.setTextColor(resources.getColor(R.color.gray))
                }

            }
            BottomItemType.Make-> {

            }
            BottomItemType.Mine -> {
                if (checked) {
                    ivIcon.setImageResource(R.drawable.icon_mine_s)
                    tvName.setTextColor(resources.getColor(R.color.green))
                } else {
                    ivIcon.setImageResource(R.drawable.icon_mine_n)
                    tvName.setTextColor(resources.getColor(R.color.gray))
                }
            }

        }
    }


    enum class BottomItemType{
        Sticker,
        Make,
        Mine
    }


}