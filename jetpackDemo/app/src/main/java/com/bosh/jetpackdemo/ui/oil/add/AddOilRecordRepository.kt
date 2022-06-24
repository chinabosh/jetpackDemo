package com.bosh.jetpackdemo.ui.oil.add

import com.bosh.jetpackdemo.db.AppDatabase
import com.bosh.jetpackdemo.entity.OilHistory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author lzq
 * @date  2022/5/18
 */
class AddOilRecordRepository @Inject constructor(
    private val db: AppDatabase
) {
    fun getInitData(): Flow<OilHistory?> {
        return flow { emit(db.oilHistory().getInitOilInfo()) }
    }

    fun insertData(oilHistory: OilHistory): Flow<String> {
        return flow {
            db.oilHistory().insert(oilHistory)
            emit("success")
        }
    }
}