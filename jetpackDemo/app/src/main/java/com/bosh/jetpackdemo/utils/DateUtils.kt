package com.bosh.jetpackdemo.utils

import java.text.ParseException
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
                SimpleDateFormat("HH:mm", Locale.CHINA).format(times)
            in 24 * 60 * 60 * 1000 + 1 until 2 * 24 * 60 * 60 * 1000 -> "昨天"
            else -> SimpleDateFormat("yy-MM-dd", Locale.CHINA).format(times)
        }
    }

    fun getCurDay(): String {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)
        return simpleDateFormat.format(System.currentTimeMillis())
    }

    fun getDate(dateTime: Calendar): String {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)
        return simpleDateFormat.format(dateTime.timeInMillis)
    }

    /**
     * 从指定的时间字符串中提取年份
     * @param date
     * @return
     */
    fun getYear(date: String): Int {
        try {
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)
            val calendar = sdf.calendar
            calendar.time = sdf.parse(date)!!
            return calendar[Calendar.YEAR]
        } catch (e: ParseException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return -1
    }

    /**
     * 获取当前月份
     * @return
     */
    fun getMonth(date: String): Int {
        try {
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)
            val calendar = sdf.calendar
            calendar.time = sdf.parse(date)!!
            return calendar[Calendar.MONTH]
        } catch (e: ParseException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return -1
    }

    /**
     * 获取当月天数
     * @return
     */
    fun getDay(date: String): Int {
        try {
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)
            val calendar = sdf.calendar
            calendar.time = sdf.parse(date)!!
            return calendar[Calendar.DAY_OF_MONTH]
        } catch (e: ParseException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return -1
    }
}