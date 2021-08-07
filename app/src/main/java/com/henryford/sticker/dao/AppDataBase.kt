package com.henryford.sticker.dao

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.henryford.sticker.mine.bean.MineStickerBean
import com.henryford.sticker.mine.dao.MineStickerDao
import com.henryford.sticker.user.UserModel
import java.util.*

@Database(entities = [MineStickerBean::class], version = 1)
@TypeConverters(value = [Converters::class])
abstract class AppDataBase : RoomDatabase() {
    companion object{
        var lastDb = ""
        val databaseConnectionPool: HashMap<String, AppDataBase> = HashMap<String, AppDataBase>()
        fun getDatabaseName(): String {
            return UserModel.userId() + "_sticker-db"
        }
        fun getInstance(context: Context): AppDataBase {
            return AppDataBaseFactory.create(context)
        }
    }



    abstract fun mineStickerDao():MineStickerDao

}