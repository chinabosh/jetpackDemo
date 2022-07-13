package com.bosh.jetpackdemo.ui.shares.addshares

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.bosh.jetpackdemo.databinding.ActivityAddSharesBinding
import com.bosh.jetpackdemo.extension.inflate
import com.bosh.jetpackdemo.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * 添加基金基本信息
 */
@AndroidEntryPoint
class AddSharesActivity : BaseActivity() {

    private val binding: ActivityAddSharesBinding by inflate()
    private val viewModel: AddSharesViewModel by  viewModels()

    override fun init() {
        TODO("Not yet implemented")
    }
}