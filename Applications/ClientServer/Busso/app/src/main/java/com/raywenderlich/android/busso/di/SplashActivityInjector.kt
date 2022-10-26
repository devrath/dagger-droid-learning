package com.raywenderlich.android.busso.di

import androidx.appcompat.app.AppCompatActivity
import com.raywenderlich.android.busso.SplashActivity
import com.raywenderlich.android.busso.lookUp

// Define SplashActivityInjector as an object using the syntax Kotlin provides for creating instances of implementation for interfaces with one abstract method (SAM)
object SplashActivityInjector : Injector<SplashActivity> {
    override fun inject(target: SplashActivity) {
        // Use the target (SplashActivity) to get the reference to ActivityServiceLocator.
        val activityServiceLocator =
            target.lookUp<ServiceLocatorFactory<AppCompatActivity>>(ACTIVITY_LOCATOR_FACTORY)
                .invoke(target)
        // Invoke lookUp() on activityServiceLocator and assign the return value to the target’s locationObservable.
        target.locationObservable = activityServiceLocator.lookUp(LOCATION_OBSERVABLE) // ERROR
        // Do the same for navigator.
        target.navigator = activityServiceLocator.lookUp(NAVIGATOR) // ERROR
    }
}