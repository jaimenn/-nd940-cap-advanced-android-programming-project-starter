package com.example.android.politicalpreparedness.data.source.remote.models

import com.example.android.politicalpreparedness.data.Election
import com.example.android.politicalpreparedness.data.VoterInfoDTO
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class VoterInfoResponse(
    val election: Election,
    val pollingLocations: String? = null,
    val contests: String? = null,
    val state: List<State>? = null,
    val electionElectionOfficials: List<ElectionOfficial>? = null
)

fun VoterInfoResponse.asVoterInfo(): VoterInfoDTO {
    val electionInfo = this.state?.first()?.electionAdministrationBody
    return VoterInfoDTO(
        name = electionInfo?.name ?: "",
        electionInfoUrl = electionInfo?.electionInfoUrl ?: "",
        votingLocationFinderUrl = electionInfo?.votingLocationFinderUrl ?: "",
        ballotInfoUrl = electionInfo?.ballotInfoUrl ?: "",
        correspondenceAddress = electionInfo?.correspondenceAddress?.toFormattedString() ?: ""
    )
}