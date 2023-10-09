package com.cse.retrofitkotlin.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

 class PrefesManager @Inject constructor(@ApplicationContext context: Context) {


    private val Pref = context.getSharedPreferences("auth-pref", Context.MODE_PRIVATE)


    fun setPref(key: String, Value: String) {
        val editor = Pref.edit()
        editor.putString(key, Value)
        editor.apply()
    }

    fun getPref(key: String): String {
        return Pref.getString(key, "").toString()
    }

}