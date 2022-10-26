package com.raywenderlich.android.busso.ui.view.busstop

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.raywenderlich.android.busso.R
import com.raywenderlich.android.busso.ui.events.OnItemSelectedListener

/** BusStopListViewBinder implementation for the BusStopFragment */
class BusStopListViewBinderImpl(
  private val busStopItemSelectedListener: BusStopListViewBinder.BusStopItemSelectedListener? = null
) : BusStopListViewBinder {

  private lateinit var busStopRecyclerView: RecyclerView
  private lateinit var busStopAdapter: BusStopListAdapter

  override fun init(rootView: View) {
    busStopRecyclerView = rootView.findViewById(R.id.busstop_recyclerview)
    busStopAdapter = BusStopListAdapter(object : OnItemSelectedListener<BusStopViewModel> {
      override fun invoke(position: Int, selectedItem: BusStopViewModel) {
        busStopItemSelectedListener?.onBusStopSelected(selectedItem)
      }
    })
    initRecyclerView(busStopRecyclerView)
  }

  private fun initRecyclerView(busStopRecyclerView: RecyclerView) {
    busStopRecyclerView.apply {
      val viewManager = LinearLayoutManager(busStopRecyclerView.context)
      layoutManager = viewManager
      adapter = busStopAdapter
    }
  }

  override fun displayBusStopList(busStopList: List<BusStopViewModel>) {
    busStopAdapter.submitList(busStopList)
  }

  override fun displayErrorMessage(msg: String) {
    Snackbar.make(
      busStopRecyclerView,
      msg,
      Snackbar.LENGTH_LONG
    ).setAction(R.string.message_retry) {
      busStopItemSelectedListener?.retry()
    }.show()
  }
}