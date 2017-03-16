package com.example.weeboos.bezier;

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

    public void secondBezier(View view){
        Intent intent = new Intent(MainActivity.this,SecondBezierActivity.class);
        startActivity(intent);
    }

    public void thirdBezier(View view){
        Intent intent = new Intent(MainActivity.this,ThirdBezierActivity.class);
        startActivity(intent);
    }

    public void drawPad(View view){
        Intent intent = new Intent(MainActivity.this,DrawPadActivity.class);
        startActivity(intent);
    }

    public void jumpToPathMorthing(View view){
        Intent intent = new Intent(MainActivity.this,PathMorthingActivity.class);
        startActivity(intent);
    }

    public void jumpToWave(View view){
        Intent intent = new Intent(MainActivity.this,WaveActivity.class);
        startActivity(intent);
    }

    public void jumpToBasket(View view){
        Intent intent = new Intent(MainActivity.this,PathBezierActivity.class);
        startActivity(intent);
    }
}
