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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import android.app.NotificationManager

import android.app.NotificationChannel

import android.os.Build

import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.bosh.jetpackdemo.R


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

    private  val  channelId = "VERBOSE_NOTIFICATION"

    override suspend fun doWork(): Result {
        showNotification(applicationContext)
        return withContext(Dispatchers.IO) {
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
            hideNotification(applicationContext)
            Result.success()
        }
    }

    private fun showNotification(context: Context) {
        val mBuilder: NotificationCompat.Builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("My notification")
            .setContentText("ddd")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("ddd")
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = "Channel_name"
            val description = "description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description
            val notificationManager = context.getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(channel)
        }
        val notificationManager: NotificationManagerCompat = NotificationManagerCompat.from(context)
        notificationManager.notify(100, mBuilder.build())
    }

    private fun hideNotification(context: Context) {
        val notificationManager: NotificationManagerCompat = NotificationManagerCompat.from(context)
        notificationManager.cancel(100)
    }
}