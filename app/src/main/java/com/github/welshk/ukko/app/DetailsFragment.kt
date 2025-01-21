package com.github.welshk.ukko.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.github.welshk.ukko.R
import com.github.welshk.ukko.utils.FormatUtil
import com.github.welshk.ukko.utils.HeroImageUtil
import com.github.welshk.ukko.utils.loadHeroImage
import com.github.welshk.ukko.utils.loadIconImage
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Fragment for Details screen
 * Just observe any changes to LiveData in ViewModel so we can properly hide progressbar
 */
class DetailsFragment : Fragment() {
    private lateinit var root: ConstraintLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var heroImage: AppCompatImageView
    private lateinit var city: TextView
    private lateinit var country: TextView
    private lateinit var time: TextView
    private lateinit var description: TextView
    private lateinit var icon: AppCompatImageView
    private lateinit var tempLow: TextView
    private lateinit var tempHigh: TextView
    private lateinit var temp: TextView
    private lateinit var author: TextView
    private lateinit var site: TextView

    private val viewModelActivity: MainViewModel by activityViewModel()
    private val viewModelFragment: DetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        root = inflater.inflate(R.layout.fragment_details, container, false) as ConstraintLayout
        progressBar = root.findViewById(R.id.progressbar)
        heroImage = root.findViewById(R.id.hero_image)
        city = root.findViewById(R.id.city)
        country = root.findViewById(R.id.country)
        time = root.findViewById(R.id.time)
        description = root.findViewById(R.id.weather_description)
        icon = root.findViewById(R.id.weather_icon)
        tempLow = root.findViewById(R.id.tempLow)
        tempHigh = root.findViewById(R.id.tempHigh)
        temp = root.findViewById(R.id.temp)
        author = root.findViewById(R.id.author)
        site = root.findViewById(R.id.site)
        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelFragment.weatherDetails.observe(this) { details ->
            context?.let {
                progressBar.visibility = View.GONE
                heroImage.loadHeroImage(details)
                city.text = FormatUtil.formatCity(details)
                country.text = FormatUtil.formatCountry(details)
                time.text = FormatUtil.formatTime(details)
                description.text = FormatUtil.formatDescription(details)
                icon.loadIconImage(details)
                tempLow.text = FormatUtil.formatTempLow(requireContext(), details)
                tempHigh.text = FormatUtil.formatTempHigh(requireContext(), details)
                temp.text = FormatUtil.formatTemp(requireContext(), details)
                author.text = HeroImageUtil.getHeroImage(details).author
                site.text = HeroImageUtil.getHeroImage(details).site
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