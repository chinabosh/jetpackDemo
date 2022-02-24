package com.bosh.jetpackdemo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bosh.jetpackdemo.entity.MessageInfo

/**
 * @author lzq
 * @date  2022/2/23
 */
@Database(entities = [MessageInfo::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun messageInfoDao(): MessageInfoDao
}