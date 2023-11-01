package com.cse.retrofitkotlin.ui.home.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cse.retrofitkotlin.R
import com.cse.retrofitkotlin.base.BaseFragment
import com.cse.retrofitkotlin.core.DataState
import com.cse.retrofitkotlin.data.model.login.ResponseLogin
import com.cse.retrofitkotlin.databinding.FragmentHomeBinding
import com.cse.retrofitkotlin.ui.home.adapter.CategoryAdapter
import com.cse.retrofitkotlin.ui.home.model.ResponseCategory
import com.cse.retrofitkotlin.ui.home.model.ResponseCategoryItem
import com.cse.retrofitkotlin.ui.home.viewmodels.HomeViewModel
import com.cse.retrofitkotlin.utils.KEY_ACCESS
import com.cse.retrofitkotlin.utils.KEY_REFRESH
import com.google.gson.Gson
import com.mehedi.manualdiu.utils.NetworkUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val viewModel: HomeViewModel by viewModels()
    lateinit var adapter: CategoryAdapter
    override fun responseObserver() {
        viewModel.categoryResponse.observe(viewLifecycleOwner) {
            when (it) {

                is DataState.Success -> {

                    // get data
                    val body = it.value.body()?.string()

                    if (body.isNullOrBlank()) {

                        var eerr = it.value.errorBody()?.string()
                        Log.e("TAG", eerr.toString())
                    } else {
                        Log.e("TAG", body.toString())
                        if (NetworkUtils.isValidResponse(it)) {


                            val mcArray: Array<ResponseCategoryItem> =
                                Gson().fromJson(body, Array<ResponseCategoryItem>::class.java)

                            mcArray.forEach {
                                Log.e("TAG", "${it.toString()}")
                            }

                            setData(mcArray.toList())



                        } else {
                            //howToast(getString(R.string.something_went_wrong))
                        }
                    }
                }

                is DataState.Loading -> {
                    Log.d("TAG", "Loading....: ")
                }

                is DataState.Error -> {
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



    private fun setData(list : List<ResponseCategoryItem>) {

        list.forEach {
            Log.e("TAG_list", "${it.toString()}")
        }

        val manager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


        binding.ctgRCV.apply {
            layoutManager = manager
            adapter = CategoryAdapter(list)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCategoryResponse()
    }


}