package com.henryford.sticker.dao

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import com.henryford.sticker.bean.UserBean
import com.henryford.sticker.user.UserModel

object AppDataBaseFactory {
    fun getMigration(): Array<Migration> {
        return arrayOf(
//            MigrationDatabase.MIGRATION_1_2
        )
    }

    fun create(context: Context): AppDataBase {
        var dataBase:AppDataBase
        var firstLogin = UserModel.isLogin()&&AppDataBase.lastDb.contains(UserBean.USER_ID_UNLOGIN)
        if (!AppDataBase.databaseConnectionPool.containsKey(UserModel.userId())&&!firstLogin) {
            AppDataBase.lastDb = AppDataBase.getDatabaseName()
            val dbBuilder: RoomDatabase.Builder<AppDataBase> = Room.databaseBuilder(context, AppDataBase::class.java, AppDataBase.getDatabaseName())
            if (getMigration().isNotEmpty()) {
                dbBuilder.addMigrations(*getMigration())
            }
            dataBase =  dbBuilder.build()
            AppDataBase.databaseConnectionPool[UserModel.userId()] = dataBase
        }
        return AppDataBase.databaseConnectionPool[UserModel.userId()]!!
    }
}