package com.antislot.antislot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class Splash_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread th = new Thread() {
            @Override
            public void run() {


                try {
                    sleep(3000);

                } catch (Exception e) {
                    Toast.makeText(Splash_Activity.this, "Error", Toast.LENGTH_LONG).show();
                } finally {
                    Intent i = new Intent(Splash_Activity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
                super.run();
            }
        };
        th.start();
    }
}