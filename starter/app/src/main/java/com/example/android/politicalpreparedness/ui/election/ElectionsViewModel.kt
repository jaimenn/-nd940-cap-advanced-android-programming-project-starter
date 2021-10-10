package com.example.android.politicalpreparedness.ui.election

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.android.politicalpreparedness.R
import com.example.android.politicalpreparedness.core.base.BaseViewModel
import com.example.android.politicalpreparedness.core.base.NavigationCommand
import com.example.android.politicalpreparedness.data.Election
import com.example.android.politicalpreparedness.data.ElectionDataSource
import com.example.android.politicalpreparedness.data.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ElectionsViewModel(private val repository: ElectionDataSource, private val app: Application) :
    BaseViewModel(app) {

    // Create live data val for upcoming elections
    private val _upcomingElections = MutableStateFlow<List<Election>>(emptyList())
    val upcomingElections: StateFlow<List<Election>>
        get() = _upcomingElections.asStateFlow()

    // Create live data val for saved elections
    val savedElections = repository.getSavedElections()

    init {
        fetchUpcomingElections()
    }

    private fun fetchUpcomingElections() {
        showLoading.value = true
        viewModelScope.launch {
            val result = repository.getUpcomingElections()
            showLoading.value = false
            when (result) {
                is Result.Success -> {
                    _upcomingElections.emit(result.data.elections)
                }
                is Result.Error -> {
                    //_upcomingElections.value = emptyList()
                    showToast.value = app.getString(R.string.error_upcoming_election)
                }
            }
        }
    }

    fun onClickElectionItem(election: Election) {
        navigationCommand.value = NavigationCommand.To(
            ElectionsFragmentDirections.actionElectionsFragmentToVoterInfoFragment(
                election
            )
        )
    }
}