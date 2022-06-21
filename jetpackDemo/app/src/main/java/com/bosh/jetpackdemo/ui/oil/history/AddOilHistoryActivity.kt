package com.bosh.jetpackdemo.ui.oil.history

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bosh.jetpackdemo.R
import com.bosh.jetpackdemo.databinding.ActivityAddOilHistoryBinding
import com.bosh.jetpackdemo.extension.inflate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddOilHistoryActivity : AppCompatActivity() {

    private val binding: ActivityAddOilHistoryBinding by inflate()
    private val viewModel: AddOilHistoryViewModel by viewModels()

    private val mAdapter: OilHistoryAdapter by lazy { OilHistoryAdapter(viewModel.records) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun init() {
        binding.rvMain.layoutManager = LinearLayoutManager(this)
        binding.rvMain.adapter = mAdapter
        binding.ivBack.setOnClickListener { finish() }

        viewModel.adapterData.observe(this , {
            mAdapter.notifyDataSetChanged()
        })

        viewModel.hundredOil.observe(this, {
            binding.tvHundredOil.text = it
        })

        viewModel.monthOil.observe(this, {
            binding.tvMonthAverageOil.text = it
        })

        lifecycleScope.launchWhenCreated {
            viewModel.initData()
        }
    }
}