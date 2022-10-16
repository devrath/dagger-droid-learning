package com.raywenderlich.android.busso

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import com.raywenderlich.android.busso.di.ServiceLocator
import com.raywenderlich.android.busso.di.ServiceLocatorImpl

class Main : Application() {
    // Define a lateinit var for the reference to a ServiceLocator implementation
    lateinit var serviceLocator: ServiceLocator

    override fun onCreate() {
        super.onCreate()
        // Create an instance of ServiceLocatorImpl and assign it to the serviceLocator property.
        serviceLocator = ServiceLocatorImpl(this)
    }
}

// Define the lookUp() extension function for AppCompatActivity, which allows you to easily look up components from any class that IS-A AppCompatActivity, like SplashActivity.
internal fun <A: Any> AppCompatActivity.lookUp(name: String): A =
    (applicationContext as Main).serviceLocator.lookUp(name)