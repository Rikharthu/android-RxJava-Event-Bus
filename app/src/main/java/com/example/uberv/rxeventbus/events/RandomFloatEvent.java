package com.example.uberv.rxeventbus.events;

import java.util.Random;

public class RandomFloatEvent implements IMyEvent {
    private float mValue;

    public RandomFloatEvent() {
        mValue = new Random().nextFloat();
    }

    public RandomFloatEvent(float mValue) {
        this.mValue = mValue;
    }

    public void setValue(float mValue) {
        this.mValue = mValue;
    }

    public float getValue() {
        return mValue;
    }
}
