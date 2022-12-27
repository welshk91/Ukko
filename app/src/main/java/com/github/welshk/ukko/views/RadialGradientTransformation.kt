package com.github.welshk.ukko.views

import android.content.Context
import android.graphics.*
import androidx.core.content.ContextCompat
import com.github.welshk.ukko.R
import com.squareup.picasso.Transformation

/**
 * Provides a darken gradient the further to the top left of the image it goes
 */
class RadialGradientTransformation(private val context: Context) : Transformation {

    override fun transform(source: Bitmap): Bitmap? {
        val sourceWidth = source.width
        val sourceHeight = source.height
        val overlay = Bitmap.createBitmap(sourceWidth, sourceHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(overlay)
        canvas.drawBitmap(source, 0f, 0f, null)
        val paint = Paint()
        val colors = intArrayOf(
            ContextCompat.getColor(context, R.color.radial_gradient_start),
            ContextCompat.getColor(context, R.color.radial_gradient_start_middle),
            ContextCompat.getColor(context, R.color.radial_gradient_middle),
            ContextCompat.getColor(context, R.color.radial_gradient_middle_end),
            ContextCompat.getColor(context, R.color.radial_gradient_end)
        )
        val shader = RadialGradient(
            0f,
            0f,
            sourceWidth.toFloat(),
            colors,
            null,
            Shader.TileMode.CLAMP
        )
        paint.shader = shader
        //paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawRect(0f, 0f, sourceWidth.toFloat(), sourceHeight.toFloat(), paint)
        source.recycle()
        return overlay
    }

    override fun key(): String {
        return "radialGradient"
    }

}