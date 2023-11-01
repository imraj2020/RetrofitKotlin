package com.cse.retrofitkotlin.network

import com.cse.retrofitkotlin.data.model.login.RequestLogin
import com.cse.retrofitkotlin.data.model.login.ResponseLogin
import com.cse.retrofitkotlin.data.model.register.RequestRegister
import com.cse.retrofitkotlin.data.model.register.ResponseRegister
import com.cse.retrofitkotlin.data.model.token.RequestToken
import com.cse.retrofitkotlin.data.model.token.ResponseToken
import com.cse.retrofitkotlin.utils.CATEGORY_ENDPOINT
import com.cse.retrofitkotlin.utils.LOGIN_ENDPOINT
import com.cse.retrofitkotlin.utils.PROFILE_ENDPOINT
import com.cse.retrofitkotlin.utils.REGISTER_ENDPOINT
import com.cse.retrofitkotlin.utils.TOKEN_ENDPOINT
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {


    @POST(LOGIN_ENDPOINT)
    suspend fun loginUser(@Body requestLogin: RequestLogin) : Response<ResponseBody>
    @POST(REGISTER_ENDPOINT)
    suspend fun register(@Body request: RequestRegister): Response<ResponseRegister>
    @GET(PROFILE_ENDPOINT)
    suspend fun profile(): Response<ResponseBody>

    @POST(TOKEN_ENDPOINT)
    suspend fun refreshToken(@Body requestToken: RequestToken): Response<ResponseToken>
    @GET(CATEGORY_ENDPOINT)
    suspend fun getCategories():Response<ResponseBody>

}