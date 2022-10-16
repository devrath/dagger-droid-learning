package com.droid.code;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.droid.code.components.CarComponent;
import com.droid.code.components.DaggerCarComponent;
import com.droid.code.databinding.ActivityMainBinding;
import com.droid.code.structures.Car;

import javax.inject.Inject;

public class Demo extends AppCompatActivity {

    public static final String PROJECT_TAG = "Vehicle_Demo";
    private ActivityMainBinding binding;

    // @Inject Car car;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView( binding.getRoot());

        // Field injection
        // DaggerCarComponent.builder().build().inject(this);

        binding.btnInitiateId.setOnClickListener(v -> {
            CarComponent component = DaggerCarComponent.create();
            component.getCarInstance().drive();
        });
    }

}
