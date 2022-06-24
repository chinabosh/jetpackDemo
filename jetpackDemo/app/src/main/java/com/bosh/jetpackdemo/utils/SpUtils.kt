package com.bosh.jetpackdemo.utils

import android.content.Context
import android.content.SharedPreferences
import com.bosh.jetpackdemo.provider.ContextProvider

/**
 * @author lzq
 * @date 2019-07-14
 */
class SpUtils private constructor(context: Context) {
    fun getBoolean(key: String?, defaultValue: Boolean): Boolean {
        return sPreferences.getBoolean(key, defaultValue)
    }

    fun getFloat(key: String?, defaultValue: Float): Float {
        return sPreferences.getFloat(key, defaultValue)
    }

    fun getInt(key: String?, defaultValue: Int): Int {
        return sPreferences.getInt(key, defaultValue)
    }

    fun getLong(key: String?, defaultValue: Long): Long {
        return sPreferences.getLong(key, defaultValue)
    }

    fun getString(key: String?, defaultValue: String?): String {
        return sPreferences.getString(key, defaultValue)!!
    }

    fun putBoolean(key: String?, value: Boolean) {
        val editor: SharedPreferences.Editor = sPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun putFloat(key: String?, value: Float) {
        val editor: SharedPreferences.Editor = sPreferences.edit()
        editor.putFloat(key, value)
        editor.apply()
    }

    fun putInt(key: String?, value: Int) {
        val editor: SharedPreferences.Editor = sPreferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun putLong(key: String?, value: Long) {
        val editor: SharedPreferences.Editor = sPreferences.edit()
        editor.putLong(key, value)
        editor.apply()
    }

    fun putString(key: String?, value: String?) {
        val editor: SharedPreferences.Editor = sPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun remove(key: String?): Boolean {
        val edit: SharedPreferences.Editor = sPreferences.edit()
        return edit.remove(key).commit()
    }

    companion object {
        private var sInstance: SpUtils? = null
        private lateinit var sPreferences: SharedPreferences
        val instance: SpUtils?
            get() {
                if (sInstance == null) {
                    sInstance = SpUtils(ContextProvider.get()!!.context)
                }
                return sInstance
            }
    }

    init {
        sPreferences =
            context.getSharedPreferences(context.packageName + "_preferences", Context.MODE_PRIVATE)
    }
}