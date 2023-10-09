package com.cse.retrofitkotlin.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cse.retrofitkotlin.core.NetworkState
import com.cse.retrofitkotlin.data.model.register.ResponseRegister
import com.cse.retrofitkotlin.repos.UserRepos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repos: UserRepos):ViewModel() {

    private var _response = MutableLiveData<NetworkState<ResponseRegister>>()
    val userProfileResponse: LiveData<NetworkState<ResponseRegister>> = _response


    fun profile() {
        _response.postValue(NetworkState.Loading())

        viewModelScope.launch {

            val response = repos.profile()

            if (response.isSuccessful) {
                _response.postValue(NetworkState.Success(response.body()!!))

            } else {
                _response.postValue(NetworkState.Error("Something went Wrong!"))
            }

            Log.d("TAG", "loginUser: $response ")


        }


    }

}