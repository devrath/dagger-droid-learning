package com.droid.code.components;

import com.droid.code.structures.Car;

import dagger.Component;

@Component
public interface CarComponent {
    Car getCarInstance();
}
