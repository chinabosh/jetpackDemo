package com.bosh.jetpackdemo.ui.shares.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.bosh.jetpackdemo.databinding.ActivitySharesListBinding
import com.bosh.jetpackdemo.extension.inflate
import com.bosh.jetpackdemo.ui.base.BaseActivity

class SharesListActivity : BaseActivity() {

    private val binding: ActivitySharesListBinding by inflate()
    private val viewModel: SharesListViewModel by  viewModels()

    override fun init() {
        TODO("Not yet implemented")
    }
}