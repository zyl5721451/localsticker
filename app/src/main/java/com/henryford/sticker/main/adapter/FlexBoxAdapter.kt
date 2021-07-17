package com.henryford.sticker.main.adapter

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.henryford.sticker.R
import com.henryford.sticker.adapter.BaseAdapter
import com.henryford.sticker.main.bean.TagBean
import kotlinx.android.synthetic.main.sticker_packs_list_item.view.*

class FlexBoxAdapter :BaseAdapter<TagBean.InnerTagBean,FlexBoxAdapter.ViewHolder>{
    constructor() : super()

    class ViewHolder:RecyclerView.ViewHolder{
        lateinit var tvName:TextView
        constructor(itemView: View) : super(itemView){
            tvName = itemView.findViewById(R.id.tv_tag)
        }
    }

    override fun onStickerBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = mDatas.get(position).name
    }

    override fun onStickerCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_tag,parent,false)
        return ViewHolder(view)
    }
}