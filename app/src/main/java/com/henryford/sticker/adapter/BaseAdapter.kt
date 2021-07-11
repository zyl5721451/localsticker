package com.henryford.sticker.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T,H:RecyclerView.ViewHolder> :RecyclerView.Adapter<H>{
    var onItemClickListener:OnItemClickListener? = null
    var onItemLongClickListener:OnItemLongClickListener? = null
    var mDatas:ArrayList<T> = ArrayList()
    constructor() : super()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): H {
       return onStickerCreateViewHolder(parent,viewType)
    }

    open fun addData(data: T?) {
        if (data != null) {
            val start = mDatas.size
            mDatas.add(data)
            if (start == 0) {
                notifyDataSetChanged()
            } else {
                notifyItemRangeInserted(start, 1)
            }
        }
    }

    /**
     * 替代原数据源并刷新
     *
     * @param data
     */
    open fun setData(data: List<T>) {
        if (data == null) {
            return
        }
        mDatas.clear()
        mDatas.addAll(data!!)
        notifyDataSetChanged()
    }



    override fun getItemCount(): Int {
        return mDatas.size
    }

    override fun onBindViewHolder(holder: H, position: Int) {
        onStickerBindViewHolder(holder,position)
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(it,position)
        }
        holder.itemView.setOnLongClickListener {
            if(onItemLongClickListener == null){
                return@setOnLongClickListener false
            }
            return@setOnLongClickListener onItemLongClickListener!!.onItemLongClick(it,position)
        }
    }

    abstract fun onStickerBindViewHolder(holder: H,position: Int)
    abstract fun onStickerCreateViewHolder(parent: ViewGroup, viewType: Int): H


    interface OnItemClickListener{
        fun onItemClick(view: View,position: Int)
    }
    interface OnItemLongClickListener{
        fun onItemLongClick(view: View,position: Int):Boolean
    }
}