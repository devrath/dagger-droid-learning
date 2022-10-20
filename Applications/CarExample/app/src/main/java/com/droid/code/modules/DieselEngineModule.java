package com.droid.code.modules;


import com.droid.code.structures.engine.DieselEngine;
import com.droid.code.structures.engine.Engine;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class DieselEngineModule {

    @Binds
    public abstract Engine providesDieselEngine(DieselEngine engine);

}
