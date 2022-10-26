package com.droid.code.structures.engine;

import static com.droid.code.Demo.PROJECT_TAG;

import android.util.Log;

import javax.inject.Inject;

public class DieselEngine implements Engine{

    private int capacity;

    @Inject
    public DieselEngine(int capacity){
        this.capacity = capacity;
        Log.d(PROJECT_TAG, "Diesel engine initialised");
    }

    @Override
    public void start() {
        Log.d(PROJECT_TAG, "Starting petrol engine with capacity :->"+capacity);
    }

}
