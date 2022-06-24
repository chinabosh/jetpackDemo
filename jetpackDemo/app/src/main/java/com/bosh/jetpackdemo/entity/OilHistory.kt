package com.bosh.jetpackdemo.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author lzq
 * @date  2022/3/11
 */
@Entity(tableName = "oil_history")
data class OilHistory(
    /**
     * 是否初始数据
     */
    var isInit: Boolean,
    var mile: Int,
    var OilLeft: Double,
    var addOil: Double,
    var addPrice: Double,
    var time: String,
) {
    @PrimaryKey(autoGenerate = true) var id: Long = 0
}