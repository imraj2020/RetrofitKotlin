package com.cse.retrofitkotlin.network

import com.cse.retrofitkotlin.data.model.RequestLogin
import com.cse.retrofitkotlin.data.model.ResponseLogin
import com.cse.retrofitkotlin.data.model.register.RequestRegister
import com.cse.retrofitkotlin.data.model.register.ResponseRegister
import com.cse.retrofitkotlin.utils.LOGIN_ENDPOINT
import com.cse.retrofitkotlin.utils.REGISTER_ENDPOINT
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {


    @POST(LOGIN_ENDPOINT)
    suspend fun loginUser(@Body requestLogin: RequestLogin) : Response<ResponseLogin>
    @POST(REGISTER_ENDPOINT)
    suspend fun register(@Body request: RequestRegister): Response<ResponseRegister>

}