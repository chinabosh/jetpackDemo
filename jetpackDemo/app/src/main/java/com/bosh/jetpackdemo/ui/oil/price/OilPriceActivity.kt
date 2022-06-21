package com.bosh.jetpackdemo.ui.oil.price

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.work.*
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.datetime.datePicker
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.afollestad.materialdialogs.list.listItemsSingleChoice
import com.bosh.jetpackdemo.databinding.ActivityOilPriceBinding
import com.bosh.jetpackdemo.extension.inflate
import com.bosh.jetpackdemo.utils.DateUtils
import com.bosh.jetpackdemo.utils.Mapper
import com.bosh.jetpackdemo.work.OilPriceWorker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class OilPriceActivity : AppCompatActivity() {

    private val binding: ActivityOilPriceBinding by inflate()
    private val viewModel: OilPriceViewModel by viewModels()
    private lateinit var filter: Filter
    private val mAdapter: OilPriceAdapter = OilPriceAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        binding.layoutMain.ivFilter.setOnClickListener {
            binding.dlMain.openDrawer(GravityCompat.END)
        }
        binding.layoutMain.ivBack.setOnClickListener { finish() }
        binding.layoutFilter.tvFilterSure.setOnClickListener {
            binding.dlMain.closeDrawer(GravityCompat.END)
            lifecycleScope.launch {
                val prov = binding.layoutFilter.tvProvince.text.toString()
                filter = Filter(
                    binding.layoutFilter.tvDate.text.toString(),
                    prov
                )
                viewModel.changeFilter(filter)
            }
        }
        binding.layoutFilter.tvProvince.setOnClickListener {
            MaterialDialog(this)
                .lifecycleOwner(this)
                .listItemsSingleChoice(items = Mapper.getOilProvince()) { _, _, text ->
                    binding.layoutFilter.tvProvince.text = text
                }.show()
        }
        binding.layoutFilter.tvDate.setOnClickListener {
            MaterialDialog(this)
                .lifecycleOwner(this)
                .datePicker(
                    maxDate = Calendar.getInstance(),
                    currentDate = Calendar.getInstance()
                ) { _, datetime ->
                    binding.layoutFilter.tvDate.text = DateUtils.getDate(datetime)
                }.show()
        }
        binding.layoutMain.srlMain.setOnRefreshListener { mAdapter.refresh() }
        binding.layoutMain.rvMain.adapter = mAdapter
        lifecycleScope.launchWhenCreated {
            mAdapter.loadStateFlow.collectLatest { loadStates ->
                binding.layoutMain.srlMain.isRefreshing =
                    loadStates.mediator?.refresh is LoadState.Loading
            }
        }
        lifecycleScope.launchWhenCreated {
            val curDay = DateUtils.getCurDay()
            binding.layoutFilter.tvDate.text = curDay
            val prov = "福建"
            binding.layoutFilter.tvProvince.text = prov
            filter = Filter(curDay, prov)
            viewModel.changeFilter(filter)
        }

        lifecycleScope.launchWhenCreated {
            viewModel.priceList.catch {
                Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
            }.collectLatest {
                mAdapter.submitData(it)
            }
        }
        val request = PeriodicWorkRequestBuilder<OilPriceWorker>(6, TimeUnit.HOURS)
            .setConstraints(
                Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED)
                    .setRequiresBatteryNotLow(true)
                    .setRequiresCharging(false)
                    .setRequiresStorageNotLow(false)
                    .build()
            )
            .addTag("test")
            .build()
        WorkManager.getInstance(this@OilPriceActivity)
            .enqueueUniquePeriodicWork(
                "oil_price",
                ExistingPeriodicWorkPolicy.KEEP, request
            )
    }
}