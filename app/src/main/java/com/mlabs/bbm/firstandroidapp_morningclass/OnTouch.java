package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


        public class OnTouch extends MainActivity {

                @Override
        protected void onCreate(Bundle savedInstanceState) {
               super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_on_touch);

                        final ImageView image = (ImageView)findViewById(R.id.imageView);

                      image.setOnTouchListener(new View.OnTouchListener() {
                               float iX,iY,fX,fY;
                               @Override
                                public boolean onTouch(View view, MotionEvent event) {
                                        switch (event.getAction()) {
                                               case MotionEvent.ACTION_DOWN:
                                                         iX = event.getX();
                                                        iY = event.getY();

                                                                                    case MotionEvent.ACTION_UP:
                                                        fX = event.getX();
                                                       fY = event.getY();

                                                                  if(iX<fX){

                                                                           Toast.makeText(getApplicationContext(), "Inital X=" + iX + " Final X=" + fX +" Left to Right swipe", Toast.LENGTH_SHORT).show();}
                                                           if(iX>fX){

                                                                           Toast.makeText(getApplicationContext(), "Initial X=" + iX + " Final X=" + fX+" Right to Left swipe", Toast.LENGTH_SHORT).show();
                                                              }
                                                           if (iY<fY){

                                                                           Toast.makeText(getApplicationContext(), "Initial Y=" + iY + " Final Y=" + fY+" Downward swipe", Toast.LENGTH_SHORT).show();
                                                              }
                                                           if (iY>fY){

                                                                            Toast.makeText(getApplicationContext(), "Initial Y=" + iY + " Final Y=" + fY+" Upward swipe", Toast.LENGTH_SHORT).show();
                                                              }
                                                      return true;
                                                  }
                                       return false;

                                         }
                          });


                           }

               @Override
        protected  void onPause(){
               super.onPause();
              finish();
          }
            @Override
            public void onBackPressed(){
                Intent intent = new Intent(OnTouch.this, MainActivity.class);
                startActivity(intent);
            }

            }