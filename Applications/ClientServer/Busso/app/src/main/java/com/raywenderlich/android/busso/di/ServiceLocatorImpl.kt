package com.raywenderlich.android.busso.di

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationManager
import com.raywenderlich.android.busso.permission.GeoLocationPermissionCheckerImpl
import com.raywenderlich.android.location.rx.provideRxLocationObservable

// WE USE THE BELOW CONSTANTS FOR THE LOOK UP OF SERVICES
const val LOCATION_OBSERVABLE = "LocationObservable"
const val ACTIVITY_LOCATOR_FACTORY = "ActivityLocatorFactory"


class ServiceLocatorImpl(
    val context: Context
) : ServiceLocator {
    private val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    private val geoLocationPermissionChecker = GeoLocationPermissionCheckerImpl(context)
    private val locationObservable = provideRxLocationObservable(locationManager, geoLocationPermissionChecker)

    @Suppress("IMPLICIT_CAST_TO_ANY", "UNCHECKED_CAST")
    override fun <A : Any> lookUp(name: String): A = when (name) {
        LOCATION_OBSERVABLE -> locationObservable
        ACTIVITY_LOCATOR_FACTORY -> activityServiceLocatorFactory(this) // HERE
        else -> throw IllegalArgumentException("No component lookup for the key: $name")
    } as A
}