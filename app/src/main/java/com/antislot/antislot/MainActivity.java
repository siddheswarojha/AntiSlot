package com.antislot.antislot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button btnStart, btnEnd;
    TextView txtTimeStart, txtTimeEnd;
    long endTime, startTime;
    String preference_Name = "timeData";
    java.sql.Date date;
    String url = "https://anti-sloth.herokuapp.com/api/user/show/";
    String userName = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);
        btnEnd = findViewById(R.id.btnEnd);
        txtTimeEnd = findViewById(R.id.txtTimeEnd);
        txtTimeStart = findViewById(R.id.txtTimeStart);

        Intent i = getIntent();
        userName = i.getStringExtra("username");
        url=url+userName;
        Log.d("myUrl",url);



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
            int seconds = (int) (startTime / 1000) % 60;
            int minutes = (int) ((startTime / (1000 * 60)) % 60);
            int hours = (int) ((startTime / (1000 * 60 * 60)) % 24);

            Log.d("timeStart: ", hours + ":" + minutes + ":" + seconds);
            Toast.makeText(MainActivity.this, String.valueOf(startTime), Toast.LENGTH_SHORT).show();
            date = new java.sql.Date(System.currentTimeMillis());
            System.out.println("Current Date: " + date);

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
                int seconds = (int) (endTime / 1000) % 60;
                int minutes = (int) ((endTime / (1000 * 60)) % 60);
                int hours = (int) ((endTime / (1000 * 60 * 60)) % 24);

                Log.d("timeEnd: ", hours + ":" + minutes + ":" + seconds);
                Toast.makeText(MainActivity.this, String.valueOf(endTime), Toast.LENGTH_SHORT).show();

                calculateValues(startTime, endTime, date);

            }
        });


    }


    private void calculateValues(long startTime, long endTime, java.sql.Date date) {

        // for start time
        int seconds = (int) (startTime / 1000) % 60;
        int minutes = (int) ((startTime / (1000 * 60)) % 60);
        int hours = (int) ((startTime / (1000 * 60 * 60)) % 24);
        String start = hours + ":" + minutes + ":" + seconds;

        // for end time
        int endSeconds = (int) (endTime / 1000) % 60;
        int endMinutes = (int) ((endTime / (1000 * 60)) % 60);
        int endHours = (int) ((endTime / (1000 * 60 * 60)) % 24);
        String end = endHours + ":" + endMinutes + ":" + endSeconds;

        //// for date
        String mDate = date.toString();

        // for Total Time
        long totalTime = endTime - startTime;
        int totalSecond = (int) (totalTime / 1000) % 60;
        int totalMinutes = (int) ((totalTime / (1000 * 60)) % 60);
        int totalHours = (int) ((totalTime / (1000 * 60 * 60)) % 24);

        String total = totalHours + ":" + totalMinutes + ":" + totalSecond;

        sendDetailsToServer(start, end, total, mDate);


    }

    private void sendDetailsToServer(String start, String end, String total, String mDate) {
        JSONObject js = new JSONObject();
        try {
            js.put("date", mDate);
            js.put("start", start);
            js.put("end", end);
            js.put("total", total);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Make request for JSONObject
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST, url, js,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        if (response.equals("Registered")) {
                            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(MainActivity.this, MainActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(MainActivity.this, "Error Response", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("VolleyErrorFound", String.valueOf(error));
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

        };

        // Adding request to request queue
        Volley.newRequestQueue(this).add(jsonObjReq);

    }


}








