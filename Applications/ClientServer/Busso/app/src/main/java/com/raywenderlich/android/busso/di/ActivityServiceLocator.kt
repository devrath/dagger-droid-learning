package com.raywenderlich.android.busso.di

import androidx.appcompat.app.AppCompatActivity
import com.raywenderlich.android.ui.navigation.NavigatorImpl

// Define a new NAVIGATOR constant to use as a key for the Navigator implementation.
const val NAVIGATOR = "Navigator"

// Create activityServiceLocatorFactory as an implementation for ServiceLocatorFactory<AppCompatActivity>
// Receives a ServiceLocator and returns a ServiceLocatorFactory<AppCompatActivity>
val activityServiceLocatorFactory: (ServiceLocator) -> ServiceLocatorFactory<AppCompatActivity> =
    // Is implemented with a lambda whose parameter is the ServiceLocator to use as fallback.
    { fallbackServiceLocator: ServiceLocator ->
        // Contains simple logic that assigns the fallbackServiceLocator to the related property of the ActivityServiceLocator.
        { activity: AppCompatActivity ->
            ActivityServiceLocator(activity).apply {
                applicationServiceLocator = fallbackServiceLocator
            }
        }
    }


// This is another implementation of ServiceLocator
class ActivityServiceLocator(
    // It contains the references to the objects with a dependency on the Activity
    val activity: AppCompatActivity
) : ServiceLocator {

    var applicationServiceLocator: ServiceLocator? = null

    @Suppress("IMPLICIT_CAST_TO_ANY", "UNCHECKED_CAST")
    override fun <A : Any> lookUp(name: String): A = when (name) {
        // Add the case for Navigator for the given constant
        NAVIGATOR -> NavigatorImpl(activity)
        // Use the applicationServiceLocator as a fallback for cases where the requested object is missing.
        else -> applicationServiceLocator?.lookUp<A>(name)
            ?: throw IllegalArgumentException("No component lookup for the key: $name")
    } as A
}
