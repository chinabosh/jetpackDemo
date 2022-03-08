package com.bosh.jetpackdemo.ui.oil.price

import androidx.paging.*
import androidx.paging.RemoteMediator
import com.bosh.jetpackdemo.db.AppDatabase
import com.bosh.jetpackdemo.entity.OilPrice
import com.bosh.jetpackdemo.ext.paging.globalPageConfig
import com.bosh.jetpackdemo.net.ServiceManager
import com.bosh.jetpackdemo.utils.DateUtils
import kotlinx.coroutines.flow.Flow
import java.lang.Exception
import javax.inject.Inject

/**
 * @author lzq
 * @date  2022/3/7
 */
class OilRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {
    @OptIn(ExperimentalPagingApi::class)
    fun getTodayOilPrice(day: String, city: String): Flow<PagingData<OilPrice>> {
        return Pager(
            globalPageConfig,
            remoteMediator = OilRemoteMediator(remoteDataSource, localDataSource, day)
        ) {
            localDataSource.getTodayOilPrice(day, city)
        }.flow
    }
}

class LocalDataSource @Inject constructor(
    private val db: AppDatabase
) {
    fun getTodayOilPrice(day: String, city: String): PagingSource<Int, OilPrice> {
        return db.oilDao().getOilPrice(day, city)
    }

    fun insertAll(list: List<OilPrice>) {
        db.oilDao().insertAll(list)
    }

    fun hasTodayOilPrice(day: String): Boolean {
        return db.oilDao().getOilPriceCount(day) > 0
    }
}

@ExperimentalPagingApi
class OilRemoteMediator constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val day: String
) : RemoteMediator<Int, OilPrice>() {

    private lateinit var cacheData:List<OilPrice>

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, OilPrice>
    ): MediatorResult {
        return when(loadType) {
            LoadType.REFRESH -> {
                //api调用次数有限，本地有就不去调用api了
                if (DateUtils.getCurDay() == day && !localDataSource.hasTodayOilPrice(day)) {
                    val data = remoteDataSource.getTodayOilPrice()
                    val curDay = DateUtils.getCurDay()
                    data.forEach {
                        it.time = curDay
                    }
                    cacheData = data
                    localDataSource.insertAll(data)
                }
                MediatorResult.Success(endOfPaginationReached = true)
            }
            LoadType.APPEND -> MediatorResult.Success(endOfPaginationReached = true)
            LoadType.PREPEND -> MediatorResult.Success(endOfPaginationReached = true)
        }
//        return if (DateUtils.getCurDay() == day && cacheData.isNullOrEmpty()) {
//            val data = remoteDataSource.getTodayOilPrice()
//            val curDay = DateUtils.getCurDay()
//            data.forEach{
//                it.time = curDay
//            }
//            cacheData = data
//            localDataSource.insertAll(data)
//            MediatorResult.Success(endOfPaginationReached = true)
//        } else{
//            //只能查询今日数据
//            MediatorResult.Success(endOfPaginationReached = true)
//        }
    }

}

class RemoteDataSource @Inject constructor(
    private val serviceManager: ServiceManager
) {
    suspend fun getTodayOilPrice(): List<OilPrice> {
        val wrapper = serviceManager.oilService.getTodayOilPrice()
        if (wrapper.error_code == 0) {
            return wrapper.result
        } else {
            throw Exception(wrapper.reason)
        }
    }

}