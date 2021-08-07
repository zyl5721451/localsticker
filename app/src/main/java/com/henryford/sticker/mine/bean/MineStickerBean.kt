package com.henryford.sticker.mine.bean

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.henryford.sticker.bean.PageInfoBean
import java.io.Serializable
@Entity(tableName = "mine_sticker")
class MineStickerBean :Serializable{
    @PrimaryKey(autoGenerate=true)
    var id:Int = 0
    var icon:String = ""


    constructor(icon: String) {
        this.icon = icon
    }


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MineStickerBean

        if (icon != other.icon) return false

        return true
    }

    override fun hashCode(): Int {
        return icon.hashCode()
    }


}