package com.bosh.jetpackdemo.ui.shares.addrecord

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.bosh.jetpackdemo.R
import com.bosh.jetpackdemo.databinding.ActivityAddTradeRecordBinding
import com.bosh.jetpackdemo.extension.inflate
import com.bosh.jetpackdemo.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * 添加基金买卖记录
 */
@AndroidEntryPoint
class AddTradeRecordActivity : BaseActivity() {

    private val binding: ActivityAddTradeRecordBinding by inflate()
    private val viewModel: AddTradeViewModel by viewModels()

    override fun init() {
        binding.ivBack.setOnClickListener { finish() }
    }
}