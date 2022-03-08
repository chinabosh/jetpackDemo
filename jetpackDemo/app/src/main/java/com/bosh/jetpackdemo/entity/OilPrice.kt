package com.bosh.jetpackdemo.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * @author lzq
 * @date  2022/3/7
 */
@Entity(tableName = "oil_price", indices = [Index(value = ["city", "time"], unique = true)])
data class OilPrice(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val city: String,
    @SerializedName("92h")
    val ninetyTwo : String,
    @SerializedName("95h")
    val ninetyFive: String,
    @SerializedName("98h")
    val ninetyEight: String,
    @SerializedName("0h")
    val zero: String,
    /**
     * yyyy-MM-dd
     */
    var time: String
)
