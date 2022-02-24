package com.bosh.jetpackdemo.ui.main.message

import android.os.Build
import androidx.annotation.AnyThread
import androidx.annotation.RequiresApi
import androidx.annotation.WorkerThread
import androidx.paging.Pager
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bosh.jetpackdemo.db.AppDatabase
import com.bosh.jetpackdemo.entity.MessageInfo
import com.bosh.jetpackdemo.ext.paging.globalPageConfig
import com.bosh.jetpackdemo.repository.MessageFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.lang.Integer.min
import javax.inject.Inject

/**
 * @author lzq
 * @date  2022/2/23
 */
class MessageRepository @Inject constructor(
    private val db: AppDatabase
) {
    private val messageFactory = MessageFactory()
    private val items = arrayListOf<MessageInfo>()

    init {
        for (i in 0..99) {
            items.add(messageFactory.createMessage())
        }
    }

    fun getTestData() = Pager(
        globalPageConfig
    ) {
        MessagePagingSource(items)
    }.flow

    @WorkerThread
    fun saveTestDataToDb() {
        try {
            db.messageInfoDao().insertAll(items)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @WorkerThread
    fun getDbData() = Pager(
        globalPageConfig
    ) {
        db.messageInfoDao().getMessage()
    }.flow

}

class MessagePagingSource(
    private val items: ArrayList<MessageInfo>
) : PagingSource<Int, MessageInfo>() {

    @RequiresApi(Build.VERSION_CODES.N)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MessageInfo> {
        val curPage = params.key ?: 0
        val hasLoadSize = curPage.times(params.loadSize)
        if (hasLoadSize >= items.size) {
            return LoadResult.Error(throwable = Throwable())
        }
        val nextPager = if (hasLoadSize == items.size) {
            null
        } else {
            curPage + 1
        }
        return LoadResult.Page(
            data = items.subList(hasLoadSize, min(items.size, hasLoadSize + params.loadSize)),
            prevKey = null,
            nextKey = nextPager
        )
    }

    override fun getRefreshKey(state: PagingState<Int, MessageInfo>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey
        }
    }
}

//class MessageDbPagingSource(
//    private val db: AppDatabase
//) : PagingSource<Int, MessageInfo>() {
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MessageInfo> {
//        val curPage = params.key ?: 0
//        val data = db.messageInfoDao().getMessage(curPage, params.loadSize)
//        val totalCount = db.messageInfoDao().getMessageTotalCount()
//        val nextPage = if (totalCount > curPage * params.loadSize) {
//            curPage + 1
//        } else{
//            null
//        }
//        return LoadResult.Page(
//            data = data,
//            prevKey = null,
//            nextKey = nextPage
//        )
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, MessageInfo>): Int? {
//        return state.anchorPosition?.let {
//            state.closestPageToPosition(it)?.prevKey
//        }
//    }
//}