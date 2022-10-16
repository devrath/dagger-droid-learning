package com.droid.code.structures;

import static com.droid.code.MainActivityKt.PROJECT_TAG;

import android.util.Log;

import javax.inject.Inject;

public class Wheels {

    @Inject
    public Wheels() {
        Log.d(PROJECT_TAG, "Wheels constructor is invoked");
    }

}
