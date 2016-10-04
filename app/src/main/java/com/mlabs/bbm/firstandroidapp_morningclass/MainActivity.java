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

        final EditText userName= (EditText)findViewById(R.id.etUser);
        final EditText password = (EditText) findViewById(R.id.etPassword);
        final Button btnSignup = (Button) findViewById(R.id.btnSignup);
        final Button btnLogin = (Button) findViewById(R.id.btnLogin);
        final Button btnShow = (Button) findViewById(R.id.btnShow);
        final DatabaseAdapter sqlDB = new DatabaseAdapter(getApplicationContext());
        final Context context = this;


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnShow.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
//                  if(motionEvent.getAction() == MotionEvent.ACTION_DOWN)
//                    {
//                        passWord.setTransformationMethod(null);
//                        Show.setText("Hide");
//                        return true;
//                    }
//                  else
//                  {
//                   passWord.setTransformationMethod(new PasswordTransformationMethod());
//                      return false;
//                  }
                        switch (motionEvent.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                password.setTransformationMethod(null);
                                return true;
                            case MotionEvent.ACTION_UP:
                                password.setTransformationMethod(new PasswordTransformationMethod());
                                return false;
                            default:
                                return false;


                        }

                    }

                });
                btnLogin.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        if (userName.equals("") || password.equals("")) {
                            Toast.makeText(getApplicationContext(), "Fill Up required fields", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if (!validateEmail(userName.getText().toString())) {
                            userName.setError("Not a valid Username or Email!");

                        }
                        if (!validatePassword(password.getText().toString())) {
                            password.setError("Not a valid password!");
                        } else {
                            userName.setError(null);
                            password.setError(null);
                            doLogin();

                        }
                    }
                });
                btnSignup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, Registerform.class);
                        startActivity(i);
                    }

                });
            }

                private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
                private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
                private Matcher matcher;

            public boolean validateEmail(String email) {
                matcher = pattern.matcher(email);
                return matcher.matches();
            }

            private static final String USERNAME = "^[a-z0-9_-]{3,15}$";
            private Pattern pattern1 = Pattern.compile(USERNAME);
            private Matcher matcher1;


            public boolean validatePassword(String Pw) {
                return Pw.length() >= 8;
            }

            public void doLogin() {
                Toast.makeText(getApplicationContext(), "Successfully Logged-in", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, blank.class);

                startActivity(i);
            }});
    }

            protected void onPause() {
                super.onPause();
                finish();
            }

        }
