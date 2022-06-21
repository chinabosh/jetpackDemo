package com.bosh.jetpackdemo.ui.setting

import com.bosh.jetpackdemo.repository.SpRepository
import javax.inject.Inject

/**
 * @author lzq
 * @date  2022/6/21
 */
class SettingRepository @Inject constructor(
    private val spRepo: SpRepository
) {

    fun saveProv(prov: String) {
        spRepo.setDefaultProvince(prov)
    }

    fun getProv() = spRepo.getDefaultProvince()
}