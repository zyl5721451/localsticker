package com.henryford.sticker.main.bean

import java.io.Serializable

class StickerDetailBean :Serializable{
    var sticker:InnerStickerDetailBean? = null
    class InnerStickerDetailBean:Serializable{
        var id:String = ""
        var dCount:Int = 0
        var sCount:Int = 0
        var original:String = ""
        var thumb:String = ""
        var tags:ArrayList<TagBean.InnerTagBean>? = null

        constructor(
            id: String,
            dCount: Int,
            sCount: Int,
            original: String,
            thumb: String,
            tags: ArrayList<TagBean.InnerTagBean>?
        ) {
            this.id = id
            this.dCount = dCount
            this.sCount = sCount
            this.original = original
            this.thumb = thumb
            this.tags = tags
        }
    }
}