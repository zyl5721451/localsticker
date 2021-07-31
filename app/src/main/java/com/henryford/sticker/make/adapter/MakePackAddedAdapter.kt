package com.henryford.sticker.make.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.allen.commlib.glide.GlideUtils
import com.henryford.sticker.R
import com.henryford.sticker.StickerApplication
import com.henryford.sticker.adapter.BaseAdapter
import com.henryford.sticker.make.bean.UnAddStickerBean
import com.henryford.ui.util.ScreenUtil

class MakePackAddedAdapter : BaseAdapter<UnAddStickerBean, MakePackAddedAdapter.ViewHolder>() {

    class ViewHolder:RecyclerView.ViewHolder{
        lateinit var ivIcon:ImageView
        constructor(itemView: View) : super(itemView){
            ivIcon = itemView.findViewById(R.id.iv_img)
        }
    }

    override fun onStickerBindViewHolder(holder: ViewHolder, position: Int) {

        GlideUtils.loadRoundImage(holder.ivIcon,mDatas[position].path,5)
    }

    override fun onStickerCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_make_added,
            parent,
            false
        )
        return ViewHolder(view)
    }
}