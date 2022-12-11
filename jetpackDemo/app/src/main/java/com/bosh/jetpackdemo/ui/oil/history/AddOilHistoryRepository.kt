package com.bosh.jetpackdemo.ui.oil.history

import com.bosh.jetpackdemo.db.AppDatabase
import com.bosh.jetpackdemo.entity.OilHistory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author lzq
 * @date  2022/6/8
 */
class AddOilHistoryRepository @Inject constructor(
    private val db: AppDatabase
) {
    fun getOilHistory(): Flow<List<OilHistory>> {
        return flow { emit(db.oilHistory().getAll()) }
    }

    fun getOilHistoryByDesc(): Flow<List<OilHistory>> {
        return flow { emit(db.oilHistory().getAllByDesc()) }
    }
}