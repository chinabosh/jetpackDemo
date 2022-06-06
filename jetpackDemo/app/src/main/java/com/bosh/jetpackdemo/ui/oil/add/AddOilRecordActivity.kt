package com.bosh.jetpackdemo.ui.oil.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import cn.qqtheme.framework.picker.DatePicker
import com.bosh.jetpackdemo.R
import com.bosh.jetpackdemo.databinding.ActivityAddOilRecordBinding
import com.bosh.jetpackdemo.extension.inflate
import com.bosh.jetpackdemo.utils.DateUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class AddOilRecordActivity : AppCompatActivity() {

    private val binding: ActivityAddOilRecordBinding by inflate()
    private val viewModel: AddOilRecordViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        lifecycleScope.launchWhenCreated {
            viewModel.initInfo.collectLatest {
                binding.etInitMile.setText(it?.mile.toString())
                binding.etInitOil.setText(it?.addOil.toString())
                binding.etInitMile.isEnabled = false
                binding.etInitOil.isEnabled = false
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.getInitData()

            binding.ivBack.setOnClickListener { finish() }
            binding.etInitMile.addTextChangedListener {
                viewModel.setInitMile(it.toString())
            }
            binding.etInitOil.addTextChangedListener {
                viewModel.setInitOil(it.toString())
            }

            binding.etOil.addTextChangedListener {
                viewModel.setOil(it.toString(), binding.etOilPrice.text.toString())
            }
            viewModel.oil.collectLatest {
                binding.etOil.setText(it)
            }
            binding.etOilPrice.addTextChangedListener {
                viewModel.setPrice(
                    it.toString(), binding.etOil.text.toString(),
                    binding.etAmount.text.toString()
                )
            }
            viewModel.amount.collectLatest {
                binding.etAmount.setText(it)
            }
            binding.etAmount.addTextChangedListener {
                viewModel.setAmount(binding.etOilPrice.text.toString(), it.toString())
            }

            viewModel.date.collectLatest {
                binding.tvDate.text = it
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

            viewModel.errorMsg.collectLatest {
                Toast.makeText(this@AddOilRecordActivity, it, Toast.LENGTH_SHORT).show()
            }
            viewModel.successMsg.collectLatest {
                Toast.makeText(this@AddOilRecordActivity, it, Toast.LENGTH_SHORT).show()
                finish()
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