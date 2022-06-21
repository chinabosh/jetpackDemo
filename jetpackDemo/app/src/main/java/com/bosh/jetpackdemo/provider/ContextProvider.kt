package com.bosh.jetpackdemo.provider

import android.annotation.SuppressLint
import android.content.Context
import java.lang.IllegalStateException

/**
 * 采用ContentProvider实现无侵入式context
 * @author bosh
 * @date 2020-03-30
 */
class ContextProvider private constructor(val context: Context) {

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var instance: ContextProvider? = null
        fun get(): ContextProvider? {
            if (instance == null) {
                synchronized(ContextProvider::class.java) {
                    if (instance == null) {
                        val context = ApplicationContextProvider.mContext
                            ?: throw IllegalStateException("context == null")
                        instance = ContextProvider(context)
                    }
                }
            }
            return instance
        }
    }
}