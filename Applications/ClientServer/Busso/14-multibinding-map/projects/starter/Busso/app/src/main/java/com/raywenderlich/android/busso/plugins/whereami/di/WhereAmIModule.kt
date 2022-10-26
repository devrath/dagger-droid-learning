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

package com.raywenderlich.android.busso.plugins.whereami.di

import com.raywenderlich.android.busso.di.scopes.ApplicationScope
import com.raywenderlich.android.busso.plugins.api.InformationEndpoint
import com.raywenderlich.android.busso.plugins.api.InformationPluginSpec
import com.raywenderlich.android.busso.plugins.whereami.endpoint.MyLocationEndpoint
import com.raywenderlich.android.busso.plugins.whereami.endpoint.WhereAmIEndpoint
import com.raywenderlich.android.busso.plugins.whereami.endpoint.WhereAmIEndpointImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

const val WHEREAMI_INFO_NAME = "WhereAmI"

@Module(includes = [WhereAmIModule.Bindings::class])
object WhereAmIModule {

  @Provides
  @ApplicationScope
  fun provideMyLocationEndpoint(retrofit: Retrofit): MyLocationEndpoint {
    return retrofit.create(MyLocationEndpoint::class.java)
  }

  @Provides
  @ApplicationScope
  @Named(WHEREAMI_INFO_NAME) // 3
  fun provideWhereAmISpec(endpoint: WhereAmIEndpointImpl): InformationPluginSpec =
      object : InformationPluginSpec {
        override val informationEndpoint: InformationEndpoint
          get() = endpoint
        override val serviceName: String
          get() = WHEREAMI_INFO_NAME
      }

  @Module
  interface Bindings {

    @Binds
    fun bindWhereAmIEndpoint(
        impl: WhereAmIEndpointImpl
    ): WhereAmIEndpoint
  }
}