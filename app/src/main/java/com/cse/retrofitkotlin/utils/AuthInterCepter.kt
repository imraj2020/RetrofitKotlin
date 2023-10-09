package com.cse.retrofitkotlin.utils

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterCepter @Inject constructor(private val prefesManager: PrefesManager): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
       val request = chain.request().newBuilder()
        request.addHeader("Authorization", "Bearer ${prefesManager.getPref(KEY_ACCESS)}")

        return chain.proceed(request.build())
    }
}