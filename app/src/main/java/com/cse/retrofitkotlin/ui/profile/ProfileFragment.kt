package com.cse.retrofitkotlin.ui.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import coil.load
import com.cse.retrofitkotlin.base.BaseFragment
import com.cse.retrofitkotlin.core.DataState
import com.cse.retrofitkotlin.core.NetworkState
import com.cse.retrofitkotlin.data.model.profile.Response_Profile
import com.cse.retrofitkotlin.databinding.FragmentProfileBinding
import com.google.gson.Gson
import com.mehedi.manualdiu.utils.NetworkUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val viewModel: ProfileViewModel by viewModels()
    override fun responseObserver() {

        viewModel.userProfileResponse.observe(viewLifecycleOwner) {
            when (it) {

                is DataState.Success -> {
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

                            val response = Gson().fromJson(body, Response_Profile::class.java)

                            serData(response)


                        } else {
                            //howToast(getString(R.string.something_went_wrong))
                        }
                    }
                }

                is DataState.Loading -> {
                    // loadingDialog.show()

                    binding.progressHorizontal.visibility = View.VISIBLE
                    Log.d("TAG", "Loading....: ")
                }

                is DataState.Error -> {
                    //loadingDialog.dismiss()
                    binding.progressHorizontal.visibility = View.GONE
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

        viewModel.profile()
    }


    private fun serData(response: Response_Profile) {

        binding.apply {
            nameTextView.text = response.name
            roleTextView.text = response.role
            emailTextView.text = response.email
            avatarImageView.load(response.avatar)


        }


    }

}

