package com.bosh.jetpackdemo.ui.oil.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bosh.jetpackdemo.entity.OilHistory
import com.bosh.jetpackdemo.utils.DateUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author lzq
 * @date  2022/3/10
 */
@HiltViewModel
class AddOilHistoryViewModel @Inject constructor(
    private val repo: AddOilHistoryRepository
) : ViewModel() {

    val records: MutableList<OilHistory> = ArrayList()
    private val _adapterData: MutableLiveData<Int> = MutableLiveData()
    val adapterData: LiveData<Int>
        get() = _adapterData

    private val _hundredOil: MutableLiveData<String> = MutableLiveData()
    val hundredOil: LiveData<String>
        get() = _hundredOil

    private val _monthOil: MutableLiveData<String> = MutableLiveData()
    val monthOil: LiveData<String>
        get() = _monthOil

    fun initData() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getOilHistory()
                .map {
                    if (it.size >= 2) {
                        calcHundredOil(it)
                        calcMonthOil(it)
                    }
                    return@map it
                }
                .collectLatest {
                    records.addAll(it)
                    _adapterData.postValue(0)
                }
        }
    }

    private fun calcHundredOil(list: List<OilHistory>) {
        assert(list.size >= 2)
        val startMile = list[0].mile
        val endMile = list.last().mile
        val mile = endMile - startMile
        //油箱剩得少要加上，油箱剩的多要扣掉
        //这个值可能有误差，但油加多了，误差慢慢就变小了，误差可忽略
        var oil = list[0].OilLeft - list.last().OilLeft
        for (i in 0..list.size - 2) {
            oil += list[i].addOil
        }
        val cost = oil / mile * 100
        val res = String.format("%.2f", cost)
        _hundredOil.postValue(res)
    }

    private fun calcMonthOil(list: List<OilHistory>) {
        assert(list.size >= 2)
        val startDate = list[0].time
        val endDate = list.last().time
        val day = DateUtils.calcDay(startDate, endDate)
        //油箱剩得少要加上，油箱剩的多要扣掉
        //这个值可能有误差，但油加多了，误差慢慢就变小了，误差可忽略
        var oil = list[0].OilLeft - list.last().OilLeft
        for (i in 0..list.size - 2) {
            oil += list[i].addOil
        }
        val cost = oil / day * 30
        val res = String.format("%.2f", cost)
        _monthOil.postValue(res)
    }
}