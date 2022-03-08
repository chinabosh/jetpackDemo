package com.bosh.jetpackdemo.ui.oil.price

import com.bosh.jetpackdemo.entity.OilPrice

/**
 * @author lzq
 * @date  2022/3/7
 */
interface IOilPrice {
    suspend fun getTodayOilPrice(day: String): List<OilPrice>
}