package com.cse.retrofitkotlin.ui.login

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
import com.cse.retrofitkotlin.data.model.RequestLogin
import com.cse.retrofitkotlin.databinding.FragmentLoginBinding
import com.cse.retrofitkotlin.utils.KEY_ACCESS
import com.cse.retrofitkotlin.utils.KEY_REFRESH
import com.cse.retrofitkotlin.utils.PrefesManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {




    @Inject
    lateinit var prefsManager: PrefesManager

    private val viewModel: LoginViewModel by viewModels()
    override fun responseObserver() {
        viewModel.loginresponse.observe(viewLifecycleOwner) {

            when (it) {
                is NetworkState.Error -> {
                    binding.progressHorizontal.visibility = View.GONE
                    Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_LONG).show()
                }

                is NetworkState.Loading -> {
                    binding.progressHorizontal.visibility = View.VISIBLE

                }

                is NetworkState.Success -> {

                    it.data?.accessToken?.let { it1 -> prefsManager.setPref(KEY_ACCESS, it1) }
                    it.data?.refreshToken?.let { it1 -> prefsManager.setPref(KEY_REFRESH, it1) }


                    Toast.makeText(requireContext(), "Login Success ! ", Toast.LENGTH_LONG).show()
                    Log.d("TAG", "Data :${it.data} ")

                    binding.progressHorizontal.visibility = View.GONE
                    findNavController().navigate(R.id.action_loginFragment_to_profileFragment)


                }
            }


        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.RegisterBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_regsiterFragment)
        }







        binding.loginBtn.setOnClickListener {

            binding.progressHorizontal.visibility = View.VISIBLE

            val loginRequest = RequestLogin("john@mail.com", "changeme")

            val email = binding.userEmail.text.toString().trim()
            val password = binding.userPasword.text.toString().trim()

            // val loginRequest = RequestLogin(email = email, password = password)

            viewModel.loginuser(loginRequest)


        }

    }


}