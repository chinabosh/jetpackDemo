package com.bosh.jetpackdemo.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bosh.jetpackdemo.entity.OilPrice

/**
 * @author lzq
 * @date  2022/3/7
 */
@Dao
interface OilDao {
    @Query("select * from oil_price where (:day ='' or time = :day) and (:city = '' or city = :city) order by id")
    fun getOilPrice(day: String, city: String): PagingSource<Int, OilPrice>

    @Query("select count(*) from oil_price where time = :day")
    fun getOilPriceCount(day: String): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(list: List<OilPrice>)
}