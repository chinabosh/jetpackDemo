package com.bosh.jetpackdemo.ui.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.afollestad.materialdialogs.list.listItemsSingleChoice
import com.bosh.jetpackdemo.databinding.ActivitySettingBinding
import com.bosh.jetpackdemo.extension.inflate
import com.bosh.jetpackdemo.ui.base.BaseActivity
import com.bosh.jetpackdemo.utils.Mapper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingActivity : BaseActivity() {

    private val binding: ActivitySettingBinding by inflate()
    private val viewModel: SettingViewModel by viewModels()

    override fun init() {
        binding.ivBack.setOnClickListener { finish() }
        binding.tvProvince.setOnClickListener {
            MaterialDialog(this)
                .lifecycleOwner(this)
                .listItemsSingleChoice(items = Mapper.getOilProvince()) { _, _, text ->
                    binding.tvProvince.text = text
                    viewModel.changeDefaultProvince(text.toString())
                }
                .show()
        }

        viewModel.prov.observe(this, {
            binding.tvProvince.text = it
        })
    }
}