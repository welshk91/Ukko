package com.github.welshk.ukko.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.github.welshk.ukko.data.models.WeatherDetails
import com.squareup.picasso.Picasso

/**
 * File that provides DataBinding Adapters for Picasso
 * https://square.github.io/picasso/
 */

/**
 * Should clean this up, but keep getting 504 Gateway error when attempting to get icon.
 * Looks like it's a server issue, though the browser seems fine...
 * https://stackoverflow.com/questions/66518908/load-image-by-picasso-in-recyclerview-by-databinding-kotlin
 */
@BindingAdapter("iconUrl")
fun loadIconImage(view: ImageView, weatherDetails: WeatherDetails?) {
    val iconUrl = FormatUtil.formatIconUrl(weatherDetails)
    if (iconUrl != null) {
        Picasso.get()
            .load(iconUrl)
//            .placeholder(R.drawable.ic_launcher_foreground)
//            .error(R.drawable.ic_launcher_background)
            .into(view)

//        val picasso = Picasso.Builder(view.context).listener(
//            object : Picasso.Listener{
//                override fun onImageLoadFailed(picasso: Picasso?, uri: Uri?, exception: Exception?) {
//                    exception?.printStackTrace()
//                    println("Picasso loading failed : ${exception?.message}")
//                }
//
//            }
//        ).build()
//        picasso.load(iconUrl).into(view)
    }
}

@BindingAdapter("heroImage")
fun loadHeroImage(view: ImageView, weatherDetails: WeatherDetails?) {
    if (weatherDetails != null) {
        Picasso.get()
            .load(HeroImageUtil.getHeroImage(weatherDetails).imageDrawable)
            .into(view)
    }
}