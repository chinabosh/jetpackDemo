package com.bosh.jetpackdemo.net

import com.bosh.jetpackdemo.BuildConfig
import com.bosh.jetpackdemo.entity.OilPriceWrapper
import retrofit2.http.GET

/**
 * @author lzq
 * @date  2022/3/7
 */
interface OilService {
    @GET("gnyj/query?key=${BuildConfig.OIL_KEY}")
    suspend fun getTodayOilPrice(): OilPriceWrapper
}