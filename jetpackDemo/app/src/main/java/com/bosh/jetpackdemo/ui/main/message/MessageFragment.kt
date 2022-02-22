package com.bosh.jetpackdemo.ui.main.message

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.bosh.jetpackdemo.R
import com.bosh.jetpackdemo.databinding.FragmentMessageBinding
import com.bosh.jetpackdemo.extension.bindView
import kotlinx.coroutines.flow.collectLatest

class MessageFragment : Fragment(R.layout.fragment_message) {

    private val binding : FragmentMessageBinding by bindView()
    private val mAdapter = MessageAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.srlMain.setOnRefreshListener { mAdapter.refresh() }
        binding.rvMain.adapter = mAdapter
        lifecycleScope.launchWhenCreated {
            mAdapter.loadStateFlow.collectLatest{ loadStates ->
                binding.srlMain.isRefreshing = loadStates.mediator?.refresh is LoadState.Loading
            }
        }
    }
}