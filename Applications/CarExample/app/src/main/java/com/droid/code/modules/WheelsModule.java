package com.droid.code.modules;

import com.droid.code.structures.wheels.Rims;
import com.droid.code.structures.wheels.Tyres;
import com.droid.code.structures.wheels.Wheels;

import dagger.Module;
import dagger.Provides;

@Module
public class WheelsModule {

    @Provides
    public Rims provideRims() {
        return new Rims();
    }

    @Provides
    public Tyres provideTyres() {
        return new Tyres();
    }

    @Provides
    public Wheels provideWheels(Rims rims, Tyres tyres) {
        return new Wheels(rims,tyres);
    }

}
