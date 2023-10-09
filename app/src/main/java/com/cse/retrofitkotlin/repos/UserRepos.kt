package com.cse.retrofitkotlin.repos

import com.cse.retrofitkotlin.data.LocalSource
import com.cse.retrofitkotlin.data.RemoteSource
import com.cse.retrofitkotlin.data.model.RequestLogin
import com.cse.retrofitkotlin.data.model.register.RequestRegister
import com.cse.retrofitkotlin.data.model.register.ResponseRegister
import retrofit2.Response
import javax.inject.Inject

class UserRepos @Inject constructor(
    private val localSource: LocalSource,
    private  val remoteSource: RemoteSource
) {

    suspend fun loginUser(requestLogin: RequestLogin) = remoteSource.loginUser(requestLogin)
    suspend fun register(request: RequestRegister) = remoteSource.register(request)
    suspend fun profile(): Response<ResponseRegister> = remoteSource.profile()
}