package com.henryford.sticker.mine.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.allen.commlib.glide.GlideUtils
import com.henryford.sticker.R
import com.henryford.sticker.adapter.BaseAdapter
import com.henryford.sticker.mine.bean.MineStickerBean
import com.henryford.sticker.util.LogUtil

class MineStickerDetailSmallAdapter :BaseAdapter<MineStickerBean, MineStickerDetailSmallAdapter.ViewHolder>{
    val TAG = MineStickerDetailSmallAdapter::class.java.simpleName

    var selectedPosition = -1024
    constructor() : super()



    class ViewHolder: RecyclerView.ViewHolder {
        lateinit var ivIcon :ImageView
        lateinit var viewStroke:View
        constructor(itemView: View) : super(itemView){
            ivIcon = itemView.findViewById(R.id.iv_img)
            viewStroke = itemView.findViewById(R.id.view_stroke)
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        LogUtil.d(TAG, "onStickerBindViewHolder:" + position+":"+selectedPosition)
        var data = mDatas[position]
        if(!TextUtils.isEmpty(data.icon)){
            GlideUtils.loadImage(holder.ivIcon, data.icon)
//            holder.ivIcon.setImageResource(R.drawable.sticker_3)
        }
        if(selectedPosition == position){
            holder.viewStroke.visibility = View.VISIBLE
        }else {
            holder.viewStroke.visibility = View.GONE
        }
        holder.itemView.setOnClickListener {
            selectCurrentItem(position)

            onItemClickListener?.onItemClick(it, position)

        }
    }

    fun selectCurrentItem(position: Int) {
        notifyItemChanged(selectedPosition)//刷新以前item 回置状态
        selectedPosition = position //选择的position赋值给参数，
        notifyItemChanged(selectedPosition)//刷新当前点击item
    }

    override fun onStickerBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun onStickerCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_mine_sticker_detail_small,
            parent,
            false
        )
        return ViewHolder(view)
    }


}