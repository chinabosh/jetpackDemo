package com.bosh.jetpackdemo.ui.main.mine

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bosh.jetpackdemo.R
import com.bosh.jetpackdemo.databinding.FragmentMineBinding
import com.bosh.jetpackdemo.extension.bindView
import com.bosh.jetpackdemo.extension.inflateBinding
import com.bosh.jetpackdemo.ui.oil.add.AddOilRecordActivity
import com.bosh.jetpackdemo.ui.oil.price.OilPriceActivity
import com.chad.library.adapter.base.listener.OnItemClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MineFragment : Fragment(R.layout.fragment_mine) {

    private val binding : FragmentMineBinding by bindView()
    val viewModel: MineViewModel by viewModels()
    private val mAdapter: FunctionAdapter by lazy { FunctionAdapter(viewModel.functionItems) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    private fun initData() {
        lifecycleScope.launchWhenCreated {
            binding.rvFunction.layoutManager = LinearLayoutManager(activity)
            mAdapter.setOnItemClickListener { _, _, position ->
                when (position) {
                    0 -> startActivity(Intent(activity, OilPriceActivity::class.java))
                    1 -> startActivity(Intent(activity, AddOilRecordActivity::class.java))
                }
            }
            binding.rvFunction.adapter = mAdapter
        }
    }
}