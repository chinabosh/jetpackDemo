package com.bosh.jetpackdemo.ui.oil.price

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.cachedIn
import com.bosh.jetpackdemo.entity.OilPrice
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
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
    val priceList = _filter.asFlow().flatMapLatest {
        oilRepository.getTodayOilPrice(it.day, it.prov).flowOn(Dispatchers.IO)
    }.cachedIn(viewModelScope)

    fun changeFilter(filter: Filter) {
        _filter.postValue(filter)
    }
}