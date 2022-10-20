package com.droid.code.structures;

import android.util.Log;

import static com.droid.code.Demo.PROJECT_TAG;

import com.droid.code.structures.engine.Engine;

import javax.inject.Inject;

public class Car {

    private Engine engine;
    private Wheels wheels;

    @Inject
    public Car(Engine engine, Wheels wheels) {
        Log.d(PROJECT_TAG, "Car constructor is invoked");
        this.engine = engine;
        this.wheels = wheels;
    }

    public void drive() {
        if(engine!=null && wheels!=null){
            Log.d(PROJECT_TAG, "Car with Engine:->"+engine+"-----"+"having the wheels"+wheels+"are running");
        }
    }


}
