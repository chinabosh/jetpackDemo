package com.bosh.jetpackdemo.ui.oil.add

import android.text.TextUtils
import androidx.lifecycle.*
import com.bosh.jetpackdemo.entity.OilHistory
import com.bosh.jetpackdemo.utils.DateUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author lzq
 * @date  2022/3/8
 */
@HiltViewModel
class AddOilRecordViewModel @Inject constructor(
    private val repo: AddOilRecordRepository
) : ViewModel() {

    private val _initInfo: MutableLiveData<Int> = MutableLiveData()

    private var isOilChange = false
    private var isAmountChange = false

    /** 是否第一次记录加油 */
    private var isInit: Boolean = true
    private lateinit var info: OilHistory
    val initInfo = _initInfo.asFlow().flatMapLatest {
        repo.getInitData().flowOn(Dispatchers.IO)
    }.filter {
        isInit = it == null
        if (it != null) {
            info = it
        }
        it != null
    }

    private val _initMile: MutableLiveData<Double> = MutableLiveData()
    private val _initOil: MutableLiveData<Double> = MutableLiveData()

    private val _oil: MutableLiveData<Double> = MutableLiveData()
    val oil = _oil.map {
        String.format("%.2f", it)
    }
    private val _amount: MutableLiveData<Double> = MutableLiveData()
    val amount = _amount.map {
        String.format("%.2f", it)
    }

    private val _date: MutableLiveData<String> = MutableLiveData()
    val date = _date

    private val _errorMsg: MutableLiveData<String> = MutableLiveData()
    val errorMsg: LiveData<String>
        get() = _errorMsg
    private val _successMsg: MutableLiveData<String> = MutableLiveData()
    val successMsg: LiveData<String>
        get() = _successMsg

    fun getInitData() {
        _initInfo.postValue(0)
        _date.value = DateUtils.getCurDay()
    }

    fun setInitMile(text: String) {
        if (text.isNotEmpty()) {
            _initMile.value = text.toDouble()
        } else {
            _initMile.value = 0.0
        }
    }

    fun setInitOil(text: String) {
        if (text.isNotEmpty()) {
            _initOil.value = text.toDouble()
        } else {
            _initOil.value = 0.0
        }
    }

    fun setOil(oilText: String, priceText: String) {
        if (isOilChange) {
            return
        }
        val oil = if (oilText.isNotEmpty()) {
            oilText.toDouble()
        } else {
            0.0
        }
        if (priceText.isNotEmpty()) {
            val amount = oil * priceText.toDouble()
            setAmount(amount)
        }
    }

    fun setPrice(priceText: String, oilText: String, amountText: String) {
        if (priceText.isNotEmpty()) {
            if (oilText.isNotEmpty()) {
                val amount = oilText.toDouble() * priceText.toDouble()
                setAmount(amount)
            }
            if (oilText.isEmpty() && amountText.isNotEmpty()) {
                val oil = amountText.toDouble() / priceText.toDouble()
                setOil(oil)
            }
        }
    }

    fun setAmount(priceText: String, amountText: String) {
        if (isAmountChange) {
            return
        }
        if (priceText.isNotEmpty()) {
            val amount = if (amountText.isEmpty()) {
                0.0
            } else {
                amountText.toDouble()
            }
            val oil = amount / priceText.toDouble()
            setOil(oil)
        }
    }

    private fun setAmount(double: Double) {
        isAmountChange = true
        _amount.value = double
        isAmountChange = false
    }

    private fun setOil(double: Double) {
        isOilChange = true
        _oil.value = double
        isOilChange = false
    }

    fun setDate(date: String) {
        _date.value = date
    }

    fun addRecord(
        mileText: String,
        addOilText: String,
        addPriceText: String,
        timeText: String,
        oilLeftText: String
    ) {
        if (mileText.isEmpty()) {
            _errorMsg.value = "请输入里程！"
            return
        }
        if (addOilText.isEmpty()) {
            _errorMsg.value = "请输入加油量！"
            return
        }
        if (addPriceText.isEmpty()) {
            _errorMsg.value = "请输入油价"
            return
        }
        if (oilLeftText.isEmpty()) {
            _errorMsg.value = "请输入剩余油量"
            return
        }
        val mile = mileText.toInt()
        val addOil = addOilText.toDouble()
        val addPrice = addPriceText.toDouble()
        val oilLeft = oilLeftText.toDouble()
        val oilHistory =
            OilHistory(
                isInit = isInit,
                mile = mile,
                addOil = addOil,
                addPrice = addPrice,
                time = timeText,
                OilLeft = oilLeft
            )
        viewModelScope.launch(Dispatchers.IO) {
            repo.insertData(oilHistory)
                .collectLatest {
                    _successMsg.postValue(it)
                }
        }

    }
}