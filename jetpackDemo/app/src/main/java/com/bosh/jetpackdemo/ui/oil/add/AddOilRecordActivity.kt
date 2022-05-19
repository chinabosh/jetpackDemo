package com.bosh.jetpackdemo.ui.oil.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bosh.jetpackdemo.R
import com.bosh.jetpackdemo.databinding.ActivityAddOilRecordBinding
import com.bosh.jetpackdemo.extension.inflate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class AddOilRecordActivity : AppCompatActivity() {

    private val binding: ActivityAddOilRecordBinding by inflate()
    private val viewModel: AddOilRecordViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding
        init()
    }

    private fun init() {
        lifecycleScope.launchWhenCreated {
            viewModel.initMile.collectLatest {
                binding.etInitMile.setText(it?.mile.toString())
                binding.etInitOil.setText(it?.addOil.toString())
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.getInitData()
        }
    }
}