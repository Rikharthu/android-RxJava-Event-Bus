package com.example.uberv.rxeventbus;

import android.app.Application;

import com.example.uberv.rxeventbus.bus.RxEventBus;
import com.example.uberv.rxeventbus.events.RandomIntegerEvent;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;


public class MyApp extends Application {

    private Disposable sub;

    @Override
    public void onCreate() {
        super.onCreate();

        sub = Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(value -> {
                    RxEventBus.events().post(new RandomIntegerEvent());
                });
    }
}
