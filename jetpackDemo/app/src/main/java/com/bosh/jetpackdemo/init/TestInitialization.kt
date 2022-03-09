package com.bosh.jetpackdemo.init

import android.content.Context
import android.util.Log
import androidx.startup.Initializer
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.bosh.jetpackdemo.work.OilPriceWorker
import java.util.concurrent.TimeUnit

/**
 * @author lzq
 * @date  2022/2/15
 */
class TestInitialization : Initializer<Unit> {
    override fun create(context: Context) {
        Log.i("TestInitialization", "create")
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        //need to start first
        return mutableListOf()
    }
}