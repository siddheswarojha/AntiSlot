package com.antislot.antislot;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.antislot.antislot.Model.userModel;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp_Activity extends AppCompatActivity {


    TextInputEditText itlName, itlEmail,itlPassword, itlUserName;
    Button btnSignUp;
    userModel user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        itlName = findViewById(R.id.itlSignUpName);
        itlEmail = findViewById(R.id.itlSignUpEmail);
        itlPassword = findViewById(R.id.itlSingUpPassword);
         itlUserName= findViewById(R.id.itlSignUpUserName);

         btnSignUp = findViewById(R.id.btnSignUp);


         btnSignUp.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String userName = itlUserName.getText().toString();
                 String email  = itlEmail.getText().toString();
                 String password = itlPassword.getText().toString();
                 String Name = itlName.getText().toString();

                 if(TextUtils.isEmpty(email) ||TextUtils.isEmpty(password) ||TextUtils.isEmpty(userName)||TextUtils.isEmpty(Name) )
                 {
                     Toast.makeText(SignUp_Activity.this, "Fields cannot be Null", Toast.LENGTH_SHORT).show();

                 }
                 else if (password.length()<8)
                 {
                     Toast.makeText(SignUp_Activity.this, "Password must have 8 or more characters", Toast.LENGTH_SHORT).show();

                 }
                 else
                 {

                     signUpUser(Name,userName,email,password);

                 }






             }
         });




    }

    private void signUpUser(String name, String userName, String email, String password) {



    }


}