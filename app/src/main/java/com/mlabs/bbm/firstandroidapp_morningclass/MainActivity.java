package com.mlabs.bbm.firstandroidapp_morningclass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.MotionEvent;



public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText email = (EditText) findViewById(R.id.regEmail);
        final EditText password = (EditText) findViewById(R.id.etPassword);
        final Button btnSignup = (Button) findViewById(R.id.btnSignup);
        final Button btnLogin = (Button) findViewById(R.id.btnLogin);
        final Button btnShow = (Button) findViewById(R.id.btnShow);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isValidEmail(email.getText().toString())) {
                    email.setError("Invalid Email");
                    email.requestFocus();

                } else if (!isValidPassword(password.getText().toString())) {
                    password.setError("Invalid Password");
                    password.requestFocus();

                } else {
                    Toast.makeText(getApplicationContext(), "Login Successful!", Toast.LENGTH_SHORT).show();
                    Intent loginIntent = new Intent(MainActivity.this, blank.class);
                    startActivity(loginIntent);
                }

            }
        });
            btnSignup.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    Intent myIntent = new Intent(v.getContext(), Registerform.class);
                    startActivityForResult(myIntent, 0);
                    onPause();
                }

            });


             btnShow.setOnTouchListener(new View.OnTouchListener(){
                     @Override
                 public boolean onTouch(View view, MotionEvent motionEvent) {

//                         if(event == motionEvent.ACTION_DOWN){
//                             Log.d("onTouchListener", "ACTION_DOWN was pressed");
//                             password.setTransformationMethod(null);
//                             return true;
//
//                         }
//                         else{
//                             Log.d("onTouchListener", "ACTION_DOWN was released");
//                             password.setTransformationMethod(new PasswordTransformationMethod());
//                             return false;
//                         }
//
                         switch (motionEvent.getAction()) {
                             case MotionEvent.ACTION_DOWN:
                                 password.setTransformationMethod(null);
                                 password.setSelection(password.getText().length());
                                 return true;
                             case MotionEvent.ACTION_UP:
                                 password.setTransformationMethod(new PasswordTransformationMethod());
                                 password.setSelection(password.getText().length());
                                 return false;
                         }
                         return true;

                     }


                     });



        }
        private boolean isValidEmail(String email) {
            String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            Pattern pattern = Pattern.compile(emailPattern);
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
        }
    private boolean isValidPassword(String password) {
        if (password != null && password.length() > 8) {
            return true;
        }
        return false;

    }

    @Override
    protected void onResume(){
        super.onResume();
    }
}














