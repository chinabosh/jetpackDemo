package com.bosh.jetpackdemo.ui.oil.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import cn.qqtheme.framework.picker.DatePicker
import com.bosh.jetpackdemo.R
import com.bosh.jetpackdemo.databinding.ActivityAddOilRecordBinding
import com.bosh.jetpackdemo.extension.inflate
import com.bosh.jetpackdemo.ui.base.BaseActivity
import com.bosh.jetpackdemo.utils.DateUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddOilRecordActivity : BaseActivity() {

    private val binding: ActivityAddOilRecordBinding by inflate()
    private val viewModel: AddOilRecordViewModel by viewModels()

    override fun init() {
        binding.ivBack.setOnClickListener { finish() }
        binding.etInitMile.doAfterTextChanged {
            viewModel.setInitMile(it.toString())
        }
        binding.etInitOil.doAfterTextChanged {
            viewModel.setInitOil(it.toString())
        }

        binding.etOil.doOnTextChanged { text, _, _, _ ->
            Log.i("test", binding.etOilPrice.text.toString())
            viewModel.setOil(text.toString(), binding.etOilPrice.text.toString())
        }
        binding.etOilPrice.doOnTextChanged { text, _, _, _ ->
            viewModel.setPrice(
                text.toString(), binding.etOil.text.toString(),
                binding.etAmount.text.toString()
            )
        }
        binding.etAmount.doOnTextChanged { text, _, _, _ ->

            viewModel.setAmount(binding.etOilPrice.text.toString(), text.toString())
        }

        binding.tvDate.setOnClickListener {
            val context = this@AddOilRecordActivity
            val datePicker = DatePicker(context, DatePicker.YEAR_MONTH_DAY)
            //setSelectedItem必须放在setRangeEnd后
            datePicker.setRangeStart(2020, 1, 1)
            datePicker.setRangeEnd(
                getCurrentDate()[0] + 1, getCurrentDate()[1],
                getCurrentDate()[2]
            )
            datePicker.setSelectedItem(
                getCurrentDate()[0], getCurrentDate()[1],
                getCurrentDate()[2]
            )
            datePicker.setTopBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.purple_700
                )
            )
            datePicker.setCancelTextColor(ContextCompat.getColor(context, R.color.white))
            datePicker.setTitleTextColor(ContextCompat.getColor(context, R.color.white))
            datePicker.setSubmitTextColor(ContextCompat.getColor(context, R.color.white))
            datePicker.setOnDatePickListener(object : DatePicker.OnYearMonthDayPickListener {
                override fun onDatePicked(year: String?, month: String?, day: String?) {
                    val date = "$year-$month-$day"
                    viewModel.setDate(date)
                }
            })
            datePicker.show()
        }
        binding.tvAdd.setOnClickListener {
            viewModel.addRecord(
                mileText = binding.etMile.text.toString(),
                addOilText = binding.etOil.text.toString(),
                addPriceText = binding.etOilPrice.text.toString(),
                timeText = binding.tvDate.text.toString(),
                oilLeftText = binding.etOilLeft.text.toString()
            )
        }
        lifecycleScope.launchWhenCreated {
            viewModel.getInitData()
        }

        viewModel.oil.observe(this@AddOilRecordActivity, {
            binding.etOil.setText(it)
        })

        viewModel.amount.observe(this@AddOilRecordActivity, {
            binding.etAmount.setText(it)
        })

        viewModel.date.observe(this@AddOilRecordActivity, {
            binding.tvDate.text = it
        })

        viewModel.errorMsg.observe(this@AddOilRecordActivity, {
            Toast.makeText(this@AddOilRecordActivity, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.successMsg.observe(this@AddOilRecordActivity, {
            Toast.makeText(this@AddOilRecordActivity, it, Toast.LENGTH_SHORT).show()
            finish()
        })

        lifecycleScope.launch {
            viewModel.initInfo.collect {
                binding.etInitMile.setText(it?.mile.toString())
                binding.etInitOil.setText(it?.addOil.toString())
                binding.etInitMile.isEnabled = false
                binding.etInitOil.isEnabled = false
            }
        }
    }

    private fun getCurrentDate(): IntArray {
        val strDate = DateUtils.getCurDay()
        val year = DateUtils.getYear(strDate)
        val month = DateUtils.getMonth(strDate) + 1
        val day = DateUtils.getDay(strDate)
        return intArrayOf(year, month, day)
    }
}