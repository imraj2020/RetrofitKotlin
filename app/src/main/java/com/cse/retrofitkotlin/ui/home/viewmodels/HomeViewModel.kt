package com.cse.retrofitkotlin.ui.home.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cse.retrofitkotlin.core.DataState
import com.cse.retrofitkotlin.data.model.login.RequestLogin
import com.cse.retrofitkotlin.repos.UserRepos
import com.cse.retrofitkotlin.ui.home.repository.HomeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repos: HomeRepo) : ViewModel() {

    private var _categoryResponse = MutableLiveData<DataState<Response<ResponseBody>>>()
    val categoryResponse: LiveData<DataState<Response<ResponseBody>>> = _categoryResponse


    fun getCategoryResponse(){
       _categoryResponse.postValue(DataState.Loading)

        viewModelScope.launch {

            val  response = repos.getCategories()
            _categoryResponse.postValue(response)
        }
    }



}