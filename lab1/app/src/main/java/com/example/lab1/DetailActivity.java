package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Log.w("MyApp", "DetailActivity onCreate() called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.w("MyApp", "DetailActivity onStart() called");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.w("MyApp", "DetailActivity onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w("MyApp", "DetailActivity onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w("MyApp", "DetailActivity onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w("MyApp", "DetailActivity onDestroy() called");
    }
}