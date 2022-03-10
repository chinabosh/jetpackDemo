package com.bosh.jetpackdemo.ui.main.message

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.bosh.jetpackdemo.entity.MessageInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
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
    @OptIn(ExperimentalCoroutinesApi::class)
    val testData = _testData.asFlow().flatMapLatest {
        repository.getTestData().flowOn(Dispatchers.IO)
    }.cachedIn(viewModelScope)

    private val _dbData: MutableLiveData<Int> = MutableLiveData()
    val dbData = _dbData.asFlow().flatMapLatest {
        repository.getDbData()
    }.cachedIn(viewModelScope)

    fun getTestData() {
        _testData.postValue(0)
    }

    fun saveTestDataToDb() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveTestDataToDb()
        }
    }

    fun getDbData() {
        _dbData.postValue(0)
    }
}