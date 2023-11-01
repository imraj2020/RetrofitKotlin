package com.cse.retrofitkotlin.ui.home.repository

import com.cse.retrofitkotlin.base.BaseRepository
import com.cse.retrofitkotlin.data.LocalSource
import com.cse.retrofitkotlin.data.model.login.RequestLogin
import com.cse.retrofitkotlin.network.ApiService
import javax.inject.Inject

class HomeRepo @Inject constructor(
    private val service: ApiService
): BaseRepository() {

    suspend fun loginUser(requestLogin: RequestLogin) =
        safeApiCall { service.loginUser(requestLogin) }

    suspend fun getCategories() = safeApiCall { service.getCategories() }


}