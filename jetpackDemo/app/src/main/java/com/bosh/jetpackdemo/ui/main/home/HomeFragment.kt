package com.bosh.jetpackdemo.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.bosh.jetpackdemo.R
import com.bosh.jetpackdemo.databinding.FragmentHomeBinding
import com.bosh.jetpackdemo.extension.bindView
import com.bosh.jetpackdemo.ui.oil.price.OilPriceActivity
import com.bosh.jetpackdemo.utils.LogUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding : FragmentHomeBinding by bindView()

    private val viewModel: HomeViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("test", "onViewCreated")
        binding.tvRandom.setOnClickListener{
            viewModel.randomText()
        }
        binding.tvOilPrice.setOnClickListener {
            startActivity(Intent(activity, OilPriceActivity::class.java))
        }
        viewModel.text.observe(this, {
            binding.etTest.setText(it)
        })
        binding.tvTest.setOnClickListener {
            LogUtils.i2File("homeFragment", "get work info")
            WorkManager.getInstance(context!!)
                .getWorkInfosForUniqueWorkLiveData("oil_price")
                .observe(this, {
                    it.apply {
                        when(this[0].state) {
                            WorkInfo.State.BLOCKED -> println("BLOCKED")
                            WorkInfo.State.CANCELLED -> println("CANCELLED")
                            WorkInfo.State.RUNNING -> println("RUNNING")
                            WorkInfo.State.ENQUEUED -> println("ENQUEUED")
                            WorkInfo.State.FAILED -> println("FAILED")
                            WorkInfo.State.SUCCEEDED -> println("SUCCEEDED")
                            else -> println("else status ${this[0]}")
                        }
                    }
                })
        }
    }
}