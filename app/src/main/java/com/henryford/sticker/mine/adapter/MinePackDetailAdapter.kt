package com.henryford.sticker.mine.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.allen.commlib.glide.GlideUtils
import com.henryford.sticker.R
import com.henryford.sticker.adapter.BaseAdapter
import com.henryford.sticker.main.bean.MainStickerBean
import com.henryford.sticker.mine.bean.MinePackBean
import com.henryford.sticker.mine.bean.MineStickerBean
import com.henryford.sticker.util.LogUtil

class MinePackDetailAdapter :BaseAdapter<String,MinePackDetailAdapter.ViewHolder>{
    val TAG = MinePackDetailAdapter::class.java.simpleName
    constructor() : super()



    class ViewHolder: RecyclerView.ViewHolder {
        lateinit var ivIcon :ImageView
        constructor(itemView: View) : super(itemView){
            ivIcon = itemView.findViewById(R.id.iv_img)
        }

    }

    override fun onStickerBindViewHolder(holder: ViewHolder, position: Int) {
        LogUtil.d(TAG,"onStickerBindViewHolder:"+position)
        var data = mDatas[position]
        if(!TextUtils.isEmpty(data)){
            GlideUtils.loadImage(holder.ivIcon,data)
        }
    }

    override fun onStickerCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_mine_sticker,parent,false)
        return ViewHolder(view)
    }


}