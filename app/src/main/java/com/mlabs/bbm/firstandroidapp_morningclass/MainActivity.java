package com.mlabs.bbm.firstandroidapp_morningclass;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.MotionEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText userName = (EditText) findViewById(R.id.etUser);
        final EditText password = (EditText) findViewById(R.id.etPassword);
        final Button btnSignup = (Button) findViewById(R.id.btnSignup);
        final Button btnLogin = (Button) findViewById(R.id.btnLogin);
        final Button btnShow = (Button) findViewById(R.id.btnShow);
        DatabaseAdapter sqlDB = new DatabaseAdapter(getApplicationContext());
        final Context context = this;


        btnShow.setOnTouchListener(new View.OnTouchListener() {
            @Override

            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        password.setTransformationMethod(null);
                        password.setSelection(password.getText().length());
                        return true;
                    }
                    case MotionEvent.ACTION_UP: {
                        password.setTransformationMethod(new PasswordTransformationMethod());
                        password.setSelection(password.getText().length());
                        return true;
                    }
                }
                return false;
            }

        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (userName.getText().toString().equals("") || password.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Fill Up required fields", Toast.LENGTH_LONG).show();

                } else {
                    DatabaseAdapter db = new DatabaseAdapter(context);
                    if (db.validateUser(userName.getText().toString(), password.getText().toString()))
                    {
                        Toast.makeText(getApplicationContext(), "Login failed!", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Login Success!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, blank.class);
                        startActivity(intent);
                        finish();
                    }

                }


                btnSignup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, Registerform.class);
                        startActivity(i);
                    }
                });

            }

            private boolean validateUsername(String user) {
                final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
                Pattern pattern = Pattern.compile(EMAIL_PATTERN);
                Matcher matcher = pattern.matcher(user);
                return matcher.matches();
            }


            public void onBackPressed() {
                moveTaskToBack(true);
            }
        });
    }}
