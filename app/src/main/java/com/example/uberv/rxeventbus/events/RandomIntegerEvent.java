package com.example.uberv.rxeventbus.events;

import java.util.Random;

public class RandomIntegerEvent implements IMyEvent {

    private int mValue;

    public RandomIntegerEvent() {
        mValue = new Random().nextInt(100);
    }

    public RandomIntegerEvent(int mValue) {
        this.mValue = mValue;
    }

    public void setValue(int mValue) {
        this.mValue = mValue;
    }

    public int getValue() {
        return mValue;
    }
}
