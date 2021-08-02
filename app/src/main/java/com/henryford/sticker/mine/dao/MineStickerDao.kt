package com.henryford.sticker.mine.dao

import androidx.room.*
import com.henryford.sticker.mine.bean.MineStickerBean

@Dao
abstract class MineStickerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(sticker: MineStickerBean.InnerMineStickerBean)
    @Insert
    abstract fun insert(stickers:List<MineStickerBean.InnerMineStickerBean>)
    @Delete
    abstract fun delete(sticker:MineStickerBean.InnerMineStickerBean)

    @Update
    abstract fun update(sticker:MineStickerBean.InnerMineStickerBean)

    @Query("select * from mine_sticker where id =:id")
    abstract fun queryById(id:Int) : MineStickerBean.InnerMineStickerBean

    @Query("select * from mine_sticker")
    abstract fun queryAll():List<MineStickerBean.InnerMineStickerBean>

    @Query("select count(*) from mine_sticker")
    abstract fun stickerCount() : Int

    @Query("select icon from mine_sticker")
    abstract fun queryAllStickerIcon() : List<String>

}