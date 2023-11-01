package com.cse.retrofitkotlin.ui.login

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cse.retrofitkotlin.R
import com.cse.retrofitkotlin.base.BaseFragment
import com.cse.retrofitkotlin.core.DataState
import com.cse.retrofitkotlin.core.NetworkState
import com.cse.retrofitkotlin.data.model.login.RequestLogin
import com.cse.retrofitkotlin.data.model.login.ResponseLogin
import com.cse.retrofitkotlin.databinding.FragmentLoginBinding
import com.cse.retrofitkotlin.utils.KEY_ACCESS
import com.cse.retrofitkotlin.utils.KEY_REFRESH
import com.cse.retrofitkotlin.utils.PrefesManager
import com.google.gson.Gson
import com.mehedi.manualdiu.utils.NetworkUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {




    @Inject
    lateinit var prefsManager: PrefesManager

    private val viewModel: LoginViewModel by viewModels()
    override fun responseObserver() {
//        viewModel.loginresponse.observe(viewLifecycleOwner) {
//
//            when (it) {
//                is NetworkState.Error -> {
//                    binding.progressHorizontal.visibility = View.GONE
//                    Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_LONG).show()
//                }
//
//                is NetworkState.Loading -> {
//                    binding.progressHorizontal.visibility = View.VISIBLE
//
//                }
//
//                is NetworkState.Success -> {
//
//                    it.data?.accessToken?.let { it1 -> prefsManager.setPref(KEY_ACCESS, it1) }
//                    it.data?.refreshToken?.let { it1 -> prefsManager.setPref(KEY_REFRESH, it1) }
//
//
//                    Toast.makeText(requireContext(), "Login Success ! ", Toast.LENGTH_LONG).show()
//                    Log.d("TAG", "Data :${it.data} ")
//
//                    binding.progressHorizontal.visibility = View.GONE
//                    findNavController().navigate(R.id.action_loginFragment_to_profileFragment)
//
//
//                }
//            }
//
//
//        }
    }

    private fun observeCurrencyGetResponse() {
        viewModel.loginResponse.observe(viewLifecycleOwner) {
            when (it) {

                is DataState.Success -> {
                    binding.animationView.visibility = View.GONE
                    binding.progressHorizontal.visibility = View.GONE
                    // dismiss loading
                    // loadingDialog.dismiss()

                    // get data
                    val body = it.value.body()?.string()

                    if (body.isNullOrBlank()) {

                        var eerr = it.value.errorBody()?.string()
                        Log.e("TAG", eerr.toString())
                    } else {
                        Log.e("TAG", body.toString())
                        if (NetworkUtils.isValidResponse(it)) {

                            val response = Gson().fromJson(body, ResponseLogin::class.java)
                            response.accessToken?.let { it1 ->
                                prefsManager.setPref(
                                    KEY_ACCESS,
                                    it1
                                )
                            }
                            response.refreshToken?.let { it1 ->
                                prefsManager.setPref(
                                    KEY_REFRESH,
                                    it1
                                )
                            }
                            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)


                        } else {
                            //howToast(getString(R.string.something_went_wrong))
                        }
                    }
                }

                is DataState.Loading -> {
                    // loadingDialog.show()
                    binding.animationView.visibility = View.VISIBLE

                    binding.progressHorizontal.visibility = View.VISIBLE
                    Log.d("TAG", "Loading....: ")
                }

                is DataState.Error -> {
                    //loadingDialog.dismiss()
                    binding.progressHorizontal.visibility = View.GONE
                    binding.animationView.visibility = View.GONE
                    binding.mainView.visibility = View.VISIBLE
                    Log.d("TAG", "${it.errorBody.toString()}....: ")
                    if (it.isNetworkError) {
                        //showToast(getString(R.string.internet_conn_lost_title))
                    } else {
                        //reLogin()
                    }
                    Log.e("TAG", "$it")
                }
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeCurrencyGetResponse()

        binding.RegisterBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_regsiterFragment)
        }







        binding.loginBtn.setOnClickListener {

            binding.progressHorizontal.visibility = View.VISIBLE

            val loginRequest = RequestLogin("nico@gmail.com", "1234")

            val email = binding.userEmail.text.toString().trim()
            val password = binding.userPasword.text.toString().trim()

            // val loginRequest = RequestLogin(email = email, password = password)

            viewModel.loginUser(loginRequest)


        }

    }


}