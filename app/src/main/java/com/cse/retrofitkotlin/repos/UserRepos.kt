package com.cse.retrofitkotlin.repos

import com.cse.retrofitkotlin.data.LocalSource
import com.cse.retrofitkotlin.data.RemoteSource
import com.cse.retrofitkotlin.data.model.RequestLogin
import javax.inject.Inject

class UserRepos @Inject constructor(
    private val localSource: LocalSource,
    private  val remoteSource: RemoteSource
) {

    suspend fun loginUser(requestLogin: RequestLogin) = remoteSource.loginUser(requestLogin)
}