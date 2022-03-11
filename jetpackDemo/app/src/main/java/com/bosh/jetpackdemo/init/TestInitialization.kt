package com.bosh.jetpackdemo.init

import android.content.Context
import android.util.Log
import androidx.startup.Initializer
import com.bosh.jetpackdemo.utils.LogUtils
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * @author lzq
 * @date  2022/2/15
 */
class TestInitialization : Initializer<Unit> {
    @OptIn(DelicateCoroutinesApi::class)
    override fun create(context: Context) {
        Log.i("TestInitialization", "create")
        LogUtils.init(context)
        GlobalScope.launch(Dispatchers.IO) {
            LogUtils.deleteLogFileInDays(30)
        }
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        //need to start first
        return mutableListOf()
    }
}