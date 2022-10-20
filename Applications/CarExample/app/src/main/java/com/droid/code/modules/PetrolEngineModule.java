package com.droid.code.modules;

import static com.droid.code.Demo.PROJECT_TAG;

import android.util.Log;

import com.droid.code.structures.engine.PetrolEngine;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@Module
public class PetrolEngineModule {

    @Provides
    public PetrolEngine providesPetrolEngine(){
        return new PetrolEngine();
    }

}
