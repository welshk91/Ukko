package com.github.welshk.ukko.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.welshk.ukko.R
import com.github.welshk.ukko.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment for Details screen
 * Just observe any changes to LiveData in ViewModel so we can properly hide progressbar
 */
@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.weatherDetails.observe(this) { details ->
            context?.let {
                binding.progressbar.visibility = View.GONE
            }
        }
    }

}