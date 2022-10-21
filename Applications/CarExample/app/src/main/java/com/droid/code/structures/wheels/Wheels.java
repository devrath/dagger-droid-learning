package com.droid.code.structures.wheels;


import static com.droid.code.Demo.PROJECT_TAG;

import android.util.Log;

import javax.inject.Inject;

public class Wheels {

    private Rims rims;
    private Tyres tyres;

    public Wheels(Rims rims, Tyres tyres) {
        this.rims = rims;
        this.tyres = tyres;
        Log.d(PROJECT_TAG, "Wheels constructor is invoked");
    }

}
