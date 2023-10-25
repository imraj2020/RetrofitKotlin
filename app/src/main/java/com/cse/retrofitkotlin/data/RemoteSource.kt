//package com.cse.retrofitkotlin.data
//
//import com.cse.retrofitkotlin.data.model.login.RequestLogin
//import com.cse.retrofitkotlin.data.model.register.RequestRegister
//import com.cse.retrofitkotlin.data.model.register.ResponseRegister
//import com.cse.retrofitkotlin.network.ApiService
//import retrofit2.Response
//import javax.inject.Inject
//
//class RemoteSource @Inject constructor(private val service:ApiService) {
//   suspend fun loginUser(requestLogin: RequestLogin)= service.loginUser(requestLogin)
//    suspend fun register(request: RequestRegister) = service.register(request)
//    suspend fun profile(): Response<ResponseRegister> = service.profile()
//}