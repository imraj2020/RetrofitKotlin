package com.cse.retrofitkotlin.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cse.retrofitkotlin.core.NetworkState
import com.cse.retrofitkotlin.data.model.RequestLogin
import com.cse.retrofitkotlin.data.model.ResponseLogin
import com.cse.retrofitkotlin.repos.UserRepos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repos: UserRepos) : ViewModel() {

    private var _loginresponse = MutableLiveData<NetworkState<ResponseLogin>>()

    val loginresponse: LiveData<NetworkState<ResponseLogin>> = _loginresponse

    fun loginuser(requestLogin: RequestLogin) {

        viewModelScope.launch {
            val response = repos.loginUser(requestLogin)
            if (response.isSuccessful) {
                _loginresponse.postValue(NetworkState.Success(response.body()!!))
            } else {
                _loginresponse.postValue(NetworkState.error("Something Went Wrong"))
            }
            Log.d("TAG", "loginUser: $response ")

        }


    }


}