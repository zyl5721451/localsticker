package com.henryford.sticker.main.bean

import com.henryford.sticker.bean.PageInfoBean

class MainStickerBean {
   var mainStickerList = ArrayList<InnerMainStickerBean>()
   var pageInfo:PageInfoBean? = null

    class InnerMainStickerBean{
        var id:Long = 0L
        var downloadCount:Int = 0
        var shareCcount:Int = 0
        var url:String = ""

        constructor(id: Long, downloadCount: Int, shareCcount: Int, url: String) {
            this.id = id
            this.downloadCount = downloadCount
            this.shareCcount = shareCcount
            this.url = url
        }
    }
}