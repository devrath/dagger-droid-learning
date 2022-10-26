package com.droid.code.structures.engine;

import static com.droid.code.Demo.PROJECT_TAG;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Named;

public class PetrolEngine implements Engine {

    private int horsePower;
    private int engineCapacity;

    @Inject
    public PetrolEngine(@Named("horse power") int horsePower,
                        @Named("engine capacity") int engineCapacity){
        Log.d(PROJECT_TAG, "Petrol engine initialised");
        this.horsePower = horsePower;
        this.engineCapacity = engineCapacity;
    }

    @Override
    public void start() {
        Log.d(PROJECT_TAG, "Starting petrol engine");
    }

}
