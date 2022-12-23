package com.github.welshk.ukko.utils

import android.net.Uri
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.github.welshk.ukko.data.models.WeatherDetails
import com.squareup.picasso.Picasso
import com.github.welshk.ukko.R
import com.squareup.picasso.Callback

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
fun loadImage(view: ImageView, weatherDetails: WeatherDetails?) {
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