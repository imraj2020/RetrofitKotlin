package com.cse.retrofitkotlin.repos

import com.cse.retrofitkotlin.base.BaseRepository
import com.cse.retrofitkotlin.data.LocalSource

import com.cse.retrofitkotlin.data.model.login.RequestLogin
import com.cse.retrofitkotlin.data.model.register.RequestRegister
import com.cse.retrofitkotlin.data.model.register.ResponseRegister
import com.cse.retrofitkotlin.network.ApiService
import retrofit2.Response
import javax.inject.Inject

class UserRepos @Inject constructor(
    private val localSource: LocalSource,
  //  private  val remoteSource: RemoteSource
    private val service: ApiService
): BaseRepository() {

    suspend fun loginUser(requestLogin: RequestLogin) =
        safeApiCall { service.loginUser(requestLogin) }

    suspend fun profile() = safeApiCall { service.profile() }

//    suspend fun refreshToken(requestToken: RequestToken): Response<ResponseToken> =
//        remoteSource.refreshToken(requestToken)
//
//    suspend fun register(request: RequestRegister) = remoteSource.register(request)
}