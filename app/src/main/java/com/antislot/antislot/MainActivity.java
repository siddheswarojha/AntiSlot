package com.antislot.antislot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button btnStart, btnEnd;
    TextView txtTimeStart, txtTimeEnd;
    long endTime, startTime;
    String preference_Name = "timeData";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);
        btnEnd = findViewById(R.id.btnEnd);
        txtTimeEnd = findViewById(R.id.txtTimeEnd);
        txtTimeStart = findViewById(R.id.txtTimeStart);
        SharedPreferences.Editor editor = getSharedPreferences(preference_Name, MODE_PRIVATE).edit();




        SharedPreferences prefs = getSharedPreferences(preference_Name, MODE_PRIVATE);
        String timeStart = prefs.getString("startTime", "No Data Available");
        String timeEnd = prefs.getString("endTime", "No Data Available");

        txtTimeStart.setText(timeStart);
        txtTimeEnd.setText(timeEnd);








        btnStart.setOnClickListener(v -> {

            Date currentTime = Calendar.getInstance().getTime();




            // setting values in shared preferences


            editor.putString("startTime", currentTime.toString());
            editor.apply();


            txtTimeStart.setText(currentTime.toString());




            startTime = Calendar.getInstance().getTimeInMillis();
            Log.d("startTime", String.valueOf(startTime));
            int seconds = (int) (startTime / 1000) % 60 ;
            int minutes = (int) ((startTime / (1000*60)) % 60);
            int hours   = (int) ((startTime / (1000*60*60)) % 24);

            Log.d("timeStart: ",hours+":"+minutes+":"+seconds);
            Toast.makeText(MainActivity.this, String.valueOf(startTime), Toast.LENGTH_SHORT).show();

            startTimeToServer(startTime);
        });


        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date currentTime = Calendar.getInstance().getTime();



                editor.putString("endTime", currentTime.toString());
                editor.apply();


                txtTimeEnd.setText(currentTime.toString());


                endTime = Calendar.getInstance().getTimeInMillis();
                Log.d("endTime", String.valueOf(endTime));
                int seconds = (int) (endTime / 1000) % 60 ;
                int minutes = (int) ((endTime / (1000*60)) % 60);
                int hours   = (int) ((endTime / (1000*60*60)) % 24);

                Log.d("timeEnd: ",hours+":"+minutes+":"+seconds);
                Toast.makeText(MainActivity.this, String.valueOf(endTime), Toast.LENGTH_SHORT).show();

                endTimeToServer(endTime);

            }
        });


    }

    private void startTimeToServer(long startTime) {
    }

    private void endTimeToServer(long endTime) {
    }
}







