package com.github.welshk.ukko.utils

import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity
import java.util.Locale

/**
 * https://stackoverflow.com/questions/52042903/capitalise-every-word-in-string-with-extension-function
 * https://stackoverflow.com/a/63312382/2128921
 */
val String.capitalizeWords
    get() = this.lowercase(Locale.ROOT).split(" ").joinToString(" ") {
        it.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
    }

/**
 * Extension that attempts to get activity from context; Google wants it for billing.
 * Source:
 * https://stackoverflow.com/questions/76127206/using-google-new-billing-client-with-jetpack-compose-and-inject-activity
 */
fun Context.findActivity(): ComponentActivity {
    var context = this
    while (context is ContextWrapper) {
        if (context is ComponentActivity) return context
        context = context.baseContext
    }
    throw IllegalStateException("Couldn't find an appropriate Activity from the given context")
}
