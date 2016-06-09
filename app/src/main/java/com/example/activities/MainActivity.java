package com.example.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
