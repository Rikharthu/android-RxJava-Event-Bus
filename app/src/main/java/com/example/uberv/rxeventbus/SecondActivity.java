package com.example.uberv.rxeventbus;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.uberv.rxeventbus.bus.RxEventBus;
import com.example.uberv.rxeventbus.events.RandomIntegerEvent;

import io.reactivex.disposables.CompositeDisposable;

public class SecondActivity extends AppCompatActivity {

    private CompositeDisposable eventSubscribtions = new CompositeDisposable();
    private TextView mTextView;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mTextView = (TextView) findViewById(R.id.textView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // To avoid memory leaks you should always subscribe and unsubscribe from events!
        eventSubscribtions.add(RxEventBus.events().of(RandomIntegerEvent.class)
                .subscribe(randomIntegerEvent -> {
                    Log.d("SecondActivity", "Event: " + randomIntegerEvent.getValue());
                    runOnUiThread(() -> mTextView.setText(randomIntegerEvent.getValue() + ""));
                }));
    }

    @Override
    protected void onPause() {
        super.onPause();
        eventSubscribtions.dispose();
    }
}
