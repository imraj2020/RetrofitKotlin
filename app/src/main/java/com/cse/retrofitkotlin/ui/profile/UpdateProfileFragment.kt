package com.cse.retrofitkotlin.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cse.retrofitkotlin.R
import com.cse.retrofitkotlin.base.BaseFragment
import com.cse.retrofitkotlin.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateProfileFragment :
    BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {
    override fun responseObserver() {
        TODO("Not yet implemented")
    }
}