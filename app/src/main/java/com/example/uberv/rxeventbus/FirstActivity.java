package com.example.uberv.rxeventbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        findViewById(R.id.secondActivityBtn).setOnClickListener(v -> {
            startActivity(new Intent(this, SecondActivity.class));
        });
    }
}
