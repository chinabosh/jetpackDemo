package com.bosh.jetpackdemo.repository

import com.bosh.jetpackdemo.entity.MessageInfo
import com.bosh.jetpackdemo.utils.DateUtils
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong

/**
 * @author lzq
 * @date  2022/2/22
 */
class MessageFactory {
    private val counter = AtomicLong(0)

    fun createMessage() : MessageInfo {
        val id = counter.incrementAndGet()
        return MessageInfo(
            id = id,
            title = "title_$id",
            subTitle = "subTitle_$id",
            createTime = DateUtils.getCurrentTime(),
            readTime = ""
        )
    }
}