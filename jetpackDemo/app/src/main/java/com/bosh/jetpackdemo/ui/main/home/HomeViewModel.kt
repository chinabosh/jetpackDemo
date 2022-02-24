package com.bosh.jetpackdemo.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author lzq
 * @date  2022/2/17
 */
@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    val text : LiveData<String> get() = _text
    private var _text: MutableLiveData<String> = MutableLiveData("首页")
    private val dictChars = mutableListOf<Char>().apply { "0123456789abcdefghijklmnopqrstuvwxyz".forEach { this.add(it) } }

    fun changeText(text : String) {
        _text.value = text
    }

    fun randomText() {
        val randomStr = StringBuilder().apply { (1..((10..30).random())).onEach { append(dictChars.random()) } }
        _text.postValue(randomStr.toString())
    }
}