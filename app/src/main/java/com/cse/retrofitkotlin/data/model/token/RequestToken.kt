package com.cse.retrofitkotlin.data.model.token


import com.google.gson.annotations.SerializedName

data class RequestToken(
    @SerializedName("refreshToken")
    val refreshToken: String? = null
)