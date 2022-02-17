package com.bosh.jetpackdemo.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author lzq
 * @date  2022/2/16
 */
@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private var _test : MutableLiveData<Int> = MutableLiveData(0)
    val test : LiveData<Int>
    get() = _test
}