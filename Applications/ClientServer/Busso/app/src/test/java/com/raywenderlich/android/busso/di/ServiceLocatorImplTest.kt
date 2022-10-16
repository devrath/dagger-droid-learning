package com.raywenderlich.android.busso.di

import androidx.test.core.app.ApplicationProvider
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class ServiceLocatorImplTest {
    // 1
    @Rule
    @JvmField
    var thrown: ExpectedException = ExpectedException.none()

    // 2
    lateinit var serviceLocator: ServiceLocatorImpl

    @Before
    fun setUp() {
        // 3
        serviceLocator = ServiceLocatorImpl(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun lookUp_whenObjectIsMissing_throwsException() {
        // 4
        thrown.expect(IllegalArgumentException::class.java)
        // 5
        serviceLocator.lookUp<Any>("MISSING")
    }
}