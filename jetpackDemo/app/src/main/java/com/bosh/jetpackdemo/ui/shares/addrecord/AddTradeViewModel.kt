package com.bosh.jetpackdemo.ui.shares.addrecord

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author lzq
 * @date 2022/6/28
 */
@HiltViewModel
class AddTradeViewModel @Inject constructor(
    private val repo: AddTradeRepository
): ViewModel() {

}