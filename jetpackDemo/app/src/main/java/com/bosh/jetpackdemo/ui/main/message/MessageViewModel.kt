package com.bosh.jetpackdemo.ui.main.message

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.bosh.jetpackdemo.entity.MessageInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

/**
 * @author lzq
 * @date  2022/2/21
 */
@HiltViewModel
class MessageViewModel @Inject constructor(
    private val repository: MessageRepository
) : ViewModel() {

    private val _testData: MutableLiveData<Int> = MutableLiveData()
    val testData = _testData.asFlow().flatMapLatest {
        repository.getTestData()
    }.cachedIn(viewModelScope)

    fun getTestData() {
        _testData.postValue(0)
    }
}