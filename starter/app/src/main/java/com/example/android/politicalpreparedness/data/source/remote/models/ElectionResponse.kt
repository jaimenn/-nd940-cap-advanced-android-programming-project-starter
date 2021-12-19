package com.example.android.politicalpreparedness.data.source.remote.models

import com.example.android.politicalpreparedness.data.Election
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ElectionResponse(
    val kind: String,
    val elections: List<Election>
)
