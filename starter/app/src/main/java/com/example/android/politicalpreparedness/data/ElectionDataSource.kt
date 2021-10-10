package com.example.android.politicalpreparedness.data

import androidx.lifecycle.LiveData
import com.example.android.politicalpreparedness.data.source.remote.models.ElectionResponse
import com.example.android.politicalpreparedness.data.source.remote.models.RepresentativeResponse

interface ElectionDataSource {


    suspend fun getUpcomingElections(): Result<ElectionResponse>

    fun getSavedElections(): LiveData<List<Election>>

    suspend fun getElection(id: Long): Election?

    suspend fun insert(election: Election)

    suspend fun delete(election: Election)

    suspend fun getVoterInfo(address: String, electionId: Long): Result<VoterInfoDTO>

    suspend fun getRepresentatives(address: String): Result<RepresentativeResponse>

}