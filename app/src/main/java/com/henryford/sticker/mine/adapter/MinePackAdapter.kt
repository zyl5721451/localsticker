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
import com.henryford.sticker.util.LogUtil

class MinePackAdapter :BaseAdapter<MinePackBean.InnerMinePackBean,MinePackAdapter.ViewHolder>{
    val TAG = MinePackAdapter::class.java.simpleName
    constructor() : super()



    class ViewHolder: RecyclerView.ViewHolder {
        lateinit var ivPreview :ImageView
        lateinit var tvName:TextView
        lateinit var tvDes:TextView
        lateinit var iv1:ImageView
        lateinit var iv2:ImageView
        lateinit var iv3:ImageView
        lateinit var iv4:ImageView
        lateinit var iv5:ImageView
        lateinit var ll6:LinearLayout
        lateinit var iv6:ImageView
        constructor(itemView: View) : super(itemView){
            ivPreview = itemView.findViewById(R.id.iv_preview)
            tvName = itemView.findViewById(R.id.tv_name)
            tvDes = itemView.findViewById(R.id.tv_des)
            iv1 = itemView.findViewById(R.id.iv_1)
            iv2 = itemView.findViewById(R.id.iv_2)
            iv3 = itemView.findViewById(R.id.iv_3)
            iv4 = itemView.findViewById(R.id.iv_4)
            iv5 = itemView.findViewById(R.id.iv_5)
            ll6 = itemView.findViewById(R.id.ll_6)
            iv6 = itemView.findViewById(R.id.iv_6)
        }

    }

    override fun onStickerBindViewHolder(holder: ViewHolder, position: Int) {
        LogUtil.d(TAG,"onStickerBindViewHolder:"+position)
        var data = mDatas[position]
        if(!TextUtils.isEmpty(data.previewIcon)){
            GlideUtils.loadImage(holder.ivPreview,data.previewIcon)
        }
        holder.tvName.text = data.name
        holder.tvDes.text = "@"+data.author+" • "+data.size+"kb"
        for(i in 0 until data.iconList.size){
            var icon = data.iconList[i]
            when(i){
                0 ->{
                    GlideUtils.loadImage(holder.iv1,icon)
                }
                1 ->{
                    GlideUtils.loadImage(holder.iv2,icon)
                }
                2 ->{
                    GlideUtils.loadImage(holder.iv3,icon)
                }
                3 ->{
                    GlideUtils.loadImage(holder.iv4,icon)
                }
                4 ->{
                    GlideUtils.loadImage(holder.iv5,icon)
                }
            }
        }
    }

    override fun onStickerCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_mine_pack,parent,false)
        return ViewHolder(view)
    }


}