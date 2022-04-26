package com.bosh.jetpackdemo.ui.oil.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.bosh.jetpackdemo.R
import com.bosh.jetpackdemo.databinding.ActivityAddOilRecordBinding
import com.bosh.jetpackdemo.extension.inflate

class AddOilRecordActivity : AppCompatActivity() {

    private val binding: ActivityAddOilRecordBinding by inflate()
    private val viewModel: AddOilRecordViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {

    }
}