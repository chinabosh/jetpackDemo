package com.bosh.jetpackdemo.utils

import android.content.Context
import android.util.Log
import com.bosh.jetpackdemo.BuildConfig
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ArrayBlockingQueue

/**
 * log工具类
 * @author lzq
 * @date 2018/7/17
 */
@Suppress("unused")
object LogUtils {

    private val TEST: String
        get() = "test"

    private var isDebug: Boolean = false

    /**
     * log日志存放路径
     */
    private var logPath: String = ""

    /**
     * 日期格式
     * 一小时一个log文件
     */
    private val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd_HH", Locale.CHINA)

    private val logDateFormat: SimpleDateFormat =
        SimpleDateFormat("MM-dd HH:mm:ss.SSS", Locale.CHINA)

    private val logList: ArrayBlockingQueue<String> = ArrayBlockingQueue(100)

    private const val ONE_DAY: Long = 24 * 60 * 60 * 1000

    private var isRunning: Boolean = false

    private const val MAX_LENGTH: Int = 2000

    /**
     * 初始化，须在使用之前设置，最好在Application创建时调用
     *
     * @param context applicationContext
     */
    fun init(context: Context) {
        //获得文件储存路径
        logPath = getFilePath(context)
        isDebug = BuildConfig.DEBUG
        startWriteLog()
    }

    /**
     * 获得文件存储路径
     *
     * @return 文件存储路径
     */
    private fun getFilePath(context: Context): String {
        return context.externalCacheDir!!.absolutePath
    }

    private const val VERBOSE: Char = 'v'

    private const val DEBUG: Char = 'd'

    private const val INFO: Char = 'i'

    private const val WARN: Char = 'w'

    private const val ERROR: Char = 'e'

    @SuppressWarnings("unused")
    fun v2File(tag: String, msg: String) {
        val log = "v/$tag: $msg"
        logList.offer(log)
    }

    fun v(msg: String) {
        if (BuildConfig.DEBUG) {
            dealLong(TEST, msg, VERBOSE)
        }
    }

    fun d2File(tag: String, msg: String) {
//        writeToFile(DEBUG, tag, msg);
        val log = "d/$tag: $msg"
        logList.offer(log)
        if (isDebug) {
            dealLong(tag, msg, DEBUG)
        }
    }

    fun d(msg: String) {
        if (BuildConfig.DEBUG) {
            dealLong(TEST, msg, DEBUG)
        }
    }

    fun i2File(tag: String, msg: String) {
//        writeToFile(INFO, tag, msg);
        val log = "i/$tag: $msg"
        logList.offer(log)
        if (isDebug) {
            dealLong(tag, msg, INFO)
        }
    }

    fun i(msg: String) {
        if (BuildConfig.DEBUG) {
            dealLong(TEST, msg, INFO)
        }
    }

    fun w2File(tag: String, msg: String) {
//        writeToFile(WARN, tag, msg);
        val log = "w/$tag: $msg"
        logList.offer(log)
        if (isDebug) {
            dealLong(tag, msg, WARN)
        }
    }

    fun w(msg: String) {
        if (BuildConfig.DEBUG) {
            dealLong(TEST, msg, WARN)
        }
    }

    fun e2File(tag: String, msg: String) {
//        writeToFile(ERROR, tag, msg);
        val log = "e/$tag: $msg"
        logList.offer(log)
        if (isDebug) {
            dealLong(tag, msg, ERROR)
        }
    }

    fun e(msg: String) {
        if (BuildConfig.DEBUG) {
            dealLong(TEST, msg, ERROR)
        }
    }

    fun deleteLogFileInDays(day: Int) {
        val filename = "$logPath/"
        val file = File(filename)
        if (!file.exists()) {
            return
        }
        val logFiles = file.listFiles()
        logFiles?.forEach {
            val lastModified = it.lastModified()
            val calendar = Calendar.getInstance()
            if (calendar.timeInMillis - lastModified > day * ONE_DAY) {
                if (!it.delete()) {
                    i2File("LogUtils", it.name + " delete fail")
                }
            }
        }
    }

    fun stopWriteLog() {
        isRunning = false
    }

    /**
     * 使用线程写log
     */
    @OptIn(DelicateCoroutinesApi::class)
    fun startWriteLog() {
        if (isRunning) {
            return
        }
        GlobalScope.launch(Dispatchers.IO) {
            isRunning = true
            while (isRunning) {
                val msg = logList.poll()
                if (msg.isNullOrEmpty()) {
                    Thread.sleep(2000)
                    continue
                }
                val currentDate = Date()
                //log日志名，使用时间命名，保证不重复
                val fileName = logPath + "/" + dateFormat.format(currentDate) + ".log"
                //log日志内容，可以自行定制
                val log = "\r\n" + logDateFormat.format(currentDate) + ":" + msg

                //如果父路径不存在
                val file = File(logPath)
                if (!file.exists()) {
                    file.mkdirs()//创建父路径
                }

                //FileOutputStream会自动调用底层的close()方法，不用关闭
                var fos: FileOutputStream
                var bw: BufferedWriter? = null
                try {
                    //这里的第二个参数代表追加还是覆盖，true为追加，false为覆盖
                    fos = FileOutputStream(fileName, true)
                    bw = BufferedWriter(OutputStreamWriter(fos))
                    bw.write(log)

                } catch (e: IOException) {
                    e.printStackTrace()
                } finally {
                    try {
                        bw?.close()//关闭缓冲流
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }.invokeOnCompletion { isRunning = false }
    }

    /**
     *  处理log过长显示不全问题，一条logcat最多显示4k，
     */
    private fun dealLong(tag: String, msg: String, level: Char) {
        val length = msg.length
        if (length <= MAX_LENGTH) {
            log(tag, msg, level)
            return
        }
        var index = 0
        while (index < length) {
            val sub = if (index + MAX_LENGTH <= length) {
                msg.substring(index, index + MAX_LENGTH)
            } else {
                msg.substring(index, length)
            }
            index += MAX_LENGTH
            log(tag, sub, level)
        }
    }

    private fun log(tag: String, msg: String, level: Char) {
        when (level) {
            VERBOSE ->
                Log.v(tag, msg)
            DEBUG ->
                Log.d(tag, msg)
            INFO ->
                Log.i(tag, msg)
            WARN ->
                Log.w(tag, msg)
            ERROR ->
                Log.e(tag, msg)
        }
    }
}
