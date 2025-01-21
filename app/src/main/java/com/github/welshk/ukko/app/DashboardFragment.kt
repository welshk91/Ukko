package com.github.welshk.ukko.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.github.welshk.ukko.R
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Fragment for the Dashboard, our opening screen.
 * We no longer start this fragment as it's currently just a button to go to Details anyways
 */
class DashboardFragment : Fragment() {
    private val viewModel: DashboardViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigateToDetails.observe(this) {
            findNavController().navigate(R.id.action_dashboard_to_details)
        }
    }
}