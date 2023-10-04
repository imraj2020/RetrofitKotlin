package com.cse.retrofitkotlin.data

import com.cse.retrofitkotlin.data.model.RequestLogin
import com.cse.retrofitkotlin.network.LoginService
import com.mehedi.manualdiu.data.models.register.RequestRegister
import javax.inject.Inject

class RemoteSource @Inject constructor(private val service:LoginService) {
   suspend fun loginUser(requestLogin: RequestLogin)= service.loginUser(requestLogin)
    suspend fun register(request: RequestRegister) = service.register(request)
}