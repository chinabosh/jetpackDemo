package com.bosh.jetpackdemo.ui.oil.add

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * @author lzq
 * @date  2022/3/8
 */
@HiltViewModel
class AddOilRecordViewModel @Inject constructor(
    private val repo: AddOilRecordRepository
) : ViewModel() {

    private val _initMile: MutableLiveData<String> = MutableLiveData()
    val initMile = _initMile.asFlow().flatMapLatest {
        repo.getInitData().flowOn(Dispatchers.IO)
    }.filter { it != null }


    fun getInitData() {
        _initMile.postValue("")
    }
}