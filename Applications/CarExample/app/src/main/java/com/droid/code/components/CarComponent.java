package com.droid.code.components;

import com.droid.code.Demo;
import com.droid.code.structures.Car;

import dagger.Component;

@Component
public interface CarComponent {
    // For:-> Constructor Injection
    Car getCarInstance();
    // For:-> Variable Injection
    void inject(Demo instance);
}
