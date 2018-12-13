package com.example.manthansingh.pokev1;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainPage1 extends AppCompatActivity {

    private static int SPLASH_TIME_OUT=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent page2 =new Intent(MainPage1.this,MainPage2.class);
                startActivity(page2);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
