package com.github.welshk.ukko.utils

import java.util.*

/**
 * https://stackoverflow.com/questions/52042903/capitalise-every-word-in-string-with-extension-function
 * https://stackoverflow.com/a/63312382/2128921
 */
val String.capitalizeWords
    get() = this.lowercase(Locale.ROOT).split(" ").joinToString(" ") {
        it.capitalize(Locale.ROOT)
    }