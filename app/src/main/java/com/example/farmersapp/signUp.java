package com.example.farmersapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.farmersapp.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class signUp extends AppCompatActivity {
    Button signUp;
    EditText confirmPass, email, password, username;
    FirebaseDatabase database;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        database =FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();


        email = findViewById(R.id.email);
        confirmPass = findViewById(R.id.confirm);
        password = findViewById(R.id.password);
        username = findViewById(R.id.username);
        signUp = (Button) findViewById(R.id.reg);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();

            }
        });
    }


    private void createUser() {


        String userName = username.getText().toString();
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();
        String userConfirmPass = confirmPass.getText().toString();

        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, "username is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(userEmail)) {
            Toast.makeText(this, "email is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(userPassword)) {
            Toast.makeText(this, "password is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userConfirmPass)) {
            Toast.makeText(this, "confirm password is empty", Toast.LENGTH_SHORT).show();
            return;
        }


        if (userPassword.length() < 6) {
            Toast.makeText(this, "password  length must be greater than 6 letters", Toast.LENGTH_SHORT).show();
            return;
        }


        //create user
        auth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    UserModel userModel = new UserModel(userName,userEmail,userPassword,userConfirmPass);
                    String id = task.getResult().getUser().getUid();
                    database.getReference().child("users").child(id).setValue(userModel);

                    Toast.makeText(signUp.this, "Registration is successful", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(signUp.this, "error"+task.getException(), Toast.LENGTH_SHORT).show();
                }


            }
        });
        {

        }
    }

}






