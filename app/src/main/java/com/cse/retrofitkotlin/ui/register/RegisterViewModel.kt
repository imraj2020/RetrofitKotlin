package com.cse.retrofitkotlin.ui.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cse.retrofitkotlin.core.NetworkState
import com.cse.retrofitkotlin.repos.UserRepos
import com.mehedi.manualdiu.data.models.register.RequestRegister
import com.mehedi.manualdiu.data.models.register.ResponseRegister
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repo: UserRepos) : ViewModel() {

    private var _response = MutableLiveData<NetworkState<ResponseRegister>>()
    val userCreateResponse: LiveData<NetworkState<ResponseRegister>> = _response


    fun register(request: RequestRegister) {
        _response.postValue(NetworkState.loading())

        viewModelScope.launch {

            val response = repo.register(request)

            if (response.isSuccessful) {
                _response.postValue(NetworkState.Success(response.body()!!))

            } else {
                _response.postValue(NetworkState.error("Something went Wrong!"))
            }

            Log.d("TAG", "loginUser: $response ")


        }


    }


}