package com.github.welshk.ukko.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.welshk.ukko.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main activity.
 * Doesn't do much other than hold fragments
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}