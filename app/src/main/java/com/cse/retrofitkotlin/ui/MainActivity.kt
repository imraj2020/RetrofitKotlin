package com.cse.retrofitkotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.cse.retrofitkotlin.core.NetworkState
import com.cse.retrofitkotlin.data.model.RequestLogin
import com.cse.retrofitkotlin.databinding.ActivityMainBinding
import com.cse.retrofitkotlin.ui.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)










    }
}