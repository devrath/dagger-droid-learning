package com.raywenderlich.android.busso.ui.view.busstop

/**
 * The Item with the data to display for a single BusStop
 */
data class BusStopViewModel(
    val stopId: String,
    val stopName: String,
    val stopDirection: String,
    val stopIndicator: String,
    val stopDistance: String
)