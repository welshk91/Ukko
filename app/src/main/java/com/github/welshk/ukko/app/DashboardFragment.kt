package com.github.welshk.ukko.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.databinding.DataBindingUtil
import com.github.welshk.ukko.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint
import com.github.welshk.ukko.R

/**
 * Fragment for the Dashboard, our opening screen
 */
@AndroidEntryPoint
class DashboardFragment: Fragment() {
    private lateinit var binding: FragmentDashboardBinding
    private val viewModel: DashboardViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigateToDetails.observe(this) {
            findNavController().navigate(R.id.action_dashboard_to_details)
        }
    }
}