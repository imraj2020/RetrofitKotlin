package com.cse.retrofitkotlin.data.model.token


import com.google.gson.annotations.SerializedName

data class ResponseToken(
    @SerializedName("access_token")
    val accessToken: String? = null,
    @SerializedName("refresh_token")
    val refreshToken: String? = null
)