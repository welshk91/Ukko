package com.github.welshk.ukko.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.github.welshk.ukko.R
import com.github.welshk.ukko.databinding.FragmentDetailsBinding
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Fragment for Details screen
 * Just observe any changes to LiveData in ViewModel so we can properly hide progressbar
 */
class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val viewModelActivity: MainViewModel by activityViewModel()
    private val viewModelFragment: DetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        binding.viewModel = viewModelFragment
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelFragment.weatherDetails.observe(this) { details ->
            context?.let {
                binding.progressbar.visibility = View.GONE
            }
        }

        viewModelActivity.userLocation.observe(this) {
            viewModelFragment.fetchWeatherDetails(it)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModelActivity.getLocation()?.let {
            viewModelFragment.fetchWeatherDetails(it)
        }
    }
}