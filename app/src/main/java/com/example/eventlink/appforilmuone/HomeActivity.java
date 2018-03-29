package com.example.eventlink.appforilmuone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    Button toFibonacci, toDice, toFirebaseAnalytics, toGoogleAnalytics;

    String key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        toFibonacci = findViewById(R.id.toFibonaci);
        toDice = findViewById(R.id.toDice);
        toFirebaseAnalytics = findViewById(R.id.toFireBaseAnalytics);
        toGoogleAnalytics = findViewById(R.id.toGoogleAnalytics);

        toFibonacci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setKey("1");
                String tag = "fibonacci";
                Intent fibActivity = new Intent(HomeActivity.this, FibonacciCaseActivity.class);
                startActivity(fibActivity);
                setFirebaseKey(getKey(), tag);
            }
        });

        toDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setKey("2");
                String tag = "dice";
                Intent diceActivity = new Intent(HomeActivity.this, DiceCaseActivity.class);
                startActivity(diceActivity);
                setFirebaseKey(getKey(), tag);
            }
        });

        toFirebaseAnalytics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setKey("3");
                String tag = "firebaseanalytics";
                Intent diceActivity = new Intent(HomeActivity.this, DiceCaseActivity.class);
                startActivity(diceActivity);
                setFirebaseKey(getKey(), tag);
            }
        });

        toGoogleAnalytics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setKey("4");
                String tag = "googleanalytics";
                Intent diceActivity = new Intent(HomeActivity.this, DiceCaseActivity.class);
                startActivity(diceActivity);
                setFirebaseKey(getKey(), tag);
            }
        });
    }

    public void setFirebaseKey(String key, String name) {
        Bundle bundle = new Bundle();
        bundle.putString("menu_id", key);
        bundle.putString("menu_name", name);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
