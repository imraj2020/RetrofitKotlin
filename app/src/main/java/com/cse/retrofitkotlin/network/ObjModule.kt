package com.cse.retrofitkotlin.network

import com.cse.retrofitkotlin.data.LocalSource
import com.cse.retrofitkotlin.data.RemoteSource
import com.cse.retrofitkotlin.repos.UserRepos
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ObjModule {



    @Provides
    @Singleton
    fun Retrofit(baseUrl : String):LoginService = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(LoginService::class.java)


    @Provides
    @Singleton
    fun localsource()=LocalSource()


    @Provides
    @Singleton
    fun remotesource(retrofit:LoginService) = RemoteSource(retrofit)


    @Provides
    @Singleton
    fun userRepo(localSource: LocalSource,remoteSource: RemoteSource)=UserRepos(localSource, remoteSource)

}