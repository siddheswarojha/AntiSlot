package com.antislot.antislot;

import androidx.appcompat.app.AppCompatActivity;

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);
        btnEnd = findViewById(R.id.btnEnd);
        txtTimeEnd = findViewById(R.id.txtTimeEnd);
        txtTimeStart = findViewById(R.id.txtTimeStart);


        btnStart.setOnClickListener(v -> {

            Date currentTime = Calendar.getInstance().getTime();
            txtTimeStart.setText(currentTime.toString());



            long startTime = Calendar.getInstance().getTimeInMillis();
            Log.d("startTime", String.valueOf(startTime));
            Toast.makeText(MainActivity.this, String.valueOf(startTime), Toast.LENGTH_SHORT).show();

        });


        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date currentTime = Calendar.getInstance().getTime();
                txtTimeEnd.setText(currentTime.toString());

                long endTime = Calendar.getInstance().getTimeInMillis();
                Log.d("endTime", String.valueOf(endTime));
                Toast.makeText(MainActivity.this, String.valueOf(endTime), Toast.LENGTH_SHORT).show();


            }
        });


    }
}







