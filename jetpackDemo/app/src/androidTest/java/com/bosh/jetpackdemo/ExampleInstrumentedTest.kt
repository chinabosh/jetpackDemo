package com.bosh.jetpackdemo

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.afollestad.date.dayOfMonth
import com.afollestad.date.month
import com.afollestad.date.year

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.util.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.bosh.jetpackdemo", appContext.packageName)
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = -1000000000
        println("test:${calendar.year}-${calendar.month}-${calendar.dayOfMonth}")
    }
}