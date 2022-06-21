package com.bosh.jetpackdemo.ui.main

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.work.*
import com.bosh.jetpackdemo.R
import com.bosh.jetpackdemo.databinding.ActivityMainBinding
import com.bosh.jetpackdemo.extension.inflate
import com.bosh.jetpackdemo.work.OilPriceWorker
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by inflate()
    val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //启动动画、防止白屏
        installSplashScreen()
        //先加载布局。。。
        binding
        initWork(this)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        val navController : NavController = findNavController(R.id.nav_host_fragment)
        setupBottomNavMenu(navController)
    }

    private fun setupBottomNavMenu(navController: NavController) {
        binding.bnvMain.setupWithNavController(navController)
    }


    private fun initWork(context: Context) {
        val request = PeriodicWorkRequestBuilder<OilPriceWorker>(6, TimeUnit.HOURS)
            .setConstraints(
                Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED)
                    .setRequiresBatteryNotLow(true)
                    .setRequiresCharging(false)
                    .setRequiresStorageNotLow(false)
                    .build()
            )
            .addTag("oil_price")
            .build()
        WorkManager.getInstance(context)
            .enqueueUniquePeriodicWork(
                "oil_price",
                ExistingPeriodicWorkPolicy.KEEP, request
            )
    }
}