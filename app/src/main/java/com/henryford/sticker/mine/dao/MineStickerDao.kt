package com.henryford.sticker.mine.dao

import androidx.room.*
import com.henryford.sticker.mine.bean.MineStickerBean

@Dao
abstract class MineStickerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(sticker: MineStickerBean)
    @Insert
    abstract fun insert(stickers:List<MineStickerBean>)
    @Delete
    abstract fun delete(sticker:MineStickerBean)

    @Update
    abstract fun update(sticker:MineStickerBean)

    @Query("select * from mine_sticker")
    abstract fun queryAll():List<MineStickerBean>

    @Query("select count(*) from mine_sticker")
    abstract fun stickerCount() : Int

    @Query("select icon from mine_sticker")
    abstract fun queryAllStickerIcon() : List<String>

}