package com.example.uberv.rxeventbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.uberv.rxeventbus.bus.MyEventBus;
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

        MyEventBus bus = MyEventBus.instanceOf();

        Consumer<RandomIntegerEvent> randomIntEventListener =
                randomIntegerEvent -> Log.d("RandomIntegerEvent", "" + randomIntegerEvent.getValue());

        bus.getObservable(RandomIntegerEvent.class)
                .subscribe(randomIntEventListener);
        bus.getObservable(RandomFloatEvent.class)
                .subscribe(onRandomFloatEvent());
        bus.getObservable(IMyEvent.class)
                .subscribe(new Consumer<IMyEvent>() {
                    @Override
                    public void accept(IMyEvent iMyEvent) throws Exception {
                        Log.d("IMyEvent", "Generic Event");
                    }
                });
        bus.getObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<IMyEvent>() {
                    @Override
                    public void accept(IMyEvent event) throws Exception {
                        if (event instanceof RandomFloatEvent) {
                            Log.d("IMyEvent", "Received RandomFloatEvent: " + ((RandomFloatEvent) event).getValue());
                        } else if (event instanceof RandomIntegerEvent) {
                            Log.d("IMyEvent", "Received RandomIntegerEvent: " + ((RandomIntegerEvent) event).getValue());
                        }
                    }
                });
    }

    private Consumer<RandomFloatEvent> onRandomFloatEvent() {
        return event -> Log.d("RandomFloatEvent", "" + event.getValue());
    }

    public void onIMyEventClicked(View view) {
        MyEventBus.instanceOf().post(new IMyEvent() {
        });
    }

    public void onFloatEventClicked(View view) {
        MyEventBus.instanceOf().post(new RandomFloatEvent());
    }

    public void onIntegerEventClicked(View view) {
        MyEventBus.instanceOf().post(new RandomIntegerEvent());
    }
}
