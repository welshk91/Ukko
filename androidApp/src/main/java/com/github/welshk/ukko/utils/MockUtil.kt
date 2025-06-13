package com.github.welshk.ukko.utils

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

/**
 * Util class to help with handling mock responses from raw json files
 * Place the mock json files in res/raw/
 */
object MockUtil {
    private const val RES_RAW_PATH = "res/raw/"

    fun getJsonFile(classLoader: ClassLoader, fileName: String): InputStream? {
        return classLoader.getResourceAsStream(RES_RAW_PATH + fileName)
    }

    @Throws(IOException::class)
    fun readJsonFile(classLoader: ClassLoader, filename: String): String {
        val br = BufferedReader(InputStreamReader(getJsonFile(classLoader, filename)))
        val sb = StringBuilder()
        var line: String? = br.readLine()
        while (line != null) {
            sb.append(line)
            line = br.readLine()
        }
        return sb.toString()
    }
}