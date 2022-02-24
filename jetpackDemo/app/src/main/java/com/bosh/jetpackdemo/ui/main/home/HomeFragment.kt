package com.bosh.jetpackdemo.ui.main.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bosh.jetpackdemo.R
import com.bosh.jetpackdemo.databinding.FragmentHomeBinding
import com.bosh.jetpackdemo.extension.bindView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding : FragmentHomeBinding by bindView()

    private val viewModel: HomeViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("test", "onViewCreated")
        binding.tvRandom.setOnClickListener{
            viewModel.randomText()
        }
        viewModel.text.observe(this, {
            binding.etTest.setText(it)
        })
    }
}