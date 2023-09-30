package com.cse.retrofitkotlin.core

sealed class NetworkState<T>(var data: T?=null,var message : String?=null) {

    class loading<T>:NetworkState<T>()
    class Success<T>(data: T):NetworkState<T>(data)
    class error<T>(msg: String):NetworkState<T>(message=msg)
}