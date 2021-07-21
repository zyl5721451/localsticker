package com.henryford.sticker.mine.bean

import com.henryford.sticker.bean.PageInfoBean

class MineStickerBean {
   var mineStickerkList = ArrayList<InnerMineStickerBean>()
   var pageInfo:PageInfoBean? = null

    class InnerMineStickerBean{
        var id:Long = 0L
        var icon:String = ""

        constructor(id: Long, icon: String) {
            this.id = id
            this.icon = icon
        }
    }
}