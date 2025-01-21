package com.github.welshk.ukko.utils

import android.widget.ImageView
import com.github.welshk.ukko.data.models.WeatherDetails
import com.github.welshk.ukko.views.GradientTransformation
import com.github.welshk.ukko.views.RadialGradientTransformation
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
fun ImageView.loadIconImage(weatherDetails: WeatherDetails?) {
    val iconUrl = FormatUtil.formatIconUrl(weatherDetails)
    if (iconUrl != null) {
        Picasso.get()
            .load(iconUrl)
//            .placeholder(R.drawable.ic_launcher_foreground)
//            .error(R.drawable.ic_launcher_background)
            .priority(Picasso.Priority.LOW)
            .into(this)

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

fun ImageView.loadHeroImage(weatherDetails: WeatherDetails?) {
    val gradientHeight = 1024
    if (weatherDetails != null) {
        Picasso.get()
            .load(HeroImageUtil.getHeroImage(weatherDetails).imageDrawable)
            .fit()
            .centerCrop()
            .transform(GradientTransformation(this.context, gradientHeight))
            .transform(RadialGradientTransformation(this.context))
            .priority(Picasso.Priority.HIGH)
            .into(this)
    }
}