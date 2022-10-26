package com.raywenderlich.android.busso.ui.view.busstop

import android.view.View
import com.raywenderlich.android.ui.mvp.ViewBinder

/** The ViewBinder implementation for the BusStopFragment */
interface BusStopListViewBinder : ViewBinder<View> {

  /**
   * Displays the list of BusStop information
   */
  fun displayBusStopList(busStopList: List<BusStopViewModel>)

  /**
   * Displays an error message
   */
  fun displayErrorMessage(msg: String)

  /**
   * Interface to implement to observe the BusStop selection
   */
  interface BusStopItemSelectedListener {

    /**
     * Invoked when the BusStop is selected
     */
    fun onBusStopSelected(busStopViewModel: BusStopViewModel)

    /**
     * Invoked when the retry option is selected
     */
    fun retry()
  }
}