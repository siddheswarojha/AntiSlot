package com.antislot.antislot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login_Activity extends AppCompatActivity {

    TextInputEditText etEmail,etPassword;
    String email, password;
    Button btnLogin;
    String baseUrl= "https://anti-sloth.herokuapp.com/api/auth/login";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail = findViewById(R.id.etLoginMail);
        etPassword = findViewById(R.id.etLoginPassword);
        btnLogin = findViewById(R.id.btnLogin);


        btnLogin.setOnClickListener(v -> {
            email = etEmail.getText().toString();
            password = etPassword.getText().toString();

            loginUser(email,password);
        });


    }

    private void loginUser(String email, String password) {

        Intent i = new Intent(Login_Activity.this,MainActivity.class);
        i.putExtra("username","india");
        startActivity(i);
    }


//        JSONObject js = new JSONObject();
//        try {
//
//            js.put("email",email);
//            js.put("password",password);
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        // Make request for JSONObject
//        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
//                Request.Method.POST, baseUrl, js,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//
//                        if (response.equals("Registered")) {
//                            Toast.makeText(Login_Activity.this, "Success", Toast.LENGTH_SHORT).show();
//                            Intent i = new Intent(Login_Activity.this, MainActivity.class);
//                            startActivity(i);
//                        } else {
//                            Toast.makeText(Login_Activity.this, "Error Response", Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("VolleyErrorFound", String.valueOf(error));
//                Toast.makeText(Login_Activity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
//            }
//        }) {
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Content-Type", "application/json; charset=utf-8");
//                return headers;
//            }
//
//        };
//
//        // Adding request to request queue
//        Volley.newRequestQueue(this).add(jsonObjReq);
//
//    }




}
