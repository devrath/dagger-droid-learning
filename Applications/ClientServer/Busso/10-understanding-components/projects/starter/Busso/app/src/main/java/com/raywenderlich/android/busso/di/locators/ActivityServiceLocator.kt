/*
 * Copyright (c) 2020 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.busso.di.locators

import androidx.appcompat.app.AppCompatActivity
import com.raywenderlich.android.busso.ui.view.main.MainPresenter
import com.raywenderlich.android.busso.ui.view.main.MainPresenterImpl
import com.raywenderlich.android.busso.ui.view.splash.SplashPresenter
import com.raywenderlich.android.busso.ui.view.splash.SplashPresenterImpl
import com.raywenderlich.android.busso.ui.view.splash.SplashViewBinder
import com.raywenderlich.android.busso.ui.view.splash.SplashViewBinderImpl
import com.raywenderlich.android.location.api.model.LocationEvent
import com.raywenderlich.android.ui.navigation.Navigator
import com.raywenderlich.android.ui.navigation.NavigatorImpl
import io.reactivex.Observable

const val NAVIGATOR = "Navigator"
const val FRAGMENT_LOCATOR_FACTORY = "FragmentLocatorFactory"
const val MAIN_PRESENTER = "MainPresenter"
const val SPLASH_PRESENTER = "SplashPresenter"
const val SPLASH_VIEWBINDER = "SplashViewBinder"

val activityServiceLocatorFactory: (ServiceLocator) -> ServiceLocatorFactory<AppCompatActivity> =
    { fallbackServiceLocator: ServiceLocator ->
      { activity: AppCompatActivity ->
        ActivityServiceLocator(activity).apply {
          applicationServiceLocator = fallbackServiceLocator
        }
      }
    }

class ActivityServiceLocator(
    val activity: AppCompatActivity
) : ServiceLocator {

  var applicationServiceLocator: ServiceLocator? = null
  var mainPresenter: MainPresenter? = null
  var splashPresenter: SplashPresenter? = null
  var splashViewBinder: SplashViewBinder? = null

  @Suppress("IMPLICIT_CAST_TO_ANY", "UNCHECKED_CAST")
  override fun <A : Any> lookUp(name: String): A = when (name) {
    NAVIGATOR -> NavigatorImpl(activity)
    FRAGMENT_LOCATOR_FACTORY -> fragmentServiceLocatorFactory(this)
    SPLASH_VIEWBINDER -> {
      if (splashViewBinder == null) {
        val navigator: Navigator = lookUp(NAVIGATOR)
        splashViewBinder = SplashViewBinderImpl(
            navigator
        )
      }
      splashViewBinder
    }
    SPLASH_PRESENTER -> {
      if (splashPresenter == null) {
        val locationObservable: Observable<LocationEvent> = applicationServiceLocator!!.lookUp(LOCATION_OBSERVABLE)
        splashPresenter = SplashPresenterImpl(
            locationObservable
        )
      }
      splashPresenter
    }
    MAIN_PRESENTER -> {
      if (mainPresenter == null) {
        val navigator: Navigator = lookUp(NAVIGATOR)
        mainPresenter = MainPresenterImpl(navigator)
      }
      mainPresenter
    }
    else -> applicationServiceLocator?.lookUp<A>(name)
        ?: throw IllegalArgumentException("No component lookup for the key: $name")
  } as A
}