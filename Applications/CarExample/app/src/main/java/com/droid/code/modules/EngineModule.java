package com.droid.code.modules;

import com.droid.code.structures.Engine;

import dagger.Module;
import dagger.Provides;

@Module
public class EngineModule {

    @Provides
    public Engine provideEngine(){
        return new Engine();
    }

}
