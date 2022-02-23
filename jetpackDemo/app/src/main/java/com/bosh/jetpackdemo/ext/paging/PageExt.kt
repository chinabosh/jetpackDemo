package com.bosh.jetpackdemo.ext.paging

import androidx.paging.PagingConfig

/**
 * @author lzq
 * @date  2022/2/23
 */
val globalPageConfig: PagingConfig
        get() = PagingConfig(
            initialLoadSize = DEFAULT_INITIAL_LOAD_SIZE,
            pageSize = DEFAULT_PAGE_SIZE,
            prefetchDistance = DEFAULT_PREFETCH_DISTANCE,
            enablePlaceholders = false
        )

private const val DEFAULT_PAGE_SIZE = 10
private const val DEFAULT_PREFETCH_DISTANCE = 10//当距离底部还有多远的时候自动加载下一页
private const val DEFAULT_INITIAL_LOAD_SIZE = 20
