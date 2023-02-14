package com.chanho.compose_1.data.model.artist

import com.google.gson.annotations.SerializedName

data class Artist(
    @SerializedName("cast")
    val cast: List<Cast>,
    @SerializedName("crew")
    val crew: List<Crew>,
    @SerializedName("id")
    val id: Int
)