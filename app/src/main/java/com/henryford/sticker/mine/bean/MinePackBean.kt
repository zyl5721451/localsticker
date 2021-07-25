package com.henryford.sticker.mine.bean

import com.henryford.sticker.bean.PageInfoBean
import java.io.Serializable

class MinePackBean{
   var minePackList = ArrayList<InnerMinePackBean>()
   var pageInfo:PageInfoBean? = null


    class InnerMinePackBean{
        var id:Long = 0L
        var name:String = ""
        var author:String = ""
        var size:Long = 0L
        var added:Boolean = false
        var state:Int = 0
        var previewIcon:String = ""
        var iconList = ArrayList<String>()

        constructor(
            id: Long,
            name: String,
            author: String,
            size: Long,
            state: Int,
            previewIcon: String,
            iconList: ArrayList<String>
        ) {
            this.id = id
            this.name = name
            this.author = author
            this.size = size
            this.state = state
            this.previewIcon = previewIcon
            this.iconList = iconList
        }




    }
}