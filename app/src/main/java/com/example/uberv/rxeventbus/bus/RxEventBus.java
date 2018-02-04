package com.example.uberv.rxeventbus.bus;

import com.example.uberv.rxeventbus.events.IMyEvent;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class RxEventBus {

    private PublishSubject<IMyEvent> mEventPublisher;

    private static RxEventBus sInstance;

    private RxEventBus() {
        mEventPublisher = PublishSubject.create();
    }

    public static RxEventBus events() {
        if (sInstance == null) {
            sInstance = new RxEventBus();
        }
        return sInstance;
    }

    public <T extends IMyEvent> Observable<T> of(Class<T> eventClass) {
        return mEventPublisher
                .filter(object -> object != null)
                .filter(eventClass::isInstance)
                .cast(eventClass);
    }

    public Observable<IMyEvent> all() {
        return mEventPublisher
                .filter(object -> object != null);
    }

    public void post(IMyEvent event) {
        if (sInstance.mEventPublisher.hasObservers()) {
            sInstance.mEventPublisher.onNext(event);
        }
    }
}
