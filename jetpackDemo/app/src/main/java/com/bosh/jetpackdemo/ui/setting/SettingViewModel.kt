package com.bosh.jetpackdemo.ui.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bosh.jetpackdemo.utils.Mapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author lzq
 * @date  2022/6/21
 */
@HiltViewModel
class SettingViewModel @Inject constructor(
    private val repo: SettingRepository
): ViewModel(){

    private val _prov: MutableLiveData<String> = MutableLiveData()
    val prov: LiveData<String>
        get() = _prov

    init {
        _prov.postValue(repo.getProv())
    }

    fun changeDefaultProvince(prov: String) {
        var province = prov
        if (province == Mapper.PROVINCE_ALL) {
            province = ""
        }
        repo.saveProv(province)
    }
}