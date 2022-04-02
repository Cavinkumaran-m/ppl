package com.example.ppl;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class commonpage extends AppCompatActivity {

    MaterialButton button1 ,button2 ;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commonpage);
        button1 = (MaterialButton) findViewById(R.id.auth);
        button2 = (MaterialButton) findViewById(R.id.people);
        mAuth  = FirebaseAuth.getInstance();

        button1.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("MissingSuperCall")
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),authoritylogin.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAuth.getCurrentUser() != null) {
                    Intent intent = new Intent(commonpage.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getApplicationContext(), sendotp.class);
                    startActivity(intent);
                }
            }
        });

    }









}