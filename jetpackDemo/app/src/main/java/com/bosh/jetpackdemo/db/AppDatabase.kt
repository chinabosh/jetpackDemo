package com.bosh.jetpackdemo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bosh.jetpackdemo.entity.MessageInfo
import com.bosh.jetpackdemo.entity.OilPrice

/**
 * @author lzq
 * @date  2022/2/23
 */
@Database(entities = [MessageInfo::class, OilPrice::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun messageInfoDao(): MessageInfoDao
    abstract fun oilDao(): OilDao
}