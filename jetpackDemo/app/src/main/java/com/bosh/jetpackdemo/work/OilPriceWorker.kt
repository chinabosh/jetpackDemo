package com.bosh.jetpackdemo.work

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.bosh.jetpackdemo.db.AppDatabase
import com.bosh.jetpackdemo.net.ServiceManager
import com.bosh.jetpackdemo.utils.DateUtils
import com.bosh.jetpackdemo.utils.LogUtils
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

/**
 * 定时任务获取油价
 * @author lzq
 * @date  2022/3/9
 */
@HiltWorker
class OilPriceWorker @AssistedInject  constructor(
    @Assisted context: Context,
    @Assisted parameters: WorkerParameters,
    private val db : AppDatabase,
    private val serviceManager: ServiceManager
) : CoroutineWorker(context, parameters) {
    override suspend fun doWork(): Result {
        Log.i("oilPriceWorker", "doWork")
        LogUtils.i2File("oilPriceWorker", "doWork")
        val curDay = DateUtils.getCurDay()
        val data = db.oilDao().getOilPrice(curDay)
        if (data.isEmpty()) {
            Log.i("oilPriceWorker", "get from remote")
            LogUtils.i2File("oilPriceWorker", "get from remote")
            val wrapper = serviceManager.oilService.getTodayOilPrice()
            if (wrapper.error_code == 0) {
                wrapper.result.forEach {
                    it.time = curDay
                }
                db.oilDao().insertAll(wrapper.result)
            } else {
                Result.failure()
            }
        }
        return Result.success()
    }

}