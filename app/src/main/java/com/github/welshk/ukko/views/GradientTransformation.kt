package com.github.welshk.ukko.views

import android.content.Context
import android.graphics.*
import androidx.core.content.ContextCompat
import com.github.welshk.ukko.R
import com.squareup.picasso.Transformation


/**
 * Provides a darken gradient the further to the end of the image it goes
 */
class GradientTransformation(private val context: Context, private var gradientHeight: Int) :
    Transformation {

    override fun transform(source: Bitmap): Bitmap? {
        val sourceWidth = source.width
        val sourceHeight = source.height

        if (gradientHeight > sourceHeight) {
            gradientHeight = sourceHeight
        }

        val overlay = Bitmap.createBitmap(sourceWidth, sourceHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(overlay)
        canvas.drawBitmap(source, 0f, 0f, null)
        val paint = Paint()
        val colors = intArrayOf(
            ContextCompat.getColor(context, R.color.gradient_start),
            ContextCompat.getColor(context, R.color.gradient_start_middle),
            ContextCompat.getColor(context, R.color.gradient_middle),
            ContextCompat.getColor(context, R.color.gradient_middle_end),
            ContextCompat.getColor(context, R.color.gradient_end)
        )
        val shader = LinearGradient(
            0f,
            (sourceHeight - gradientHeight).toFloat(),
            0f,
            sourceHeight.toFloat(),
            colors,
            null,
            Shader.TileMode.CLAMP
        )
        paint.shader = shader
        //paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawRect(
            0f,
            (sourceHeight - gradientHeight).toFloat(),
            sourceWidth.toFloat(),
            sourceHeight.toFloat(),
            paint
        )
        source.recycle()
        return overlay
    }

    override fun key(): String {
        return "linearGradient"
    }
}