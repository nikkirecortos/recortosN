package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class blank extends OnTouch {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank);
        final Button btnOnTouch = (Button) findViewById(R.id.btnOnTouch);
        btnOnTouch.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v){
                Intent i = new Intent(blank.this,OnTouch.class);
                startActivity(i);
            }

        });}

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(blank.this, MainActivity.class);
        startActivity(intent);
    }}
