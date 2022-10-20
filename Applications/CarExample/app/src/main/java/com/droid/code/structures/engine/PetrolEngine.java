package com.droid.code.structures.engine;

import static com.droid.code.Demo.PROJECT_TAG;

import android.util.Log;

import javax.inject.Inject;

public class PetrolEngine implements Engine {

    @Inject
    public PetrolEngine(){
        Log.d(PROJECT_TAG, "Diesel engine initialised");
    }

    @Override
    public void start() {
        Log.d(PROJECT_TAG, "Starting diesel engine");
    }

}
