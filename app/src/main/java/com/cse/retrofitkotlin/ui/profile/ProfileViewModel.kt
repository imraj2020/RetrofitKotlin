package com.cse.retrofitkotlin.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cse.retrofitkotlin.core.DataState
import com.cse.retrofitkotlin.core.NetworkState
import com.cse.retrofitkotlin.data.model.register.ResponseRegister
import com.cse.retrofitkotlin.repos.UserRepos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repos: UserRepos):ViewModel() {

    private var _response = MutableLiveData<DataState<Response<ResponseBody>>>()
    val userProfileResponse: LiveData<DataState<Response<ResponseBody>>> = _response


    fun profile() {
        _response.postValue(DataState.Loading)

        viewModelScope.launch {
            val response = repos.profile()
            _response.postValue(response)


        }


    }

}