package com.droid.code.components;

import com.droid.code.Demo;
import com.droid.code.modules.DieselEngineModule;
import com.droid.code.modules.PetrolEngineModule;
import com.droid.code.modules.WheelsModule;
import com.droid.code.structures.Car;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Component;

@Component(modules = {PetrolEngineModule.class, WheelsModule.class})
public interface CarComponent {
    // For:-> Constructor Injection
    Car getCarInstance();
    // For:-> Variable Injection
    void inject(Demo instance);

    // When we define component builder, We take responsibility of building the component
    @Component.Builder
    interface Builder {
        CarComponent build();
    }

}
