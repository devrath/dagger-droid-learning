package com.droid.code.modules;


import com.droid.code.structures.engine.DieselEngine;
import com.droid.code.structures.engine.Engine;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public class DieselEngineModule {

    private int capacity;

    public DieselEngineModule(int capacity) {
        this.capacity = capacity;
    }

    @Provides
    int providesCapacity() {
        return capacity;
    }

    @Provides
    public Engine providesDieselEngine(){
        return new DieselEngine(capacity);
    }

}
