package com.henryford.sticker.make.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.allen.commlib.glide.GlideUtils
import com.henryford.sticker.R
import com.henryford.sticker.adapter.BaseAdapter
import com.henryford.sticker.make.bean.UnAddStickerBean

class MakePackUnAddAdapter : BaseAdapter<UnAddStickerBean, MakePackUnAddAdapter.BaseHolder>() {
    companion object{
        const val TYPE_TITLE = 0
        const val TYPE_ITEM = 1
    }




    open class BaseHolder: RecyclerView.ViewHolder {
        constructor(itemView: View) : super(itemView)
    }

    class ItemHolder:BaseHolder{
        lateinit var ivIcon:ImageView
        lateinit var flMask:FrameLayout
        constructor(itemView: View) : super(itemView){
            ivIcon = itemView.findViewById(R.id.iv_img)
            flMask = itemView.findViewById(R.id.fl_mask)
        }

    }

    class TitleHolder:BaseHolder{
        lateinit var tvTitle:TextView
        constructor(itemView: View) : super(itemView){
            tvTitle = itemView.findViewById(R.id.tv_title)
        }
    }


    override fun onStickerBindViewHolder(holder: BaseHolder, position: Int) {
        var sticker = mDatas.get(position)
        when(getItemViewType(position)){
            TYPE_TITLE -> {
                var hodlerTitle: TitleHolder = holder as TitleHolder
                hodlerTitle.tvTitle.text = sticker.title
            }
            TYPE_ITEM -> {
                var hodlerItem: ItemHolder = holder as ItemHolder
                if(sticker.selected){
                    hodlerItem.flMask.visibility = View.VISIBLE
                }else {
                    hodlerItem.flMask.visibility = View.GONE
                }
                GlideUtils.loadRoundImage(hodlerItem.ivIcon,sticker.path,5)
            }
        }
    }



    override fun onStickerCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        lateinit var  baseHolder:BaseHolder
        when(viewType){
            TYPE_TITLE -> {
                var view = LayoutInflater.from(parent.context).inflate(
                    R.layout.item_make_title,
                    parent,
                    false
                )
                baseHolder = TitleHolder(view)
            }
            TYPE_ITEM -> {
                var view = LayoutInflater.from(parent.context).inflate(
                    R.layout.item_make_pack,
                    parent,
                    false
                )
                baseHolder = ItemHolder(view)
            }
        }
        return baseHolder
    }



    override fun getItemViewType(position: Int): Int {
        return mDatas[position].type
    }
}