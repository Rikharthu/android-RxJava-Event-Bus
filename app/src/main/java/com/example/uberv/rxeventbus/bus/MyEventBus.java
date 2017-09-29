package com.example.uberv.rxeventbus.bus;

import com.example.uberv.rxeventbus.events.IMyEvent;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class MyEventBus {

    private PublishSubject<IMyEvent> mEventPublisher;

    private static MyEventBus sInstance;

    public MyEventBus() {
        mEventPublisher = PublishSubject.create();
    }

    public static MyEventBus instanceOf() {
        if (sInstance == null) {
            sInstance = new MyEventBus();
        }
        return sInstance;
    }

    public <T extends IMyEvent> Observable<T> getObservable(Class<T> eventClass) {
        return mEventPublisher
                .filter(object -> object != null)
                .filter(eventClass::isInstance) // object -> eventClass.isInstance(object)
                .cast(eventClass);
    }

    public Observable<IMyEvent> getObservable() {
        return mEventPublisher
                .filter(object -> object != null);
    }

    public void post(IMyEvent event) {
        if (sInstance.mEventPublisher.hasObservers()) {
            sInstance.mEventPublisher.onNext(event);
        }
    }
}
