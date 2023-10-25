package com.cse.retrofitkotlin.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cse.retrofitkotlin.core.DataState
import com.cse.retrofitkotlin.core.NetworkState
import com.cse.retrofitkotlin.data.model.login.RequestLogin
import com.cse.retrofitkotlin.data.model.login.ResponseLogin
import com.cse.retrofitkotlin.repos.UserRepos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import okhttp3.ResponseBody
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repos: UserRepos) : ViewModel() {

    private var _loginResponse = MutableLiveData<DataState<Response<ResponseBody>>>()
    val loginResponse: LiveData<DataState<Response<ResponseBody>>> = _loginResponse


    fun loginUser(requestLogin: RequestLogin) {
        _loginResponse.postValue(DataState.Loading)

        viewModelScope.launch {

            val response = repos.loginUser(requestLogin)

            _loginResponse.postValue(response)


            Log.d("TAG", "loginUser: $response ")


        }


    }
}