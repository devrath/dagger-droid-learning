package com.raywenderlich.android.busso.di

import android.content.Context
import android.location.LocationManager
import com.raywenderlich.android.busso.network.provideBussoEndPoint
import com.raywenderlich.android.busso.permission.GeoLocationPermissionCheckerImpl
import com.raywenderlich.android.location.rx.provideRxLocationObservable

// WE USE THE BELOW CONSTANTS FOR THE LOOK UP OF SERVICES
const val BUSSO_ENDPOINT = "BussoEndpoint"
const val LOCATION_OBSERVABLE = "LocationObservable"
const val ACTIVITY_LOCATOR_FACTORY = "ActivityLocatorFactory"


class ServiceLocatorImpl(
    val context: Context
) : ServiceLocator {
    private val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    private val geoLocationPermissionChecker = GeoLocationPermissionCheckerImpl(context)
    private val locationObservable = provideRxLocationObservable(locationManager, geoLocationPermissionChecker)
    private val bussoEndpoint = provideBussoEndPoint(context)

    @Suppress("IMPLICIT_CAST_TO_ANY", "UNCHECKED_CAST")
    override fun <A : Any> lookUp(name: String): A = when (name) {
        // If you have LOCATION_OBSERVABLE, you return Observable<LocationEvent>.
        LOCATION_OBSERVABLE -> locationObservable
        // In the BUSSO_ENDPOINT case, you return BussoEndpoint.
        BUSSO_ENDPOINT -> bussoEndpoint
        ACTIVITY_LOCATOR_FACTORY -> activityServiceLocatorFactory(this) // HERE
        else -> throw IllegalArgumentException("No component lookup for the key: $name")
    } as A
}