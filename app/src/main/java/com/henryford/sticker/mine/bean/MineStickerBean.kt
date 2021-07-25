package com.henryford.sticker.mine.bean

import com.henryford.sticker.bean.PageInfoBean
import java.io.Serializable

class MineStickerBean :Serializable{
   var mineStickerkList = ArrayList<InnerMineStickerBean>()
   var pageInfo:PageInfoBean? = null

    class InnerMineStickerBean:Serializable{
        var id:Long = 0L
        var icon:String = ""

        constructor(id: Long, icon: String) {
            this.id = id
            this.icon = icon
        }
    }
}