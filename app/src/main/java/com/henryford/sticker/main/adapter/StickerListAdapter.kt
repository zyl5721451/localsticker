package com.henryford.sticker.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.henryford.sticker.R
import com.henryford.sticker.adapter.BaseAdapter
import com.henryford.sticker.main.bean.MainStickerBean
import com.henryford.sticker.util.LogUtil
import kotlinx.android.synthetic.main.activity_test_ad.*

class StickerListAdapter :BaseAdapter<MainStickerBean.InnerMainStickerBean,StickerListAdapter.ViewHolder>{
    val TAG = StickerListAdapter::class.java.simpleName
    constructor() : super()



    class ViewHolder: RecyclerView.ViewHolder {
        lateinit var ivImg :ImageView
        lateinit var tvDownload:TextView
        lateinit var tvShare:TextView
        lateinit var ivReport:ImageView
        constructor(itemView: View) : super(itemView){
            ivImg = itemView.findViewById(R.id.iv_img)
            tvDownload = itemView.findViewById(R.id.tv_download)
            tvShare = itemView.findViewById(R.id.tv_share)
            ivReport = itemView.findViewById(R.id.iv_report)
        }

    }

    override fun onStickerBindViewHolder(holder: ViewHolder, position: Int) {
        LogUtil.d(TAG,"onStickerBindViewHolder:"+position)
        holder.tvDownload.text = mDatas[position].downloadCount.toString()
        holder.tvShare.text = mDatas[position].shareCcount.toString()
    }

    override fun onStickerCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_main_sticker,parent,false)
        return ViewHolder(view)
    }


}