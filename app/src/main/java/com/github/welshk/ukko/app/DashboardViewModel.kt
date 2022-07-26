package com.github.welshk.ukko.app

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.github.welshk.ukko.utils.SingleLiveEvent

/**
 * ViewModel for Dashboard, our first screen
 * Not much on this screen other than handling moving to details screen
 */
class DashboardViewModel: ViewModel() {
    private val _navigateToDetails = SingleLiveEvent<Any>()
    val navigateToDetails: LiveData<Any>
        get() = _navigateToDetails

    // Event handlers
    fun onDetailsPressed(view: View) {
        _navigateToDetails.call()
    }
}