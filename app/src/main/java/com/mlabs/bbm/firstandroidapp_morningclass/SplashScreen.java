package com.mlabs.bbm.firstandroidapp_morningclass;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.os.Handler;



public class SplashScreen extends MainActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);


        Thread timerThread = new Thread() {
            public void run() {

                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent loginIntent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(loginIntent);
                }
            }
        };
        timerThread.start();

    }
        @Override
        protected void onPause(){
            super.onPause();
            finish();
        }
    }

