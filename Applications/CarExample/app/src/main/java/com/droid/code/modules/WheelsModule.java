package com.droid.code.modules;

import com.droid.code.structures.Wheels;

import dagger.Module;
import dagger.Provides;

@Module
public class WheelsModule {

    @Provides
    public Wheels provideWheels() {
        return new Wheels();
    }

}
