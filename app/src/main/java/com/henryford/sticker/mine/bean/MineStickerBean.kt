package com.henryford.sticker.mine.bean

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.henryford.sticker.bean.PageInfoBean
import java.io.Serializable

class MineStickerBean :Serializable{
   var mineStickerkList = ArrayList<InnerMineStickerBean>()
   var pageInfo:PageInfoBean? = null

    @Entity(tableName = "mine_sticker")
    class InnerMineStickerBean:Serializable{
        @PrimaryKey(autoGenerate=true)
        var id:Int = 0
        var icon:String = ""


        constructor(icon: String) {
            this.icon = icon
        }

        override fun toString(): String {
            return "InnerMineStickerBean(id=$id, icon='$icon')"
        }
    }
}