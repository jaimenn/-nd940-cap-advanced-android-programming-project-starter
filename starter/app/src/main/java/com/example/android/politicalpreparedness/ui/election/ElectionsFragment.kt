package com.example.android.politicalpreparedness.ui.election

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.android.politicalpreparedness.R
import com.example.android.politicalpreparedness.core.base.BaseFragment
import com.example.android.politicalpreparedness.databinding.FragmentElectionBinding
import com.example.android.politicalpreparedness.ui.election.adapter.ElectionListAdapter
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class ElectionsFragment : BaseFragment() {

    override val _viewModel: ElectionsViewModel by viewModel()

    private lateinit var binding: FragmentElectionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_election,
            container, false
        )
        binding.lifecycleOwner = this
        binding.viewModel = _viewModel

        setupUI()

        return binding.root
    }

    private fun setupUI() {
        val electionsAdapter = ElectionListAdapter(ElectionListAdapter.ClickListener {
            _viewModel.onClickElectionItem(it)
        })
        binding.rvUpcomingElection.adapter = electionsAdapter

        lifecycleScope.launchWhenStarted {
            _viewModel.upcomingElections.collect {
                electionsAdapter.submitList(it)
            }
        }
        val savedElectionsAdapter = ElectionListAdapter(ElectionListAdapter.ClickListener {
            _viewModel.onClickElectionItem(it)
        })
        binding.rvSavedElection.adapter = savedElectionsAdapter
        _viewModel.savedElections.observe(viewLifecycleOwner, { elections ->
            savedElectionsAdapter.submitList(elections)
        })
    }
}