package com.cse.retrofitkotlin.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cse.retrofitkotlin.R
import com.cse.retrofitkotlin.base.BaseFragment
import com.cse.retrofitkotlin.core.NetworkState
import com.cse.retrofitkotlin.data.model.RequestLogin
import com.cse.retrofitkotlin.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment() : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()
    override fun responseObserver() {
        TODO("Not yet implemented")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loginresponse.observe(viewLifecycleOwner){

            when (it) {
                is NetworkState.error -> {
                    binding.progressHorizontal.visibility = View.GONE
                    Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_LONG).show()
                }

                is NetworkState.loading -> {
                    binding.progressHorizontal.visibility = View.VISIBLE

                }

                is NetworkState.Success -> {

                    Toast.makeText(requireContext(), "Login Success ! ", Toast.LENGTH_LONG).show()
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