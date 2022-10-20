package com.droid.code.modules;


import com.droid.code.structures.engine.DieselEngine;
import com.droid.code.structures.engine.Engine;

import dagger.Module;
import dagger.Provides;

@Module
public class DieselEngineModule {

    @Provides
    public Engine providesDieselEngine() {
        return new DieselEngine();
    }

}
