package com.example.component.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class TestActivity extends AppCompatActivity {
    String Tag = "TestActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(Tag,"onCreate");
        setContentView(R.layout.activity_test);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(Tag,"onDestroy");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(Tag,"onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(Tag,"onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(Tag,"onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(Tag,"onResume");
    }
}
