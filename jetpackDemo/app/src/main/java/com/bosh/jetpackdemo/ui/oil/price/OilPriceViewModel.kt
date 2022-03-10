package com.bosh.jetpackdemo.ui.oil.price

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * @author lzq
 * @date  2022/3/7
 */
@HiltViewModel
class OilPriceViewModel @Inject constructor(
    private val oilRepository: OilRepository
) : ViewModel() {

    private val _filter: MutableLiveData<Filter> = MutableLiveData()
    @OptIn(ExperimentalCoroutinesApi::class)
    val priceList = _filter.asFlow().flatMapLatest {
        oilRepository.getTodayOilPrice(it.day, it.prov).flowOn(Dispatchers.IO)
    }.cachedIn(viewModelScope)

    fun changeFilter(filter: Filter) {
        _filter.postValue(filter)
    }
}