package com.bosh.jetpackdemo.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * @author lzq
 * @date  2022/2/22
 */
object DateUtils {
    private val sdf: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)
    fun getCurrentTime(): String {
        return sdf.format(System.currentTimeMillis())
    }

    fun getShowTime(time: String): String {
        val times = sdf.parse(time)?.time ?: -1
        return when (System.currentTimeMillis() - times) {
            -1L -> "loading"
            in 0..10 * 1000 -> "刚刚"
            in 10001 until 24 * 60 * 60 * 1000 ->
                SimpleDateFormat("mm:ss", Locale.CHINA).format(times)
            in 24 * 60 * 60 * 1000 + 1 until 2 * 24 * 60 * 60 * 1000 -> "昨天"
            else -> SimpleDateFormat("yy-MM-dd", Locale.CHINA).format(times)
        }
    }
}