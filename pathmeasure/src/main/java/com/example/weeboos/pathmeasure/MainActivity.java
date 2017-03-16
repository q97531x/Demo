package com.example.weeboos.pathmeasure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pathTracing(View view){
        Intent intent = new Intent(MainActivity.this,PathTracingActivity.class);
        startActivity(intent);
    }

    public void pathPaint(View view){
        Intent intent = new Intent(MainActivity.this,PathPaintActivity.class);
        startActivity(intent);
    }

    public void pathPos(View view){
        Intent intent = new Intent(MainActivity.this,PathPosActivity.class);
        startActivity(intent);
    }
}
