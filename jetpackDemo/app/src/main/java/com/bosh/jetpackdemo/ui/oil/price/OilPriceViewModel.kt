package com.bosh.jetpackdemo.ui.oil.price

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.bosh.jetpackdemo.utils.DateUtils
import com.bosh.jetpackdemo.utils.Mapper
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

    fun getInitFilter(): Filter {
        val curDay = DateUtils.getCurDay()
        val prov = oilRepository.getDefaultProvince()
        return Filter(curDay, prov)
    }

    fun changeFilter(filter: Filter) {
        if (filter.prov == Mapper.PROVINCE_ALL) {
            filter.prov = ""
        }
        _filter.postValue(filter)
    }
}