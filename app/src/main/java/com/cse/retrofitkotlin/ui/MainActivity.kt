package com.cse.retrofitkotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.cse.retrofitkotlin.R
import com.cse.retrofitkotlin.core.NetworkState
import com.cse.retrofitkotlin.data.model.RequestLogin
import com.cse.retrofitkotlin.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel:MainViewModel by viewModels()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel.loginresponse.observe(this){

            when (it) {
                is NetworkState.error -> {
                    binding.progressHorizontal.visibility = View.GONE
                    Toast.makeText(this, "${it.message}", Toast.LENGTH_LONG).show()
                }

                is NetworkState.loading -> {
                    binding.progressHorizontal.visibility = View.VISIBLE

                }

                is NetworkState.Success -> {

                    Toast.makeText(this, "Login Success ! ", Toast.LENGTH_LONG).show()
                    Log.d("TAG", "Data :${it.data} ")

                    binding.progressHorizontal.visibility = View.GONE

                }
            }
        }


        binding.loginBtn.setOnClickListener {
            binding.progressHorizontal.visibility = View.VISIBLE
            val loginrequest = RequestLogin("john@mail.com", "changeme")
            val email = binding.userEmail.text.toString().trim()
            val password = binding.userPasword.text.toString().trim()

            viewModel.loginuser(loginrequest)
        }

    }
}