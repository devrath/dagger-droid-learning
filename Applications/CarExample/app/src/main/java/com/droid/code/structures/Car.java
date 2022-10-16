package com.droid.code.structures;

import static com.droid.code.MainActivityKt.PROJECT_TAG;

import android.util.Log;

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
