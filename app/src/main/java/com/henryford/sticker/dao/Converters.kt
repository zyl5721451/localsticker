package com.henryford.sticker.dao

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.henryford.sticker.mine.bean.MineStickerBean
import com.henryford.sticker.util.GsonUtil
import java.util.*

class Converters {
    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return if (timestamp == null) null else Date(timestamp)
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun stringToMineSticker(sticker: String?): MineStickerBean? {
        return GsonUtil.parseObject(sticker, MineStickerBean::class.java)
    }

    @TypeConverter
    fun mineStickerToString(sticker: MineStickerBean?): String? {
        return GsonUtil.getJsonString(sticker)
    }


}