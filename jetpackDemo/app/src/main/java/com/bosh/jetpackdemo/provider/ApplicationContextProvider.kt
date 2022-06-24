package com.bosh.jetpackdemo.provider

import android.annotation.SuppressLint
import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.util.Log

/**
 * @author bosh
 * @date 2020-03-30
 */
class ApplicationContextProvider : ContentProvider() {
    override fun onCreate(): Boolean {
        mContext = context
        Log.e("test", "mContext == null ? " + (mContext == null))
        ContextProvider.get()
        return false
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        return null
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return 0
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        var mContext: Context? = null
    }
}