package com.cse.retrofitkotlin.ui.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController

import com.cse.retrofitkotlin.R
import com.cse.retrofitkotlin.base.BaseFragment
import com.cse.retrofitkotlin.core.NetworkState
import com.cse.retrofitkotlin.data.model.register.RequestRegister
import com.cse.retrofitkotlin.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {


    private val viewModel: RegisterViewModel by viewModels()
    override fun responseObserver() {
        viewModel.userCreateResponse.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkState.Error -> {
                    binding.progressHorizontal.visibility = View.GONE
                    Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_LONG).show()
                }

                is NetworkState.Loading -> {
                    binding.progressHorizontal.visibility = View.VISIBLE

                }

                is NetworkState.Success -> {

                    Toast.makeText(requireContext(), "User Created!", Toast.LENGTH_LONG).show()
                    Log.d("TAG", "Data :${it.data} ")

                    binding.progressHorizontal.visibility = View.GONE
                    findNavController().popBackStack()

                }
            }


        }


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginBtn.setOnClickListener {

            findNavController().popBackStack()

        }


        binding.registerBtn.setOnClickListener {
            binding.progressHorizontal.visibility = View.VISIBLE
            val name = binding.userName.text.toString().trim()
            val email = binding.userEmail.text.toString().trim()
            val password = binding.userPasword.text.toString().trim()
            val request = RequestRegister(
                name = name,
                email = email,
                password = password,
                avatar = "https://dmarkcy.com/images/team/web/mehedi.png"
            )
            viewModel.register(request)


        }


    }


}