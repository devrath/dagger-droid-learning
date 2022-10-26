package com.raywenderlich.android.busso.ui.view.busstop

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.raywenderlich.android.busso.R
import com.raywenderlich.android.busso.di.injectors.BusStopFragmentInjector

/**
 * The Fragment which displays the list of BusStop close to the
 */
class BusStopFragment : Fragment() {

  lateinit var busStopListViewBinder: BusStopListViewBinder
  lateinit var busStopListPresenter: BusStopListPresenter

  override fun onAttach(context: Context) {
    // Initialize the service locator
    BusStopFragmentInjector.inject(this)
    super.onAttach(context)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? = inflater.inflate(R.layout.fragment_busstop_layout, container, false).apply {
    busStopListViewBinder.init(this)
  }


  override fun onStart() {
    super.onStart()
    with(busStopListPresenter) {
      bind(busStopListViewBinder)
      start()
    }
  }

  override fun onStop() {
    with(busStopListPresenter) {
      stop()
      unbind()
    }
    super.onStop()
  }
}