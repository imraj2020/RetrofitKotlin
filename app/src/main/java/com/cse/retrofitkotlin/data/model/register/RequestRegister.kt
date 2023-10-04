package com.cse.retrofitkotlin.data.model.register


import com.google.gson.annotations.SerializedName

data class RequestRegister(
    @SerializedName("avatar")
    var avatar: String?,
    @SerializedName("email")
    var email: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("password")
    var password: String?
)