package com.bosh.jetpackdemo.ui.main.message

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.paging.Pager
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bosh.jetpackdemo.entity.MessageInfo
import com.bosh.jetpackdemo.ext.paging.globalPageConfig
import com.bosh.jetpackdemo.repository.MessageFactory
import java.lang.Integer.min
import javax.inject.Inject

/**
 * @author lzq
 * @date  2022/2/23
 */
class MessageRepository @Inject constructor() {
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

}

class MessagePagingSource(
    private val items: ArrayList<MessageInfo>
) : PagingSource<Int, MessageInfo>() {

    @RequiresApi(Build.VERSION_CODES.N)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MessageInfo> {
        var curPage = params.key ?: 0
        var hasLoadSize = curPage.times(params.loadSize)
        if (hasLoadSize >= items.size) {
            return LoadResult.Error(throwable = Throwable())
        }
        var nextPager = if (hasLoadSize == items.size) {
            null
        } else{
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