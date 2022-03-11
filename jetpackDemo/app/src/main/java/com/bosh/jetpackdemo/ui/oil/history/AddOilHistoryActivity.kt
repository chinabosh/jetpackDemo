package com.bosh.jetpackdemo.ui.oil.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.bosh.jetpackdemo.R
import com.bosh.jetpackdemo.databinding.ActivityAddOilHistoryBinding
import com.bosh.jetpackdemo.extension.inflate

class AddOilHistoryActivity : AppCompatActivity() {

    private val binding: ActivityAddOilHistoryBinding by inflate()
    private val viewModel: AddOilHistoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}