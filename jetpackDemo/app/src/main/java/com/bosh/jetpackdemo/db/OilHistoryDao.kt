package com.bosh.jetpackdemo.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.bosh.jetpackdemo.entity.OilHistory

/**
 * @author lzq
 * @date  2022/5/18
 */
@Dao
interface OilHistoryDao {

    @Query("select * from oil_history where isInit = 1")
    fun getInitOilInfo(): OilHistory

    @Insert
    fun insert(vararg oilHistory: OilHistory)
}