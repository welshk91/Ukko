package com.github.welshk.ukko.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.github.welshk.ukko.R
import com.github.welshk.ukko.databinding.FragmentDashboardBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Fragment for the Dashboard, our opening screen.
 * We no longer start this fragment as it's currently just a button to go to Details anyways
 */
class DashboardFragment : Fragment() {
    private lateinit var binding: FragmentDashboardBinding
    private val viewModel: DashboardViewModel by viewModel()

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