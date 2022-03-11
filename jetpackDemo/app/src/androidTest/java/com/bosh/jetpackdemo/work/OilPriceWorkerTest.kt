package com.bosh.jetpackdemo.work

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.work.ListenableWorker
import androidx.work.testing.TestListenableWorkerBuilder
import androidx.work.testing.WorkManagerTestInitHelper
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author lzq
 * @date  2022/3/11
 */
@RunWith(AndroidJUnit4::class)
class OilPriceWorkerTest {
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun doWork() {
        val worker = TestListenableWorkerBuilder<OilPriceWorker>(context).build()
        val testDriver = WorkManagerTestInitHelper.getTestDriver(context)
//        testDriver.setPeriodDelayMet()
        val result = worker.startWork().get()
        assertEquals(result, `is`(ListenableWorker.Result.success()))
    }
}