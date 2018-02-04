package com.example.uberv.rxeventbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.uberv.rxeventbus.bus.RxEventBus;
import com.example.uberv.rxeventbus.events.IMyEvent;
import com.example.uberv.rxeventbus.events.RandomFloatEvent;
import com.example.uberv.rxeventbus.events.RandomIntegerEvent;

import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RxEventBus bus = RxEventBus.events();

        Consumer<RandomIntegerEvent> randomIntEventListener =
                randomIntegerEvent -> Log.d("RandomIntegerEvent", "" + randomIntegerEvent.getValue());

        bus.of(RandomIntegerEvent.class)
                .subscribe(randomIntEventListener);
        bus.of(RandomFloatEvent.class)
                .subscribe(onRandomFloatEvent());
        bus.of(IMyEvent.class)
                .subscribe(iMyEvent -> Log.d("IMyEvent", "Generic Event"));
        bus.all()
                .subscribeOn(Schedulers.io())
                .subscribe(event -> {
                    if (event instanceof RandomFloatEvent) {
                        Log.d("IMyEvent", "Received RandomFloatEvent: " + ((RandomFloatEvent) event).getValue());
                    } else if (event instanceof RandomIntegerEvent) {
                        Log.d("IMyEvent", "Received RandomIntegerEvent: " + ((RandomIntegerEvent) event).getValue());
                    }
                });
    }

    private Consumer<RandomFloatEvent> onRandomFloatEvent() {
        return event -> Log.d("RandomFloatEvent", "" + event.getValue());
    }

    public void onIMyEventClicked(View view) {
        RxEventBus.events().post(new IMyEvent() {
        });
    }

    public void onFloatEventClicked(View view) {
        RxEventBus.events().post(new RandomFloatEvent());
    }

    public void onIntegerEventClicked(View view) {
        RxEventBus.events().post(new RandomIntegerEvent());
    }
}
