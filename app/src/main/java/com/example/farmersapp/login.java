package com.example.farmersapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class login extends AppCompatActivity {
    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        textView = (TextView) findViewById(R.id.sign_up);
        button = findViewById(R.id.lognbtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn();
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign();
            }
        });
    }

    private void btn() {
        Intent intent = new Intent(this,services.class);
        startActivity(intent);
    }

    public  void sign()
    {
        Intent intent = new Intent(this,signUp.class);
        startActivity(intent);
    }


}