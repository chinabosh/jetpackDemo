package com.bosh.jetpackdemo.repository

import com.bosh.jetpackdemo.utils.SpUtils
import javax.inject.Inject

/**
 * 同一管理sp
 * @author lzq
 * @date  2022/6/21
 */
class SpRepository @Inject constructor(
    private val sp: SpUtils
) {
    private val keyDefaultProvince = "key_default_province"

    fun setDefaultProvince(prov: String) {
        sp.putString(keyDefaultProvince, prov)
    }

    fun getDefaultProvince(): String {
        return sp.getString(keyDefaultProvince, "")
    }
}