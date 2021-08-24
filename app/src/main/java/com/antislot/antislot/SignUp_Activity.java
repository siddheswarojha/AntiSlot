package com.antislot.antislot;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.antislot.antislot.Model.userModel;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class SignUp_Activity extends AppCompatActivity {


    TextInputEditText itlName, itlEmail, itlPassword, itlUserName;
    Button btnSignUp;
    String baseUrl = "https://anti-sloth.herokuapp.com/api/auth/register";
//    String endPoint = ""

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        itlName = findViewById(R.id.itlSignUpName);
        itlEmail = findViewById(R.id.itlSignUpEmail);
        itlPassword = findViewById(R.id.itlSingUpPassword);
        itlUserName = findViewById(R.id.itlSignUpUserName);

        btnSignUp = findViewById(R.id.btnSignUp);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = Objects.requireNonNull(itlUserName.getText()).toString();
                String email = Objects.requireNonNull(itlEmail.getText()).toString();
                String password = Objects.requireNonNull(itlPassword.getText()).toString();
                String Name = Objects.requireNonNull(itlName.getText()).toString();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(userName) || TextUtils.isEmpty(Name)) {
                    Toast.makeText(SignUp_Activity.this, "Fields cannot be Null", Toast.LENGTH_SHORT).show();

                } else if (password.length() < 8) {
                    Toast.makeText(SignUp_Activity.this, "Password must have 8 or more characters", Toast.LENGTH_SHORT).show();

                } else {

                    signUpUser(Name, userName, email, password);

                }


            }
        });


    }

    private void signUpUser(String name, String userName, String email, String password) {

        Log.d("trial","1");
        JSONObject js = new JSONObject();
        try {
            js.put("fullname",name);
            js.put("username",userName);
            js.put("email",email);
            js.put("password",password);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Make request for JSONObject
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST, baseUrl, js,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        if (response.equals("Registered")) {
                            Toast.makeText(SignUp_Activity.this, "Success", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(SignUp_Activity.this, MainActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(SignUp_Activity.this, "Error Response", Toast.LENGTH_SHORT).show();
                }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("VolleyErrorFound", String.valueOf(error));
                Toast.makeText(SignUp_Activity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
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



//        StringRequest stringRequest = new StringRequest(Request.Method.POST, baseUrl, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                if (response.equals("success")) {
//                    Intent i = new Intent(SignUp_Activity.this, MainActivity.class);
//                    startActivity(i);
//                } else {
//                    Toast.makeText(SignUp_Activity.this, "Error Response", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("error", String.valueOf(error));
//                Toast.makeText(SignUp_Activity.this, "Unusual Turbulence", Toast.LENGTH_SHORT).show();
//
//            }
//
//        }){
//            @Nullable
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String , String> map = new HashMap<>();
//                map.put("fullname",name);
//                map.put("username",userName);
//                map.put("email",email);
//                map.put("password",password);
//
//
//                return map;
//            }
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Content-Type", "application/json; charset=utf-8");
//                return headers;
//            }
//        };
//        RequestQueue rq = Volley.newRequestQueue(getApplicationContext());
//        rq.add(stringRequest);
//
//
//
//    }










}


