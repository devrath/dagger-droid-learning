package com.raywenderlich.android.busso

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.raywenderlich.android.busso.di.ACTIVITY_LOCATOR_FACTORY
import com.raywenderlich.android.busso.di.LOCATION_OBSERVABLE
import com.raywenderlich.android.busso.di.NAVIGATOR
import com.raywenderlich.android.busso.di.ServiceLocator
import com.raywenderlich.android.busso.di.ServiceLocatorFactory
import com.raywenderlich.android.busso.di.SplashActivityInjector
import com.raywenderlich.android.location.api.model.LocationEvent
import com.raywenderlich.android.location.api.model.LocationPermissionGranted
import com.raywenderlich.android.location.api.model.LocationPermissionRequest
import com.raywenderlich.android.location.api.permissions.GeoLocationPermissionChecker
import com.raywenderlich.android.location.rx.provideRxLocationObservable
import com.raywenderlich.android.ui.navigation.ActivityIntentDestination
import com.raywenderlich.android.ui.navigation.Navigator
import com.raywenderlich.android.ui.navigation.NavigatorImpl
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

/**
 * Splash Screen with the app icon and name at the center, this is also the launch screen and
 * opens up in fullscreen mode. Once launched it waits for 2 seconds after which it opens the
 * MainActivity
 */
class SplashActivity : AppCompatActivity() {

    companion object {
        private const val DELAY_MILLIS = 1000L
        private const val LOCATION_PERMISSION_REQUEST_ID = 1
    }

    private val handler = Handler()
    private val disposables = CompositeDisposable()

    lateinit var locationObservable: Observable<LocationEvent>
    lateinit var navigator: Navigator
    private lateinit var activityServiceLocator: ServiceLocator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makeFullScreen()
        setContentView(R.layout.activity_splash)
        SplashActivityInjector.inject(this)
        activityServiceLocator =
            lookUp<ServiceLocatorFactory<AppCompatActivity>>(ACTIVITY_LOCATOR_FACTORY)
                .invoke(this)
        // Get the reference to Observable<LocationEvent>
        locationObservable = activityServiceLocator.lookUp(LOCATION_OBSERVABLE)
        // Obtain the instance of the Navigator implementation.
        navigator = activityServiceLocator.lookUp(NAVIGATOR)
    }

    override fun onStart() {
        super.onStart()
        disposables.add(
            locationObservable
                .delay(DELAY_MILLIS, TimeUnit.MILLISECONDS)
                .filter(::isPermissionEvent)
                .subscribe(::handlePermissionRequest, ::handleError)
        )
    }

    private fun makeFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        supportActionBar?.hide()
    }

    override fun onStop() {
        disposables.clear()
        super.onStop()
    }

    private fun handleError(error: Throwable) {
        TODO("Handle Errors")
    }

    private fun handlePermissionRequest(permissionRequestEvent: LocationEvent) {
        when (permissionRequestEvent) {
            is LocationPermissionRequest -> requestLocationPermission()
            is LocationPermissionGranted -> goToMain()
            else -> throw IllegalStateException("You should never receive this!")
        }
    }

    private fun goToMain() =
        handler.post {
            navigator.navigateTo(
                ActivityIntentDestination(
                    Intent(this, MainActivity::class.java)
                )
            )
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }

    private fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_ID
            )
            // Show an explanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.
        } else {
            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_ID
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_ID -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // Permission granted! We go on!
                    goToMain()
                } else {
                    // Request denied, we request again
                    requestLocationPermission()
                }
            }
        }
    }

    private fun isPermissionEvent(locationEvent: LocationEvent) =
        locationEvent is LocationPermissionRequest || locationEvent is LocationPermissionGranted
}
