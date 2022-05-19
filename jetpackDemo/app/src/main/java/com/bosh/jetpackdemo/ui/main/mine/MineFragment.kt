package com.bosh.jetpackdemo.ui.main.mine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bosh.jetpackdemo.R
import com.bosh.jetpackdemo.databinding.FragmentMineBinding
import com.bosh.jetpackdemo.extension.bindView
import com.bosh.jetpackdemo.extension.inflateBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MineFragment : Fragment(R.layout.fragment_mine) {

    private val binding : FragmentMineBinding by bindView()
    val viewModel: MineViewModel by viewModels()
}