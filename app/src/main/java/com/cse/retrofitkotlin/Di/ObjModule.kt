package com.cse.retrofitkotlin.Di

import android.content.Context
import com.cse.nativelib2.NativeLib
import com.cse.retrofitkotlin.data.LocalSource
import com.cse.retrofitkotlin.data.RemoteSource
import com.cse.retrofitkotlin.network.ApiService
import com.cse.retrofitkotlin.repos.UserRepos
import com.cse.retrofitkotlin.utils.AuthInterCepter
import com.cse.retrofitkotlin.utils.PrefesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ObjModule {



    @Provides
    @Singleton
    fun urlProvides(): String {
        return NativeLib().stringFromJNI()
    }

    @Provides
    @Singleton
    fun providePrefs(@ApplicationContext context: Context) = PrefesManager(context)

    @Provides
    @Singleton
    fun provideHttpClient(interceptor: AuthInterCepter): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }
    @Provides
    @Singleton
    fun Retrofit(baseUrl : String, interceptorClient: OkHttpClient): ApiService = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(interceptorClient)
        .build()
        .create(ApiService::class.java)


    @Provides
    @Singleton
    fun localsource()=LocalSource()


    @Provides
    @Singleton
    fun remotesource(retrofit: ApiService) = RemoteSource(retrofit)


    @Provides
    @Singleton
    fun userRepo(localSource: LocalSource,remoteSource: RemoteSource)=UserRepos(localSource, remoteSource)

}