package com.bosh.jetpackdemo.ui.main.mine

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author lzq
 * @date  2022/2/17
 */
@HiltViewModel
class MineViewModel @Inject constructor() : ViewModel() {

    val functionItems: MutableList<FunctionItem> = ArrayList()

    init {
        functionItems.add(FunctionItem("今日油价"))
        functionItems.add(FunctionItem("加油"))
    }
}