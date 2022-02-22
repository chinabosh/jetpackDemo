package com.bosh.jetpackdemo.entity

/**
 * @author lzq
 * @date  2022/2/22
 */
data class MessageInfo(
    val id: Long,
    val title: String,
    val subTitle: String,
    var isRead: Boolean = false,
    val createTime: String,
    var readTime: String
)
