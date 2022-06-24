package com.bosh.jetpackdemo.ui.main.message

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.bosh.jetpackdemo.R
import com.bosh.jetpackdemo.databinding.FragmentMessageBinding
import com.bosh.jetpackdemo.extension.bindView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MessageFragment : Fragment(R.layout.fragment_message) {

    private val binding : FragmentMessageBinding by bindView()
    private val mAdapter = MessageAdapter()
    private val viewModel: MessageViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.tvTest.setOnClickListener {
            viewModel.getTestData()
        }
        binding.tvPrefill.setOnClickListener {
            viewModel.saveTestDataToDb()
        }
        binding.tvDatabase.setOnClickListener {
            viewModel.getDbData()
        }
        binding.srlMain.setOnRefreshListener { mAdapter.refresh() }
        binding.rvMain.adapter = mAdapter
        lifecycleScope.launchWhenCreated {
            mAdapter.loadStateFlow.collectLatest{ loadStates ->
                binding.srlMain.isRefreshing = loadStates.mediator?.refresh is LoadState.Loading
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.testData.collectLatest {
                mAdapter.submitData(it)
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.dbData.collectLatest {
                mAdapter.submitData(it)
            }
        }
    }
}