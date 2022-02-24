package com.bosh.jetpackdemo.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.bosh.jetpackdemo.entity.MessageInfo

/**
 * @author lzq
 * @date  2022/2/23
 */
@Dao
interface MessageInfoDao {
    @Insert
    fun insertAll(vararg messageInfo: MessageInfo)

    @Insert
    fun insertAll(list: List<MessageInfo>)

    @Query("select * from message_info")
    fun getMessage(): PagingSource<Int, MessageInfo>
}