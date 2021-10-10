package com.example.android.politicalpreparedness.ui.election

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.android.politicalpreparedness.R
import com.example.android.politicalpreparedness.core.base.BaseViewModel
import com.example.android.politicalpreparedness.data.Election
import com.example.android.politicalpreparedness.data.ElectionDataSource
import com.example.android.politicalpreparedness.data.Result
import com.example.android.politicalpreparedness.data.VoterInfoDTO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class VoterInfoViewModel(
    private val repository: ElectionDataSource,
    val election: Election,
    val app: Application
) : BaseViewModel(app) {

    /**
     * Hint: The saved state can be accomplished in multiple ways. It is directly related to how elections are saved/removed from the database.
     */

    private var _isFollow = MutableStateFlow(false)
    val isFollow: StateFlow<Boolean>
        get() = _isFollow.asStateFlow()

    private var _voterInfo = MutableStateFlow(VoterInfoDTO())
    val voterInfo: StateFlow<VoterInfoDTO>
        get() = _voterInfo.asStateFlow()

    init {
        checkIsFollowing()
        fetchVoterInfo()
    }

    private fun checkIsFollowing() {
        viewModelScope.launch {
            _isFollow.value = repository.getElection(election.id) != null
        }
    }

    private fun fetchVoterInfo() {
        viewModelScope.launch {
            if (election.division.state.isNotEmpty()) {
                val address = "${election.division.country},${election.division.state}"
                when (val result = repository.getVoterInfo(address, election.id)) {
                    is Result.Success -> {
                        _voterInfo.value = result.data
                    }
                    is Result.Error -> {
                        _voterInfo.value = VoterInfoDTO()
                        showToast.value = app.getString(R.string.error_voter_information)
                    }
                }
            }
        }
    }

    fun toggleElection(election: Election) {
        viewModelScope.launch {
            if (isFollow.value) {
                repository.delete(election)
            } else {
                repository.insert(election)
            }
            checkIsFollowing()
        }
    }

    fun onURLClick(url: String) {
        openUrl.value = url
    }
}