package com.droid.code.modules;

import static com.droid.code.Demo.PROJECT_TAG;

import android.util.Log;

import com.droid.code.structures.engine.Engine;
import com.droid.code.structures.engine.PetrolEngine;

import javax.inject.Inject;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class PetrolEngineModule {

    @Binds
    abstract Engine bindEngine(PetrolEngine engine);

}
