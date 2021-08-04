package com.antislot.antislot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Button btnStart, btnEnd;

    private static final long START_TIME_IN_MILLIS = 28800000;
    private CountDownTimer mCountDownTimer;
    private boolean isRunning=true;
    private long timeLeftInMillis = START_TIME_IN_MILLIS;
    String timeLeftFormatted;
    int ms=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);
        btnEnd = findViewById(R.id.btnEnd);






        while(true) {



            if (!isRunning) {
                break;

            } else {

                Thread th = new Thread() {
                    @Override
                    public void run() {


                        try {
                            sleep(1);
                            ms++;
                            Log.d("mssss", String.valueOf(ms));

                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
                        }
                    }
                };
                th.start();
            }
        }









        btnStart.setOnClickListener(v -> {
//            if(!isRunning)
//            {
//                startTimer();
//
//            }
//            else
//            {
//                Toast.makeText(this, "Timer Already in progress", Toast.LENGTH_SHORT).show();
//            }




        });
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, String.valueOf(ms), Toast.LENGTH_SHORT).show();
                isRunning = false;
            }
        });

//        btnEnd.setOnClickListener(v -> {
////            Toast.makeText(this, timeLeftFormatted, Toast.LENGTH_SHORT).show();
////            isRunning=false;
////            timeLeftInMillis = START_TIME_IN_MILLIS;
//
//        });


    }


//        private void startTimer() {
//            mCountDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
//                @Override
//                public void onTick(long millisUntilFinished) {
//                    timeLeftInMillis = millisUntilFinished;
//                    updateCountDownText();
//                }
//                @Override
//                public void onFinish() {
//                    isRunning = false;
//
//                }
//            }.start();
//            isRunning = true;
//
//    }
//
//
//
//    private void updateCountDownText() {
//        int hour = (int) (timeLeftInMillis / 3600000);
//        int minutes = (int) (timeLeftInMillis / 10000) / 60;
//        int seconds = (int) (timeLeftInMillis / 1000) % 60;
//         timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d:%02d",hour, minutes, seconds);
//
//    }

}