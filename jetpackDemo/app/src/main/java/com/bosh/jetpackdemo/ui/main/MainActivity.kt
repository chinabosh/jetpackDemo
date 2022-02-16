package com.bosh.jetpackdemo.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bosh.jetpackdemo.R
import com.bosh.jetpackdemo.databinding.ActivityMainBinding
import com.bosh.jetpackdemo.extension.inflate

class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by inflate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //启动动画、防止白屏
        installSplashScreen()
        //先加载布局。。。
        binding
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        val navController : NavController = findNavController(R.id.nav_host_fragment)
        setupBottomNavMenu(navController)
    }

    private fun setupBottomNavMenu(navController: NavController) {
        binding.bnvMain.setupWithNavController(navController)
    }
}