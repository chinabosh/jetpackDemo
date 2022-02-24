package com.bosh.jetpackdemo.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author lzq
 * @date  2022/2/22
 */
@Entity(tableName = "message_info")
data class MessageInfo(
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "subTitle")
    val subTitle: String,
    @ColumnInfo(name = "isRead")
    var isRead: Boolean = false,
    @ColumnInfo(name = "createTime")
    val createTime: String,
    @ColumnInfo(name = "readTime")
    var readTime: String
)
