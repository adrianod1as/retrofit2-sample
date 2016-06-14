package com.example.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.R;
import com.example.extra.SaveSharedPreference;

public class SplashScreenActivity extends AppCompatActivity {

    private boolean mIsBackButtonPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();

                if (!mIsBackButtonPressed) {
                    if (SaveSharedPreference.getUserName(SplashScreenActivity.this).length() == 0) {
                        startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                    } else {
                        startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                    }
                }
            }
        }, 3000);
    }

    @Override
    public void onBackPressed() {
        mIsBackButtonPressed = true;
        super.onBackPressed();
    }
}
