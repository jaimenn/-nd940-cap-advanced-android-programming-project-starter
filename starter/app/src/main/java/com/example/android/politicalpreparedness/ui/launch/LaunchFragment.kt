package com.example.android.politicalpreparedness.ui.launch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.politicalpreparedness.core.base.BaseFragment
import com.example.android.politicalpreparedness.databinding.FragmentLaunchBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LaunchFragment : BaseFragment() {

    override val _viewModel: LaunchViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentLaunchBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = _viewModel

        return binding.root
    }
}
