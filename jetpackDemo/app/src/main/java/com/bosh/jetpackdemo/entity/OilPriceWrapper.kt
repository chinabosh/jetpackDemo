package com.bosh.jetpackdemo.entity

/**
 * @author lzq
 * @date  2022/3/7
 */
data class OilPriceWrapper(
    val error_code: Int,
    val reason: String,
    val result: List<OilPrice>
)