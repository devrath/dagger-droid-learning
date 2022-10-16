package com.droid.code.structures;

import static com.droid.code.MainActivityKt.PROJECT_TAG;

import android.util.Log;

import javax.inject.Inject;

public class Engine {

    @Inject
    private Engine() {
        Log.d(PROJECT_TAG, "Engine constructor is invoked");
    }

}
